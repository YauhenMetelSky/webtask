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
import by.metelski.webtask.command.Router;
import by.metelski.webtask.command.Router.Type;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.service.impl.UserServiceImpl;

public class SignUpCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private UserService userService = new UserServiceImpl();

	@Override
	public Router execute(HttpServletRequest request) {
		Router router = new Router();
		Map<String, String> userData = new HashMap<>();
		logger.log(Level.DEBUG, "execute method SignUp");
		String login = request.getParameter(RequestParameter.USER_LOGIN);
		String name = request.getParameter(RequestParameter.USER_NAME);
		String surname = request.getParameter(RequestParameter.USER_SURNAME);
		String email = request.getParameter(RequestParameter.USER_EMAIL);
		String phone = request.getParameter(RequestParameter.USER_PHONE);
		String password = request.getParameter(RequestParameter.USER_PASSWORD);
		String confirmedPassword = request.getParameter(RequestParameter.USER_CONFIRMED_PASSWORD);
		userData.put(RequestParameter.USER_LOGIN, login);
		userData.put(RequestParameter.USER_NAME, name);
		userData.put(RequestParameter.USER_SURNAME, surname);
		userData.put(RequestParameter.USER_EMAIL, email);
		userData.put(RequestParameter.USER_PHONE, phone);
		userData.put(RequestParameter.USER_PASSWORD, password);
		userData.put(RequestParameter.URL, request.getRequestURL().toString());
		if (password.equals(confirmedPassword)) {
			try {
				if (userService.findUsersByLogin(login).isEmpty()) {
					if (userService.addUser(userData)) {
						String page = request.getContextPath();
						router.setPagePath(page);
						router.setType(Type.REDIRECT);
						request.setAttribute(RequestAttribute.MESSAGE, "User created");
					} else {
						router.setPagePath(PagePath.ERROR);// TODO need to do smth
					}
				} else {
					router.setPagePath(PagePath.SIGN_UP);
					// TODO messages in separate file
					request.setAttribute(RequestAttribute.MESSAGE, "user with that login already exists");
				}
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "UserServiceException in method execute SignUpCommand" + e);
				router.setPagePath(PagePath.ERROR);
			}
		} else {
			router.setPagePath(PagePath.SIGN_UP);
			// TODO messages in separate file
			request.setAttribute(RequestAttribute.MESSAGE, "password and confirmed password do not match");
		}
		return router;
	}
}
