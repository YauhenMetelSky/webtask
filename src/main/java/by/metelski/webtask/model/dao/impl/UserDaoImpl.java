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
import by.metelski.webtask.entity.User;
import by.metelski.webtask.entity.User.Role;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.model.connection.ConnectionPool;
import by.metelski.webtask.model.dao.UserDao;

import static by.metelski.webtask.model.dao.ColumnName.*;

public class UserDaoImpl implements UserDao {
	private static final Logger logger = LogManager.getLogger();
	private static final String SQL_FIND_ALL_USERS = "SELECT user_id,name,surname,email,phone,is_blocked,role FROM users";
	private static final String SQL_FIND_USERS_BY_NAME = "SELECT user_id,name,surname,email,phone,is_blocked,role FROM users WHERE name=?";
	private static final String SQL_FIND_USERS_BY_ROLE = "SELECT user_id,name,surname,email,phone,is_blocked,role FROM users WHERE role=?";
	private static final String SQL_FIND_PASSWORD_BY_EMAIL = "SELECT password FROM users WHERE email=?";
	private static final String SQL_FIND_USER_BY_EMAIL = "SELECT user_id,name,surname,email,phone,is_blocked,role FROM users WHERE email=?";
	private static final String SQL_ADD_USER = "INSERT INTO users (name,surname,password,email,phone) values(?,?,?,?,?,?)";
	private static final String SQL_ACTIVATE_ACCOUNT = "UPDATE users SET is_active=true WHERE email=?";
	private static final String SQL_BLOCK_USER = "UPDATE users SET is_blocked=true WHERE id=?";
	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public List<User> findAll() throws DaoException {
		logger.log(Level.INFO, "Find all users");
		List<User> users = new ArrayList<User>();
		try (Connection connection = connectionPool.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_USERS)) {
			while (resultSet.next()) {
				long userId = resultSet.getLong(USER_ID);
				String name = resultSet.getString(USER_NAME);
				String surname = resultSet.getString(USER_SURNAME);
				String email = resultSet.getString(USER_EMAIL);
				String phone = resultSet.getString(USER_PHONE);
				boolean isBlocked = resultSet.getBoolean(IS_BLOCKED);
				Role role = Role.valueOf(resultSet.getString(ROLE).toUpperCase());
				logger.log(Level.DEBUG, "user id:" + userId + " user name:" + name + " user surname:" + surname);
				users.add(new User(userId, name, surname, email, phone, isBlocked, role));// TODO Use builder???
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in findAll: " + e.getMessage() + " : " + e.getErrorCode());
			throw new DaoException("Dao exception", e);
		}
		return users;
	}

	@Override
	public List<User> findUsersByName(String userName) throws DaoException {
		logger.log(Level.INFO, "Find user by name, name=  " + userName);
		List<User> users = new ArrayList<User>();
		try (Connection connection = connectionPool.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_NAME);
			statement.setString(1, userName);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long userId = resultSet.getLong(USER_ID);
				String name = resultSet.getString(USER_NAME);
				String surname = resultSet.getString(USER_SURNAME);
				String email = resultSet.getString(USER_EMAIL);
				String phone = resultSet.getString(USER_PHONE);
				boolean isBlocked = resultSet.getBoolean(IS_BLOCKED);
				Role role = Role.valueOf(resultSet.getString(ROLE).toUpperCase());
				logger.log(Level.INFO, "finded user id:" + userId + "FIO: " + name + " " + surname);
				users.add(new User(userId, name, surname, email, phone, isBlocked, role));
			}
		} catch (SQLException e) {
			throw new DaoException("Dao exception", e);
		}
		return users;
	}

	@Override
	public List<User> findUsersByRole(Role role) throws DaoException {
		logger.log(Level.INFO, "Find user by role, role=  " + role);
		List<User> users = new ArrayList<User>();
		try (Connection connection = connectionPool.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_ROLE);
			statement.setString(1, role.name());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long userId = resultSet.getLong(USER_ID);
				String name = resultSet.getString(USER_NAME);
				String surname = resultSet.getString(USER_SURNAME);
				String email = resultSet.getString(USER_EMAIL);
				String phone = resultSet.getString(USER_PHONE);
				boolean isBlocked = resultSet.getBoolean(IS_BLOCKED);
				Role userRole = Role.valueOf(resultSet.getString(ROLE));
				logger.log(Level.INFO, "finded user id:" + userId + ", FIO: " + name + " " + surname);
				users.add(new User(userId, name, surname, email, phone, isBlocked, userRole));
			}
		} catch (SQLException e) {
			throw new DaoException("Dao exception", e);
		}
		return users;
	}

	@Override
	public Optional<String> findPasswordByEmail(String email) throws DaoException {
		logger.log(Level.INFO, "Find password by email, email=  " + email);
		Optional<String> optionalPassword;
		String password = null;
		try (Connection connection = connectionPool.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_PASSWORD_BY_EMAIL);
			logger.log(Level.DEBUG, "in try block");
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				password = resultSet.getString(PASSWORD);
				optionalPassword = Optional.of(password);
				logger.log(Level.INFO, "password=" + password);
			} else {
				optionalPassword = Optional.empty();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in method findPasswordByLogin " + e.getMessage());
			throw new DaoException("Dao exception", e);
		}
		return optionalPassword;
	}

	@Override
	public Optional<User> findUserByEmail(String email) throws DaoException {
		logger.log(Level.INFO, "Find user by email, email=  " + email);
		Optional<User> optionalUser;
		try (Connection connection = connectionPool.getConnection();) {
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL);
			logger.log(Level.DEBUG, "in try block, login");
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				long userId = resultSet.getLong(USER_ID);
				String name = resultSet.getString(USER_NAME);
				String surname = resultSet.getString(USER_SURNAME);
				String userEmail = resultSet.getString(USER_EMAIL);
				String phone = resultSet.getString(USER_PHONE);
				boolean isBlocked = resultSet.getBoolean(IS_BLOCKED);
				Role role = Role.valueOf(resultSet.getString(ROLE).toUpperCase());// FIXME delete toUpperCase
				User user = new User(userId, name, surname, userEmail, phone, isBlocked, role);
				logger.log(Level.INFO, "finded user id:" + userId + "FIO: " + name + " " + surname);
				optionalUser = Optional.of(user);
			} else {
				logger.log(Level.INFO, "didn't find user with login:" + email);
				optionalUser = Optional.empty();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException("Dao exception in method findUserByEmail", e);
		}
		return optionalUser;
	}

	@Override
	public boolean addUser(User user, String password) throws DaoException {
		logger.log(Level.INFO, "Try to add user in db" + user);
		boolean userAdded = false;
		try (Connection connection = connectionPool.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER);
			statement.setString(1, user.getName());
			statement.setString(2, user.getSurname());
			statement.setString(4, password);
			statement.setString(5, user.getEmail());
			statement.setString(6, user.getPhone());
			int rowCount = statement.executeUpdate();
			if (rowCount != 0) {
				userAdded = true;
				logger.log(Level.INFO, "user added" + user);
			} else {
				logger.log(Level.ERROR, "user was not added");
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException("Dao exception in method addUser, when we try to add user:" + user, e);
		}
		return userAdded;
	}

	@Override
	public boolean activateAccount(String email) throws DaoException {
		logger.log(Level.INFO, "Try to activate user account, email:" + email);
		boolean isActive = false;
		try (Connection connection = connectionPool.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_ACTIVATE_ACCOUNT);
			statement.setString(1, email);
			int rowCount = statement.executeUpdate();
			if (rowCount != 0) {
				isActive = true;
				logger.log(Level.INFO, "account " + email + " is active.");
			} else {
				logger.log(Level.ERROR, "account " + email + " was't activated");
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException("Dao exception in method activateAccount", e);
		}
		return isActive;
	}

	@Override
	public boolean blockUser(long id) throws DaoException {
		logger.log(Level.INFO, "Try to block user account :" + id);
		boolean isBlocked = false;
		try (Connection connection = connectionPool.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_BLOCK_USER);
			statement.setLong(1, id);
			int rowCount = statement.executeUpdate();
			if (rowCount != 0) {
				isBlocked = true;
				logger.log(Level.INFO, "account " + id + " is blocked.");
			} else {
				logger.log(Level.ERROR, "account " + id + " was't blocked");
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException("Dao exception in method activateAccount", e);
		}
		return isBlocked;
	}
}
