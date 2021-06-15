package by.metelski.webtask.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.entity.User.Role;
import by.metelski.webtask.exception.ServiceException;

public interface UserService {
//FIXME delete	List<User> findAllUsers() throws ServiceException;
	List<User> findUsersFromRow(int pageNumber) throws ServiceException;
	List<User> findUsersByName(String userName) throws ServiceException;
	List<User> findUsersBySurname(String userSurname) throws ServiceException;
	List<User> findUsersByRole(Role role) throws ServiceException;
	Optional<User> findUserByEmail(String email) throws ServiceException;
	Optional<User> findUserByEmailPassword(String login, String password) throws ServiceException;
	int findNumberOfPages() throws ServiceException;
	boolean addUser(Map<String, String> userData) throws ServiceException;	
	boolean activateAccount(String token,String email) throws ServiceException;	
	boolean changeUserIsBlocked(long id,boolean isBlocked) throws ServiceException;
	boolean changeUserRole(long id,Role role) throws ServiceException;
	boolean changePersonalInfo(User user,Map<String, String> userData) throws ServiceException;
}
