package by.metelski.webtask.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.model.connection.ConnectionPool;
import by.metelski.webtask.model.dao.ColumnName;
import by.metelski.webtask.model.dao.UserDao;
import by.metelski.webtask.model.entity.User;

public class UserDaoImpl implements UserDao {
	private static final Logger logger = LogManager.getLogger();
	private static final String SQL_FIND_ALL_USERS = "SELECT user_id,name,surname FROM users";
	private static final String SQL_FIND_USERS_BY_NAME = "SELECT user_id,name,surname FROM users WHERE name=?";
	private static final String SQL_FIND_PASSWORD_BY_LOGIN = "SELECT user_id,name,surname, password FROM users WHERE login=?";
	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT user_id,name,surname FROM users WHERE login=?";
	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public List<User> findAll() throws DaoException {
		List<User> users = new ArrayList<User>();
		Connection connection = connectionPool.getConnection();
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_USERS)) {
			while (resultSet.next()) {
				int userId = resultSet.getInt(ColumnName.USER_ID);
				String name = resultSet.getString(ColumnName.USER_NAME);
				String surname = resultSet.getString(ColumnName.USER_SURNAME);
				logger.log(Level.DEBUG, "user id:" + userId + " user name:" + name + " user surname:" + surname);
				users.add(new User(userId, name, surname));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in findAll: " + e.getMessage() + " : " + e.getErrorCode());
			throw new DaoException("Dao exception", e);
		} finally {
			connectionPool.releaseConnection(connection);
		}
		return users;
	}

	@Override
	public List<User> findUsersByName(String userName) throws DaoException {
		List<User> users = new ArrayList<User>();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_NAME);
			statement.setString(1, userName);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int userId = resultSet.getInt(ColumnName.USER_ID);
				String name = resultSet.getString(ColumnName.USER_NAME);
				String surname = resultSet.getString(ColumnName.USER_SURNAME);
				logger.log(Level.INFO, "finded user id:" + userId + "FIO: " + name + " " + surname);
				users.add(new User(userId, name, surname));
			}
		} catch (SQLException e) {
			throw new DaoException("Dao exception", e);
		} finally {
			connectionPool.releaseConnection(connection);
		}
		return users;
	}

	@Override
	public Optional<String> findPasswordByLogin(String login) throws DaoException {
		Optional<String> optionalPassword;
		String password = null;
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_PASSWORD_BY_LOGIN);
			logger.log(Level.INFO, "in try block");
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				password = resultSet.getString(ColumnName.PASSWORD);
				optionalPassword = Optional.of(password);
				logger.log(Level.INFO, "password=" + password);
			} else {
				optionalPassword = Optional.empty();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in method findPasswordByLogin " + e.getMessage());
			throw new DaoException("Dao exception", e);
		} finally {
			connectionPool.releaseConnection(connection);
		}
		return optionalPassword;
	}

	@Override
	public Optional<User> findUserByLogin(String login) throws DaoException {
		logger.log(Level.INFO, "Find user by login, login=  " + login);
		Optional<User> optionalUser;
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
			logger.log(Level.DEBUG, "in try block, login");
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int userId = resultSet.getInt(ColumnName.USER_ID);
				String name = resultSet.getString(ColumnName.USER_NAME);
				String surname = resultSet.getString(ColumnName.USER_SURNAME);
				User user = new User(userId, name, surname);
				logger.log(Level.INFO, "finded user id:" + userId + "FIO: " + name + " " + surname);
				optionalUser = Optional.of(user);
			} else {
				logger.log(Level.INFO, "didn't find user with login:" + login);
				optionalUser = Optional.empty();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-- " + e.getErrorCode());
			throw new DaoException("Dao exception in method findUserByLogin", e);
		} finally {
			connectionPool.releaseConnection(connection);
		}
		return optionalUser;
	}
}
