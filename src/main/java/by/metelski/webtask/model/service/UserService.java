package by.metelski.webtask.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.ServiceException;

public interface UserService {
	List<User> findAllUsers() throws ServiceException;

	List<User> findUsersByName(String userName) throws ServiceException;
	
	Optional<User> findUserByEmail(String email) throws ServiceException;

	Optional<User> findUserByEmailPassword(String login, String password) throws ServiceException;

	boolean addUser(Map<String, String> userData) throws ServiceException;
	
	boolean activateAccount(String token,String email) throws ServiceException;
	
	boolean blockUser(long id) throws ServiceException;

}
