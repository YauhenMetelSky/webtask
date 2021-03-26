package by.metelski.webtask.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionCreator {
	private static final Logger logger = LogManager.getLogger();
	private ConnectionCreator() {
	};

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			//TODO Exception???
			logger.log(Level.ERROR, "exception in getConnection" +e.getMessage());
		}
		logger.log(Level.INFO, "return connection ");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb", "root", "Gfhjkmr,l");
	}
}
