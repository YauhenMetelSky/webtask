package by.metelski.webtask.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.RequestParameter;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.service.impl.UserServiceImpl;

public class FindUsersByNameCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private UserService userService = new UserServiceImpl();

	@Override
	public String execute(HttpServletRequest request) {
		List<User> users;
		String page;
		String userName = request.getParameter(RequestParameter.USER_NAME);
		logger.log(Level.INFO, "Find by name: " + userName);
		try {
			users = userService.findUsersByName(userName);
			if (users.size() > 0) {
				page = PagePath.RESULT;
				request.setAttribute("lst", users);
			} else {
				page = PagePath.EMPTY_RESULT;
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "UserServiceException in method execute");
			page = PagePath.ERROR;
		}
		return page;
	}
}
