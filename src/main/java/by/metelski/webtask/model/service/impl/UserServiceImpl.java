package by.metelski.webtask.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.Message;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.entity.User.Role;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.ColumnName;
import by.metelski.webtask.model.dao.UserDao;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.util.Encoder;
import by.metelski.webtask.util.MailSender;
import by.metelski.webtask.validator.UserValidator;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger();
	private final UserDao userDao;
	private final int numberOfUsersInPage = 10;
	private final String COMMAND_CONFIRM = "?command=activate";
	private final String TOKEN = "&token=";
	private final String EMAIL = "&email=";

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Optional<User> findUserByEmailPassword(String email, String password) throws ServiceException {
		logger.log(Level.DEBUG, "findUserByEmailPassword()");
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
						optionalUser = userDao.findUserByEmail(email);
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
//FIXME delete
//	@Override
//	public List<User> findAllUsers() throws ServiceException {
//		logger.log(Level.DEBUG, "findAllUsers()");
//		List<User> users = new ArrayList<>();
//		try {
//			users = userDao.findAll();
//		} catch (DaoException e) {
//			logger.log(Level.ERROR, "dao exception in method FindAllUsers");
//			throw new ServiceException(e);
//		}
//		return users;
//	}

	@Override
	public List<User> findUsersByName(String userName) throws ServiceException {
		logger.log(Level.DEBUG, "findUsersByName(), userName:" + userName);
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
	public List<User> findUsersBySurname(String userSurname) throws ServiceException {
		logger.log(Level.DEBUG, "findUsersBySurname(), user surname:" + userSurname);
		List<User> users = new ArrayList<>();
		if (UserValidator.isValidName(userSurname)) {
			try {
				users = userDao.findUsersBySurname(userSurname);
			} catch (DaoException e) {
				logger.log(Level.ERROR, "dao exception in method FindUsersBySurname" + e);
				throw new ServiceException(e);
			}
		}
		return users;
	}

	@Override
	public List<User> findUsersByRole(Role role) throws ServiceException {
		logger.log(Level.DEBUG, "findUsersByRole(), role:" + role);
		List<User> users = new ArrayList<>();
		try {
			users = userDao.findUsersByRole(role);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method FindUsersByRole" + e);
			throw new ServiceException(e);
		}

		return users;
	}

	@Override
	public Optional<User> findUserByEmail(String email) throws ServiceException {
		logger.log(Level.DEBUG, "findUsersByEmail(), email:" + email);
		Optional<User> user;
		try {
			if (UserValidator.isValidEmail(email)) {
				user = userDao.findUserByEmail(email);
			} else {
				user = Optional.empty();
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method FindUsersByLoginPassword" + e);
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public boolean addUser(Map<String, String> userData) throws ServiceException {
		logger.log(Level.DEBUG, "addUser(), userData:" + userData);
		boolean userAdded;
		if (UserValidator.isValidPassword(userData.get(ColumnName.PASSWORD))
				&& UserValidator.isValidName(userData.get(ParameterAndAttribute.USER_NAME))
				&& UserValidator.isValidEmail(userData.get(ParameterAndAttribute.USER_EMAIL))) {
			String encodedPassword = Encoder.encodePassword(userData.get(ColumnName.PASSWORD));
			User user = new User.Builder().setName(userData.get(ParameterAndAttribute.USER_NAME))
					.setSurname(userData.get(ParameterAndAttribute.USER_SURNAME))
					.setEmail(userData.get(ParameterAndAttribute.USER_EMAIL))
					.setPhone(userData.get(ParameterAndAttribute.USER_PHONE)).build();
			String uniqToken = Encoder.encodePassword(userData.get(ColumnName.USER_EMAIL));
			String url = userData.get(ParameterAndAttribute.URL) + COMMAND_CONFIRM + TOKEN + uniqToken + EMAIL
					+ userData.get(ParameterAndAttribute.USER_EMAIL);
			String message = Message.WELCOM + url;
			try {
				userAdded = userDao.addUser(user, encodedPassword);
				MailSender.sendEmail(user.getEmail(), Message.ACCOUNT_CONFIRMATION, message);
			} catch (DaoException e) {
				logger.log(Level.ERROR, "dao exception in method addUser" + e);
				throw new ServiceException(e);
			}
		} else {
			userAdded = false;
		}
		return userAdded;
	}

	@Override
	public boolean activateAccount(String token, String email) throws ServiceException {
		logger.log(Level.DEBUG, "activateAccount(), email:" + email);
		String uniqToken = Encoder.encodePassword(email);
		boolean isActive = false;
		if (UserValidator.isValidEmail(email)) {
			if (uniqToken.equals(token)) {
				try {
					isActive = userDao.activateAccount(email);
				} catch (DaoException e) {
					logger.log(Level.ERROR, "dao exception in method activateAccount" + e);
					throw new ServiceException(e);
				}
			}
		}
		return isActive;
	}

	@Override
	public boolean changeUserIsBlocked(long id, boolean isBlocked) throws ServiceException {
		logger.log(Level.DEBUG, "blockUser(), id:" + id);
		boolean changeStatusResult = false;
		try {
			changeStatusResult = userDao.changeIsBlockedStatus(id, isBlocked);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method blockUser" + e);
			throw new ServiceException(e);
		}
		return changeStatusResult;
	}

	@Override
	public boolean changeUserRole(long id, Role role) throws ServiceException {
		logger.log(Level.DEBUG, "changeUserRole, id:" + id + ", new role:" + role);
		boolean isChanged = false;
		try {
			isChanged = userDao.changeUserRole(id, role);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method changeUserRole" + e);
			throw new ServiceException(e);
		}
		return isChanged;
	}

	@Override
	public boolean changePersonalInfo(User user, Map<String, String> userData) throws ServiceException {
		logger.log(Level.DEBUG, "Change personal info user:" + user);
		boolean isChanged = false;
		if (UserValidator.isValidName(userData.get(ParameterAndAttribute.USER_NAME))
				&& UserValidator.isValidName(userData.get(ParameterAndAttribute.USER_SURNAME))) {
			user.setName(userData.get(ParameterAndAttribute.USER_NAME));
			user.setSurname(userData.get(ParameterAndAttribute.USER_SURNAME));
			user.setPhone(userData.get(ParameterAndAttribute.USER_PHONE));
			try {
				isChanged = userDao.changePersonalInfo(user);
			} catch (DaoException e) {
				logger.log(Level.ERROR, "dao exception in method changePersonalInfo" + e);
				throw new ServiceException(e);
			}
		}
		return isChanged;
	}

	@Override
	public int findNumberOfPages() throws ServiceException {
		logger.log(Level.DEBUG, "FindNumberOfPages:");
		int numberOfPages;
		int numberOfUser;
		try {
			numberOfUser = userDao.findNumberOfRows();
			if (numberOfUser > numberOfUsersInPage) {
				numberOfPages = (int) Math.ceil((double) numberOfUser / numberOfUsersInPage);
			} else {
				numberOfPages = 1;
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method findNumberOfRows()" + e);
			throw new ServiceException(e);
		}
		return numberOfPages;
	}

	@Override
	public List<User> findUsersFromRow(int pageNumber) throws ServiceException {
		logger.log(Level.DEBUG, "findUsersFromRow(), page number:" + pageNumber);
		List<User> users = new ArrayList<>();
		int fromRow;
		if (pageNumber > 1) {
			fromRow = (pageNumber - 1) * numberOfUsersInPage;
		} else {
			fromRow = 0;
		}
		try {
			users = userDao.findUsersFromRow(fromRow, numberOfUsersInPage);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method findUsersFromRow" + e);
			throw new ServiceException(e);
		}
		return users;
	}
}
