package by.metelski.webtask.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

public class ActivateAccountCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private UserService userService = new UserServiceImpl();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
	    Router router = new Router();
	    boolean isActive = false;
	    logger.log(Level.DEBUG, "execute method ActivateAccountCommand");
		String email = request.getParameter(ParameterAndAttribute.USER_EMAIL);
		String token = request.getParameter(ParameterAndAttribute.TOKEN);
		logger.log(Level.DEBUG, "email: " + email + " token: " + token);
		try {
			isActive = userService.activateAccount(token,email);
			if (isActive) {
				router.setPagePath(PagePath.MAIN);
				router.setType(Type.REDIRECT);
				//TODO session attribute message successful activation
			} else {
				router.setPagePath(PagePath.MAIN);
				router.setType(Type.REDIRECT);
				//TODO session attribute message try to activate your account later"
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "UserServiceException in method execute" + e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}
