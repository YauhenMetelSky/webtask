package by.metelski.webtask.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {
	public static final Logger logger = LogManager.getLogger();
	private static boolean isCreated;
	private static ConnectionPool instance = new ConnectionPool();
	private static Lock locker = new ReentrantLock(true);
	private BlockingQueue<ProxyConnection> freeConnection;
	private Queue<ProxyConnection> givenAwayConnections;
	// TODO default pool size from connection or properties
	private static final int DEFAULT_POOL_SIZE = 8;

	private ConnectionPool() {
		freeConnection = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
		givenAwayConnections = new ArrayDeque<>();
		for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
			try {
				Connection connection = ConnectionCreator.getConnection();
				ProxyConnection proxyConnection = new ProxyConnection(connection);
				boolean isAddded = freeConnection.add(proxyConnection);
				logger.log(Level.INFO, "connection added to freeConnection: " + isAddded);
			} catch (SQLException e) {
				logger.log(Level.ERROR, "coudn't create connection to data base: " + e.getMessage());
			}
		}
		if (freeConnection.size() == 0) {
			logger.log(Level.FATAL, "connections poll don't created, pool size: " + freeConnection.size());
			throw new RuntimeException("connections poll don't created");
		}
	}

	public static ConnectionPool getInstance() {
		if (!isCreated) {
			locker.lock();
			if (instance == null) {
				instance = new ConnectionPool();
				isCreated = true;
			}
			locker.unlock();
		}
		logger.log(Level.DEBUG, "created instanse" + instance);
		return instance;
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = freeConnection.take();
			logger.log(Level.DEBUG, "Gave connection " + connection);
			givenAwayConnections.add((ProxyConnection) connection);
		} catch (InterruptedException e) {
			logger.log(Level.ERROR, "InterruptedException in method getConnection " + e.getMessage());
			Thread.currentThread().interrupt();
		}
		return connection;
	}

	public void releaseConnection(Connection connection) {
		if (connection instanceof ProxyConnection) {
			logger.log(Level.DEBUG, "release connection " + connection);
			if (givenAwayConnections.remove(connection)) {
				try {
					freeConnection.put((ProxyConnection) connection);
				} catch (InterruptedException e) {
					logger.log(Level.ERROR, "InterruptedException in method getConnection " + e.getMessage());
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	public void destroyPool() {
		for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
			try {
				logger.log(Level.DEBUG, "destroyPool");
				freeConnection.take().reallyClose();
			} catch (InterruptedException e) {
				logger.log(Level.ERROR, "InterruptedException in method destroyPool " + e.getMessage());
			} catch (SQLException e) {
				logger.log(Level.ERROR, "SQLException in method destroyPool " + e.getMessage());
			}
		}
		deregisterDrivers();
	}

	private void deregisterDrivers() {
		DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
			try {
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
				logger.log(Level.ERROR, "SQLException in method deregisterDrivers " + e.getMessage());
			}
		});
	}
}
