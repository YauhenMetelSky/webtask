package by.metelski.webtask.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.UserDao;
import by.metelski.webtask.model.dao.impl.UserDaoImpl;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.util.Encoder;
import by.metelski.webtask.validator.UserValidator;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger();
	private UserDao userDao = new UserDaoImpl();

	@Override
	public Optional<User> findUsersByLoginPassword(String login, String password) throws ServiceException {
		Optional<User> optionalUser = null;
		if (UserValidator.isValidLogin(login)) {
			String encodedPassword = Encoder.encodePassword(password);
			logger.log(Level.DEBUG, "Encoded password: " + encodedPassword);
			try {
				Optional<String> passwordFromDBOptional = userDao.findPasswordByLogin(login);
				if (passwordFromDBOptional.isPresent()) {
					String passwordFromDB = passwordFromDBOptional.get();
					logger.log(Level.DEBUG, "passwordFromDB: " + passwordFromDB);
					if (passwordFromDB.equals(encodedPassword)) {
						logger.log(Level.INFO, "passwords equals, authorization is successful for user: " + login);
						User user = userDao.findUserByLogin(login).get();
						optionalUser = Optional.of(user);
					} else {
						optionalUser = Optional.empty();
					}
				} else {
					optionalUser = Optional.empty();
				}
			} catch (DaoException e) {
				logger.log(Level.ERROR, "dao exception in method FindUsersByLoginPassword" + e);
				throw new ServiceException(e);
			}
		} else {
			optionalUser = Optional.empty();
		}
		return optionalUser;
	}

	@Override
	public List<User> findAllUsers() throws ServiceException {
		List<User> users = new ArrayList<>();
		try {
			users = userDao.findAll();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method FindAllUsers");
			throw new ServiceException(e);
		}
		return users;
	}

	@Override
	public List<User> findUsersByName(String userName) throws ServiceException {
		List<User> users = new ArrayList<>();
		if (UserValidator.isValidName(userName)) {
			try {
				users = userDao.findUsersByName(userName);
			} catch (DaoException e) {
				logger.log(Level.ERROR, "dao exception in method FindUsersByName" + e);
				throw new ServiceException(e);
			}
		}
		return users;
	}

	@Override
	public Optional<User> findUsersByLogin(String login) throws ServiceException {
		Optional<User> user = null;
		if (UserValidator.isValidLogin(login)) {
			try {
				user = userDao.findUserByLogin(login);
			} catch (DaoException e) {
				logger.log(Level.ERROR, "dao exception in method FindUsersByLoginPassword" + e);
				throw new ServiceException(e);
			}
		}
		return user;
	}

	@Override
	public boolean addUser(Map<String, String> userData, String password) throws ServiceException {
		String encodedPassword = Encoder.encodePassword(password);
		boolean userAdded = false;
		try {
			userAdded = userDao.addUser(userData, encodedPassword);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method addUser" + e);
			throw new ServiceException(e);
		}
		return userAdded;
	}
}
