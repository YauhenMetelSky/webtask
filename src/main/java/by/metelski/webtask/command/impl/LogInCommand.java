package by.metelski.webtask.command.impl;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.Message;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.UserDaoImpl;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.service.impl.UserServiceImpl;

public class LogInCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private UserService userService = new UserServiceImpl(new UserDaoImpl());

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
	    Router router = new Router();
	    HttpSession session = request.getSession();
	    logger.log(Level.DEBUG, "execute method logIn");
	    String page = request.getContextPath() + PagePath.SIGN_IN;
	    logger.log(Level.DEBUG, "page path=" + page);
		User user;
		String email = request.getParameter(ParameterAndAttribute.USER_EMAIL);
		String password = request.getParameter(ParameterAndAttribute.USER_PASSWORD);
		logger.log(Level.DEBUG, "email: " + email + " password: " + password);
		Optional<User> optionalUser;
		try {
			optionalUser = userService.findUserByEmailPassword(email, password);
			if (optionalUser.isPresent()) {
				user = optionalUser.get();
				router.setPagePath(PagePath.MAIN);
				session.setAttribute(ParameterAndAttribute.USER, user);
			} else {
				router.setPagePath(PagePath.SIGN_IN);
				request.setAttribute(ParameterAndAttribute.MESSAGE, Message.INCORRECT_EMAIL_OR_LOGIN);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "UserServiceException in method execute" + e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}