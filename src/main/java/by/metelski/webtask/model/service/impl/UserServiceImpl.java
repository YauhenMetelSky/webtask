package by.metelski.webtask.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.entity.UserFactory;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.ColumnName;
import by.metelski.webtask.model.dao.UserDao;
import by.metelski.webtask.model.dao.impl.UserDaoImpl;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.util.Encoder;
import by.metelski.webtask.util.MailSender;
import by.metelski.webtask.validator.UserValidator;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger();
	private UserDao userDao = new UserDaoImpl();
	private final String COMMAND_CONFIRM = "?command=activate";
	private final String TOKEN = "&token=";
	private final String EMAIL = "&email=";
	
	@Override
	public Optional<User> findUserByEmailPassword(String email, String password) throws ServiceException {
		Optional<User> optionalUser = null;
		if (UserValidator.isValidEmail(email)) {
			String encodedPassword = Encoder.encodePassword(password);
			logger.log(Level.DEBUG, "Encoded password: " + encodedPassword);
			try {
				Optional<String> passwordFromDBOptional = userDao.findPasswordByEmail(email);
				if (passwordFromDBOptional.isPresent()) {
					String passwordFromDB = passwordFromDBOptional.get();
					logger.log(Level.DEBUG, "passwordFromDB: " + passwordFromDB);
					if (passwordFromDB.equals(encodedPassword)) {
						logger.log(Level.INFO, "passwords equals, authorization is successful for user: " + email);
						User user = userDao.findUserByEmail(email).get();
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
	public Optional<User> findUserByEmail(String email) throws ServiceException {
		Optional<User> user;
		try {
			if (UserValidator.isValidEmail(email)) {
				user=userDao.findUserByEmail(email);
			} else {
				user= Optional.empty();
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method FindUsersByLoginPassword" + e);
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public boolean addUser(Map<String, String> userData) throws ServiceException {
		String encodedPassword = Encoder.encodePassword(userData.get(ColumnName.PASSWORD));
		//TODO check user email. Must be unique.																				
		User user = UserFactory.getInstance().build(userData);
		String uniqToken = Encoder.encodePassword(userData.get(ColumnName.USER_EMAIL));
		String url = userData.get(ParameterAndAttribute.URL) + COMMAND_CONFIRM + TOKEN + uniqToken + EMAIL
				+ userData.get(ParameterAndAttribute.USER_EMAIL);// TODO url for check
		String message = "Welcome and thanks... " + url;// TODO Magic string
		boolean userAdded = false;
		try {
			userAdded = userDao.addUser(user, encodedPassword);
			MailSender.sendEmail(user.getEmail(), "Account confirmation", message);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method addUser" + e);
			throw new ServiceException(e);
		}
		return userAdded;
	}

	@Override
	public boolean activateAccount(String token, String email) throws ServiceException {
		// TODO compare token
		boolean isActive = false;
		try {
			isActive = userDao.activateAccount(email);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method activateAccount" + e);
			throw new ServiceException(e);
		}
		return isActive;
	}

	@Override
	public boolean blockUser(long id) throws ServiceException {
		boolean isBlocked =false;
		try {
			isBlocked = userDao.blockUser(id);
		}catch(DaoException e) {
			logger.log(Level.ERROR, "dao exception in method blockUser" + e);
			throw new ServiceException(e);
		}	
		return isBlocked;
	}
}
