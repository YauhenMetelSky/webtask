package by.metelski.webtask.command.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.RequestAttribute;
import by.metelski.webtask.command.RequestParameter;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.command.SessionAttribute;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.service.impl.UserServiceImpl;

public class FindUserByEmailCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private UserService userService = new UserServiceImpl();

	@Override
	public Router execute(HttpServletRequest request) {
		Optional<User> user;
		Router router = new Router();
		HttpSession session = request.getSession();
		String page = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
		String email = request.getParameter(RequestParameter.USER_EMAIL);
		logger.log(Level.INFO, "Find by email: " + email);
		try {
			user = userService.findUserByEmail(email);
			if (user.isPresent()) {
				router.setPagePath(page);
				request.setAttribute(RequestAttribute.FINDED_USER, user);
			} else {
				router.setPagePath(page);
				request.setAttribute(RequestAttribute.MESSAGE, "Can't find such user");
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "UserServiceException in method execute");
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}