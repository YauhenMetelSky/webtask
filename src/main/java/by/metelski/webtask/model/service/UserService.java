package by.metelski.webtask.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.entity.User.Role;
import by.metelski.webtask.exception.ServiceException;

/**
 * The interface user service
 * @author Yauhen Metelski
 *
 */
public interface UserService {
	/**
	 * @param pageNumber
	 * @return List of users from row, calculated by page number
	 * @throws ServiceException
	 */
	List<User> findUsersFromRow(int pageNumber) throws ServiceException;

	/**
	 * @param userName
	 * @return List of users with such name
	 * @throws ServiceException
	 */
	List<User> findUsersByName(String userName) throws ServiceException;

	/**
	 * @param userSurname
	 * @return List of users with such surname
	 * @throws ServiceException
	 */
	List<User> findUsersBySurname(String userSurname) throws ServiceException;

	/**
	 * @param role
	 * @return List of users with such role
	 * @throws ServiceException
	 */
	List<User> findUsersByRole(Role role) throws ServiceException;

	/**
	 * @param email
	 * @return Optional<User> with this email
	 * @throws ServiceException
	 */
	Optional<User> findUserByEmail(String email) throws ServiceException;

	/**
	 * @param login
	 * @param password
	 * @return Optional<User> with these email and password
	 * @throws ServiceException
	 */
	Optional<User> findUserByEmailPassword(String login, String password) throws ServiceException;

	/**
	 * @return int number of pages calculated by number of rows in database
	 * @throws ServiceException
	 */
	int findNumberOfPages() throws ServiceException;

	/**
	 * @param userData
	 * @return boolean result of operation
	 * @throws ServiceException
	 */
	boolean addUser(Map<String, String> userData) throws ServiceException;

	/**
	 * @param token
	 * @param email
	 * @return boolean result of operation
	 * @throws ServiceException
	 */
	boolean activateAccount(String token, String email) throws ServiceException;

	/**
	 * @param id
	 * @param isBlocked
	 * @return boolean result of operation
	 * @throws ServiceException
	 */
	boolean changeUserIsBlocked(long id, boolean isBlocked) throws ServiceException;

	/**
	 * @param id
	 * @param role
	 * @return boolean result of operation
	 * @throws ServiceException
	 */
	boolean changeUserRole(long id, Role role) throws ServiceException;

	/**
	 * @param user
	 * @param userData
	 * @return boolean result of operation
	 * @throws ServiceException
	 */
	boolean changePersonalInfo(User user, Map<String, String> userData) throws ServiceException;
}
