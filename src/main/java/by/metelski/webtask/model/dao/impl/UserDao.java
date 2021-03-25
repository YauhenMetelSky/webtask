package by.metelski.webtask.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
	private static final String FIND_USERS_BY_NAME = "SELECT user_id,name,surname FROM users where name=?";

	@Override
	public List<User> FindAllUsers() throws DaoException {

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
		throw new DaoException("Dao exception",e);
		}
		return users;
	}

	@Override
	public List<User> FindUsersByName(String userName) throws DaoException {
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
			throw new DaoException("Dao exception",e);
			}
		return users;
	}
}
