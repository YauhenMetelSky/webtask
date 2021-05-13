package by.metelski.webtask.model.dao;

import java.util.List;
import java.util.Optional;

import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.DaoException;

public interface UserDao {
	List<User> findAll() throws DaoException;

	List<User> findUsersByName(String userName) throws DaoException;

	Optional<String> findPasswordByEmail(String email) throws DaoException;
	
	Optional<User> findUserByEmail(String email) throws DaoException;

	boolean addUser(User user, String password) throws DaoException;
	
	boolean activateAccount(String email) throws DaoException;
	
	boolean blockUser(long id) throws DaoException;

}
