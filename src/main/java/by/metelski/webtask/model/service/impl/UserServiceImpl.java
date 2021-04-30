package by.metelski.webtask.model.service.impl;

import java.util.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.entity.User;
import by.metelski.webtask.entity.UserBuilder;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.ColumnName;
import by.metelski.webtask.model.dao.UserDao;
import by.metelski.webtask.model.dao.impl.UserDaoImpl;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.util.Encoder;
import by.metelski.webtask.validator.UserValidator;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger();
	private final UserDao userDao = new UserDaoImpl();

	//Move validate credentials to level up (@LoginCommand)
	//Do not create god method and god class))) service must be a service
	@Override
	public Optional<User> findUsersByLoginPassword(final String login, final String password) throws ServiceException {
		if (UserValidator.isValidLogin(login)) {
			final String encodedPassword = Encoder.encodePassword(password);
			logger.log(Level.DEBUG, "Encoded password: " + encodedPassword);
			try {
				final Optional<String> passwordFromDBOptional = userDao.findPasswordByLogin(login);
				if (passwordFromDBOptional.isPresent()) {
					final String passwordFromDB = passwordFromDBOptional.get();
					logger.log(Level.DEBUG, "passwordFromDB: " + passwordFromDB);
					if (passwordFromDB.equals(encodedPassword)) {
						logger.log(Level.INFO, "passwords equals, authorization is successful for user: " + login);
						return userDao.findUserByLogin(login);
					} else {
						return Optional.empty();
					}
				} else {
					return Optional.empty();
				}
			} catch (final DaoException e) {
				logger.log(Level.ERROR, "dao exception in method FindUsersByLoginPassword" + e);
				throw new ServiceException(e);
			}
		}
		return Optional.empty();
	}

	@Override
	public List<User> findAllUsers() throws ServiceException {
		try {
			return userDao.findAll();
		} catch (final DaoException e) {
			logger.log(Level.ERROR, "dao exception in method FindAllUsers");
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> findUsersByName(final String userName) throws ServiceException {
		try {
			if (UserValidator.isValidName(userName)) {
				return userDao.findUsersByName(userName);
			}
			return Collections.emptyList();
		} catch (final DaoException e) {
			logger.log(Level.ERROR, "dao exception in method FindUsersByName" + e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<User> findUsersByLogin(final String login) throws ServiceException {
		try {
			if (UserValidator.isValidLogin(login)) {
				return userDao.findUserByLogin(login);
			}
			return Optional.empty();
		} catch (final DaoException e) {
			logger.log(Level.ERROR, "dao exception in method FindUsersByLoginPassword" + e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<User> findUserByEmail(final String email) throws ServiceException {
		try {
			return userDao.findUserByEmail(email);
		} catch (final DaoException e) {
			logger.log(Level.ERROR, "dao exception in method FindUsersByLoginPassword" + e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean addUser(final Map<String, String> userData) throws ServiceException {
		// Also move to level up. Just save user. rename method like as saveUser.
		final String encodedPassword = Encoder.encodePassword(userData.get(ColumnName.PASSWORD));//TODO Delete password, use only map, create user
		final User user= UserBuilder.getInstance().build(userData);
		try {
			return userDao.addUser(user, encodedPassword);
			//FIXME
			//remove to level up  - SERVICE MUST NOT KNOW ABOUT MAIL SENDER
			// MailSender.sendEmail(user.getEmail(),"Account confirmation",message);
		} catch (final DaoException e) {
			logger.log(Level.ERROR, "dao exception in method addUser" + e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean activateAccount(String token,String email) throws ServiceException {
		//TODO compare token
		try {
			return userDao.activateAccount(email);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method activateAccount" + e);
			throw new ServiceException(e);
		}
	}
}
