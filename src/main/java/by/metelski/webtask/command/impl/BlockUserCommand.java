package by.metelski.webtask.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.command.Router.Type;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.service.impl.UserServiceImpl;

public class BlockUserCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private UserService userService = new UserServiceImpl();

	@Override
	public Router execute(HttpServletRequest request) {
	    Router router = new Router();
	    boolean isBlocked = false;
	    logger.log(Level.DEBUG, "execute method BlockUserCommand");
		long id = Long.parseLong(request.getParameter(ParameterAndAttribute.USER_ID));		
		try {
			isBlocked = userService.blockUser(id);
			if (isBlocked) {
				router.setPagePath(PagePath.MAIN);
				router.setType(Type.REDIRECT);
				//TODO session attribute message user blocked
			} else {
				router.setPagePath(PagePath.MAIN);
				router.setType(Type.REDIRECT);
				//TODO session attribute message not blocked"
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "UserServiceException in method execute BlockUserCommand" + e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}
