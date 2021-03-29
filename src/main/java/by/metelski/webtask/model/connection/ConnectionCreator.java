package by.metelski.webtask.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionCreator {
	private static final Logger logger = LogManager.getLogger();
	private static final String DATABASE_PASSWORD = "Gfhjkmr,l";
	private static final String DATABASE_USER = "root";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/projectdb";
	private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
	static {
		try {
			Class.forName(DATABASE_DRIVER);
		} catch (ClassNotFoundException e) {
			logger.log(Level.ERROR, "exception in getConnection" + e.getMessage());
		}
	}

	private ConnectionCreator() {
	};

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
	}
}
