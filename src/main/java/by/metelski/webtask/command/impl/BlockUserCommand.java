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
 * The command block user
 * 
 * @author Yauhen Metelski
 *
 */
public class BlockUserCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private UserService userService = new UserServiceImpl(new UserDaoImpl());

	@Override
	public Router execute(HttpServletRequest request) {
		Router router = new Router();
		HttpSession session = request.getSession();
		boolean isBlocked = false;
		logger.log(Level.DEBUG, "execute method BlockUserCommand");
		long id = Long.parseLong(request.getParameter(ParameterAndAttribute.USER_ID));
		try {
			String page = request.getContextPath() + PagePath.TO_PERSONAL_PAGE;
			isBlocked = userService.changeUserIsBlocked(id, true);
			if (isBlocked) {
				router.setPagePath(page);
				router.setType(Type.REDIRECT);
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.SUCCESSFUL);
			} else {
				router.setPagePath(page);
				router.setType(Type.REDIRECT);
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.UNSUCCESSFUL);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "UserServiceException in method execute BlockUserCommand" + e);
			request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
			request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}
