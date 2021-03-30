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
import by.metelski.webtask.model.connection.ConnectionCreator;
import by.metelski.webtask.model.dao.BaseDao;
import by.metelski.webtask.model.entity.User;

public class UserDao implements BaseDao {
	private static final Logger logger = LogManager.getLogger();
	private static final String SHOW_ALL_USERS = "SELECT user_id,name,surname FROM users";
	private static final String FIND_USERS_BY_NAME = "SELECT user_id,name,surname FROM users WHERE name=?";
	private static final String FIND_PASSWORD_BY_LOGIN = "SELECT user_id,name,surname, password FROM users WHERE login=?";
	private static final String FIND_USER_BY_LOGIN = "SELECT user_id,name,surname FROM users WHERE login=?";
	// TODO correct query

	@Override
	public List<User> findAllUsers() throws DaoException {
		List<User> users = new ArrayList<User>();
		try (Connection connection = ConnectionCreator.getConnection();
				Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(SHOW_ALL_USERS);
			while (resultSet.next()) {
				int userId = resultSet.getInt("user_id");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				logger.log(Level.INFO, "user id:" + userId + " user name:" + name + " user surname:" + surname);
				users.add(new User(userId, name, surname));
			}
		} catch (SQLException e) {
			throw new DaoException("Dao exception", e);
		}
		return users;
	}

	@Override
	public List<User> findUsersByName(String userName) throws DaoException {
		List<User> users = new ArrayList<User>();
		try (Connection connection = ConnectionCreator.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_USERS_BY_NAME)) {
			statement.setString(1, userName);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int userId = resultSet.getInt("user_id");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				logger.log(Level.INFO, "user id:" + userId + "FIO: " + name + " " + surname);
				users.add(new User(userId, name, surname));
			}
		} catch (SQLException e) {
			throw new DaoException("Dao exception", e);
		}
		return users;
	}

	@Override
	public Optional<String> findPasswordByLogin(String login) throws DaoException {
		Optional<String> optionalPassword;
		String password = null;
		try (Connection connection = ConnectionCreator.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_PASSWORD_BY_LOGIN)) {
			logger.log(Level.INFO, "in try block");
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				password = resultSet.getString("password");
				optionalPassword = Optional.of(password);
				logger.log(Level.INFO, "password=" + password);
			} else {
				optionalPassword=Optional.empty();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
			throw new DaoException("Dao exception", e);
		}
		return optionalPassword;
	}

	@Override
	public Optional<User> findUserByLogin(String login) throws DaoException {
		logger.log(Level.INFO, "Find user by login, login=  " + login);
		Optional<User> optionalUser;
       //TODO double check, user in try?

		try (Connection connection = ConnectionCreator.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
			logger.log(Level.INFO, "in try block, login");
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int userId = resultSet.getInt("user_id");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				User user = new User(userId, name, surname);
				logger.log(Level.INFO, "user id:" + userId + "FIO: " + name + " " + surname);
				optionalUser = Optional.of(user);
			} else {
				logger.log(Level.INFO, "didn't find user with login:" + login);
				optionalUser = Optional.empty();
			}

		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-- " + e.getErrorCode());
			throw new DaoException("Dao exception in method findUserByLogin", e);
		}
		return optionalUser;
	}
}
