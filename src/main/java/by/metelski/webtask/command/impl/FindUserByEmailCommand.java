package by.metelski.webtask.command.impl;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
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

/**
 * The command find user by email
 * @author Yauhen Metelski
 *
 */
public class FindUserByEmailCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private UserService userService = new UserServiceImpl(new UserDaoImpl());

	@Override
	public Router execute(HttpServletRequest request) {
		Optional<User> user;
		Router router = new Router();
		HttpSession session = request.getSession();
		String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
		String email = request.getParameter(ParameterAndAttribute.USER_EMAIL);
		logger.log(Level.INFO, "Find by email: " + email);
		try {
			user = userService.findUserByEmail(email);
			router.setPagePath(page);
			if (user.isPresent()) {
				request.setAttribute(ParameterAndAttribute.FINDED_USER, user);
			} else {
				request.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.NOTHING_FOUNDED);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "UserServiceException in method execute");
			request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
			request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}
