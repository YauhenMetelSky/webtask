package by.metelski.webtask.command.impl;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.RequestAttribute;
import by.metelski.webtask.command.RequestParameter;
import by.metelski.webtask.exception.UserServiceException;
import by.metelski.webtask.model.entity.User;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.service.impl.UserServiceImpl;

public class LogInCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private UserService userService = new UserServiceImpl();

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		logger.log(Level.DEBUG, "execute method logIn");
		User user;
		String login = request.getParameter(RequestParameter.USER_LOGIN);
		String password = request.getParameter(RequestParameter.USER_PASSWORD);
		logger.log(Level.DEBUG, "login: " + login + " password: " + password);
		Optional<User> optionalUser;
		try {
			optionalUser = userService.findUsersByLoginPassword(login, password);
			if (optionalUser.isPresent()) {
				user = optionalUser.get();
				page = PagePath.ADMIN;
				request.setAttribute(RequestAttribute.USER, user);// TODO attribute class?
			} else {
				page = PagePath.SIGN_IN;
				request.setAttribute(RequestAttribute.MESSAGE, "incorrect login or password неверно");//TODO remove Неверно
			}
		} catch (UserServiceException e) {
			logger.log(Level.ERROR, "UserServiceException in method execute" + e);
			page = PagePath.ERROR;
		}
		return page;
	}
}