package by.metelski.webtask.model.service;

import java.util.List;

import by.metelski.webtask.model.entity.User;

public interface UserServiceInterface {
	List<User>FindAllUsers();
	List<User>FindUsersByName(String userName);
}
