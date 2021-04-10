package by.metelski.webtask.model.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.DaoException;

public interface UserDao {
	List<User> findAll() throws DaoException;

	List<User> findUsersByName(String userName) throws DaoException;

	Optional<String> findPasswordByLogin(String login) throws DaoException;

	Optional<User> findUserByLogin(String login) throws DaoException;

	boolean addUser(Map<String, String> userData, String password) throws DaoException;
}
