package by.metelski.webtask.command.impl;

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
import by.metelski.webtask.command.Router.Type;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.UserDaoImpl;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.service.impl.UserServiceImpl;

/**
 * The command changes column is_active in the database, if user click the link sent by email.
 * 
 * @author Yauhen Metelski
 *
 */
public class ActivateAccountCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private UserService userService = new UserServiceImpl(new UserDaoImpl());

	@Override
	public Router execute(HttpServletRequest request) {
		Router router = new Router();
		HttpSession session = request.getSession();
		boolean isActive = false;
		logger.log(Level.DEBUG, "execute method ActivateAccountCommand");
		String email = request.getParameter(ParameterAndAttribute.USER_EMAIL);
		String token = request.getParameter(ParameterAndAttribute.TOKEN);
		try {
			isActive = userService.activateAccount(token, email);
			String page = request.getContextPath() + PagePath.TO_MAIN_PAGE;
			if (isActive) {
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.ACCOUNT_IS_ACTIVE);
				router.setPagePath(page);
				router.setType(Type.REDIRECT);
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.SUCCESSFUL);
			} else {
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.CANT_ACTIVATE);
				router.setPagePath(page);
				router.setType(Type.REDIRECT);
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.UNSUCCESSFUL);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "UserServiceException in method execute" + e);
			request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
			request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}
