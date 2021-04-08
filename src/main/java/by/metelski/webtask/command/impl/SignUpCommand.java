package by.metelski.webtask.command.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.RequestAttribute;
import by.metelski.webtask.command.RequestParameter;
import by.metelski.webtask.exception.UserServiceException;
import by.metelski.webtask.model.dao.ColumnName;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.service.impl.UserServiceImpl;

public class SignUpCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private UserService userService = new UserServiceImpl();

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		Map<String, String> userData = new HashMap<>();
		logger.log(Level.DEBUG, "execute method SignUp");
		// TODO realization
		String login = request.getParameter(RequestParameter.USER_LOGIN);
		String name = request.getParameter(RequestParameter.USER_NAME);
		String surname = request.getParameter(RequestParameter.USER_SURNAME);
		String email = request.getParameter(RequestParameter.USER_EMAIL);
		String phone = request.getParameter(RequestParameter.USER_PHONE);
		String password = request.getParameter(RequestParameter.USER_PASSWORD);
		String confirmedPassword = request.getParameter(RequestParameter.USER_CONFIRMED_PASSWORD);
		userData.put(ColumnName.USER_LOGIN, login);
		userData.put(ColumnName.USER_NAME, name);
		userData.put(ColumnName.USER_SURNAME, surname);
		userData.put(ColumnName.USER_EMAIL, email);
		userData.put(ColumnName.USER_PHONE, phone);
		if (password.equals(confirmedPassword)) {
			try {
				if (userService.findUsersByLogin(login).isEmpty()) {
					if (userService.addUser(userData, password)) {
						page = PagePath.SIGN_IN;// TODO or it can be main page
						request.setAttribute(RequestAttribute.MESSAGE, "User created");
					} else {
						page = PagePath.ERROR;// TODO need to do smth
					}
				} else {
					page = PagePath.SIGN_UP;
					// TODO messages in separate file
					request.setAttribute(RequestAttribute.MESSAGE, "user with that login already exists");
				}
			} catch (UserServiceException e) {
				logger.log(Level.ERROR, "UserServiceException in method execute SignUpCommand" + e);
				page = PagePath.ERROR;
			}
		} else {
			page = PagePath.SIGN_UP;
			// TODO messages in separate file
			request.setAttribute(RequestAttribute.MESSAGE, "password and confirmed password do not match");
		}
		return page;
	}
}
