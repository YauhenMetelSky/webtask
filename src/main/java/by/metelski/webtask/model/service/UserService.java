package by.metelski.webtask.model.service;

import java.util.List;
import java.util.Optional;

import by.metelski.webtask.exception.UserServiceException;
import by.metelski.webtask.model.entity.User;

public interface UserService {
	List<User>findAllUsers() throws UserServiceException;
	List<User>findUsersByName(String userName) throws UserServiceException;
	Optional<User>findUsersByLoginPassword(String login,String password) throws UserServiceException;
}
