package by.metelski.webtask.model.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	private BlockingQueue<Connection> freeConnection;
	private Queue<Connection> givenAwayConnections;
	private final static int DEFAULT_POOL_SIZE = 32;

	private ConnectionPool() {
		// TODO register driver, init pool params, init connection
		freeConnection = new LinkedBlockingDeque(DEFAULT_POOL_SIZE);
		givenAwayConnections = new ArrayDeque();
		for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
			try  {
				Connection connection = ConnectionCreator.getConnection();
				boolean isAddded = freeConnection.add(connection);
				logger.log(Level.INFO, "connection added to freeConnection: " + isAddded);
			} catch (SQLException e) {
				logger.log(Level.FATAL, "coudn't create connection to data base: ");
				throw new RuntimeException();
			}
		}
	}

//TODO realization
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
			givenAwayConnections.offer(connection);
		} catch (InterruptedException e) {
			logger.log(Level.ERROR, "InterruptedException in method getConnection " + e.getMessage());
		}
		return connection;
	}

	public void releaseConnection(Connection connection) {
		logger.log(Level.DEBUG, "release connection " + connection);
		givenAwayConnections.remove(connection);
		freeConnection.offer(connection);
	}

	public void destroyPool() {
		for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
			try {
				freeConnection.take().close();
			} catch (InterruptedException e) {
				logger.log(Level.ERROR, "InterruptedException in method destroyPool " + e.getMessage());
			} catch (SQLException e) {
				logger.log(Level.ERROR, "SQLException in method destroyPool " + e.getMessage());
			}
		}

	}

	private void deregisterDrivers() {

	}
}
