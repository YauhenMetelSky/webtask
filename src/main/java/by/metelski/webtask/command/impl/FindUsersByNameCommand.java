package by.metelski.webtask.command.impl;

import java.util.List;
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


public class FindUsersByNameCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private UserService userService = new UserServiceImpl(new UserDaoImpl());

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		List<User> users;
		Router router = new Router();
		HttpSession session = request.getSession();
		String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
		String userName = request.getParameter(ParameterAndAttribute.USER_NAME);
		logger.log(Level.INFO, "Find by name: " + userName);
		try {
			users = userService.findUsersByName(userName);
			router.setPagePath(page);			
			if (users.size() > 0) {
				request.setAttribute(ParameterAndAttribute.RESULT_LIST, users);
			} else {
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.NOTHING_FOUNDED);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "UserServiceException in method execute");
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}
