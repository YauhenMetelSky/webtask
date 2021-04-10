package by.metelski.webtask.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.ServiceException;

public interface UserService {
	List<User> findAllUsers() throws ServiceException;

	List<User> findUsersByName(String userName) throws ServiceException;

	Optional<User> findUsersByLogin(String login) throws ServiceException;

	Optional<User> findUsersByLoginPassword(String login, String password) throws ServiceException;

	boolean addUser(Map<String, String> userData, String password) throws ServiceException;
}
