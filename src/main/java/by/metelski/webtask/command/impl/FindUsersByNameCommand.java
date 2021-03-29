package by.metelski.webtask.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.RequestParameter;
import by.metelski.webtask.exception.UserServiceException;
import by.metelski.webtask.model.entity.User;
import by.metelski.webtask.model.service.UserServiceInterface;
import by.metelski.webtask.model.service.impl.UserService;

public class FindUsersByNameCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private UserServiceInterface userService = new UserService();

	@Override
	public String execute(HttpServletRequest request) {
		List<User> users;
		String page;
		String userName = request.getParameter(RequestParameter.USER_NAME);
		logger.log(Level.INFO, "Find by name: " + userName);
		try {
			users = userService.FindUsersByName(userName);
			if (users.size()>0) {
				page = PagePath.RESULT;
				request.setAttribute("lst", users);
			} else {
				page = PagePath.EMPTY_RESULT;
			}
		} catch (UserServiceException e) {
			logger.log(Level.ERROR, "UserServiceException in method execute");
			page = PagePath.ERROR;
		}
		return page;
	}
}
