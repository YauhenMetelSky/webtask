package by.metelski.webtask.model.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.exception.UserServiceException;
import by.metelski.webtask.model.dao.BaseDao;
import by.metelski.webtask.model.dao.impl.UserDao;
import by.metelski.webtask.model.entity.User;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.validator.UserValidator;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger();
	BaseDao userDao = new UserDao();

	@Override
	public List<User> findAllUsers() throws UserServiceException {
		List<User> users = new ArrayList<>();
		try {
			users = userDao.findAllUsers();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method FindAllUsers");
			throw new UserServiceException(e);
		}
		return users;
	}

	@Override
	public List<User> findUsersByName(String userName) throws UserServiceException {
		List<User> users = new ArrayList<>();
		if (UserValidator.isValidName(userName)) {
			try {
				users = userDao.findUsersByName(userName);
			} catch (DaoException e) {
				logger.log(Level.ERROR, "dao exception in method FindUsersByName");
				throw new UserServiceException(e);
			}
		}
		return users;
	}

	@Override
	public Optional<User> findUsersByLoginPassword(String login, String password) throws UserServiceException {
		Optional<User> optionalUser = null;
		if(UserValidator.isValidEmail(login)) {
		Base64.Encoder encoder = Base64.getEncoder();
		byte[] bytesEncoded = encoder.encode(password.getBytes());
		BigInteger bigInt = new BigInteger(1, bytesEncoded);
		String resultHex = bigInt.toString(16);
		logger.log(Level.INFO, "Encoded password: " + resultHex);
		try {
			Optional<String> passwordFromDBOptional = userDao.findPasswordByLogin(login);
			if (passwordFromDBOptional.isPresent()) {
				String passwordFromDB = passwordFromDBOptional.get();
				logger.log(Level.INFO, "passwordFromDB: " + passwordFromDB);
				if (passwordFromDB.equals(resultHex)) {
					logger.log(Level.INFO, "passwords equals");
					User user = userDao.findUserByLogin(login).get();
					optionalUser = Optional.of(user);
				} else {
					optionalUser = Optional.empty();
				}
			} else {
				optionalUser = Optional.empty();
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method FindUsersByLoginPassword");
			throw new UserServiceException(e);
		}
		} else {
			optionalUser = Optional.empty();
		}
		return optionalUser;
	}
}
