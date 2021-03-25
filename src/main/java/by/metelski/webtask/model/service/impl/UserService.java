package by.metelski.webtask.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.model.dao.BaseDao;
import by.metelski.webtask.model.dao.impl.UserDao;
import by.metelski.webtask.model.entity.User;
import by.metelski.webtask.model.service.UserServiceInterface;

public class UserService implements UserServiceInterface {
	BaseDao userDao = new UserDao();

	@Override
	public List<User> FindAllUsers() {
		List<User> users = new ArrayList<>();
		try {
			users = userDao.FindAllUsers();
		} catch (DaoException e) {
					e.printStackTrace();
		}
		return users;
	}

	@Override
	public List<User> FindUsersByName(String userName) {
		List<User> users = new ArrayList<>();
			try {
			users = userDao.FindUsersByName(userName);
		} catch (DaoException e) {
			
			e.printStackTrace();
		}
		return users;
	}
}
