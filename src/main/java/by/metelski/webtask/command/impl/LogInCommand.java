package by.metelski.webtask.command.impl;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
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
		logger.log(Level.INFO, "execute method logIn");
	User user;
	String login = request.getParameter("login");
	String password = request.getParameter("password");
		Optional<User> optionalUser;
		try {
			optionalUser = userService.findUsersByLoginPassword(login, password);
			if(optionalUser.isPresent()) {
				user = optionalUser.get();
				page = PagePath.MAIN;
				request.setAttribute("user", user);
			} else {
				page = PagePath.SIGN_IN;
				request.setAttribute("wrong", "login or password");
			}		
		} catch (UserServiceException e) {
			logger.log(Level.ERROR, "UserServiceException in method execute");
			page = PagePath.ERROR;
		}		
		return page;
	}
}