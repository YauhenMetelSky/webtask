package by.metelski.webtask.model.service;

import java.util.List;
import java.util.Optional;

import by.metelski.webtask.exception.UserServiceException;
import by.metelski.webtask.model.entity.User;

public interface UserServiceInterface {
	List<User>FindAllUsers() throws UserServiceException;
	List<User>FindUsersByName(String userName) throws UserServiceException;
	Optional<User>FindUsersByLoginPassword(String login,String password) throws UserServiceException;
}
