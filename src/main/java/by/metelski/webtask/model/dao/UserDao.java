package by.metelski.webtask.model.dao;

import java.util.List;
import java.util.Optional;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.entity.User.Role;
import by.metelski.webtask.exception.DaoException;

/**
 * The interface user dao
 * @author Yauhen Metelski
 *
 */
public interface UserDao {
	/**
	 * @return List of all users from database
	 * @throws DaoException
	 */
	List<User> findAll() throws DaoException;

	/**
	 * @param fromRow
	 * @param numberOfUsersInPage
	 * @return List of users from row
	 * @throws DaoException
	 */
	List<User> findUsersFromRow(int fromRow, int numberOfUsersInPage) throws DaoException;

	/**
	 * @param userName
	 * @return List of users with such name
	 * @throws DaoException
	 */
	List<User> findUsersByName(String userName) throws DaoException;

	/**
	 * @param userSurname
	 * @return List of users with such surname
	 * @throws DaoException
	 */
	List<User> findUsersBySurname(String userSurname) throws DaoException;

	/**
	 * @param role
	 * @return List of users with such role
	 * @throws DaoException
	 */
	List<User> findUsersByRole(Role role) throws DaoException;

	/**
	 * @param email
	 * @return Optional<User> 
	 * @throws DaoException
	 */
	Optional<String> findPasswordByEmail(String email) throws DaoException;

	/**
	 * @param email
	 * @return Optional<User> 
	 * @throws DaoException
	 */
	Optional<User> findUserByEmail(String email) throws DaoException;

	/**
	 * @return int number of rows in database with users
	 * @throws DaoException
	 */
	int findNumberOfRows() throws DaoException;

	/**
	 * @param user
	 * @param password
	 * @return boolean result of operation
	 * @throws DaoException
	 */
	boolean addUser(User user, String password) throws DaoException;

	/**
	 * @param email
	 * @return boolean result of operation
	 * @throws DaoException
	 */
	boolean activateAccount(String email) throws DaoException;

	/**
	 * @param id
	 * @param isBlocked
	 * @return boolean result of operation
	 * @throws DaoException
	 */
	boolean changeIsBlockedStatus(long id, boolean isBlocked) throws DaoException;

	/**
	 * @param id
	 * @param role
	 * @return boolean result of operation
	 * @throws DaoException
	 */
	boolean changeUserRole(long id, Role role) throws DaoException;

	/**
	 * @param user
	 * @return boolean result of operation
	 * @throws DaoException
	 */
	boolean changePersonalInfo(User user) throws DaoException;
}
