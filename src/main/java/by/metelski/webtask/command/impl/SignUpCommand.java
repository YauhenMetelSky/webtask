package by.metelski.webtask.command.impl;

import java.util.HashMap;
import java.util.Map;
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
 * Sign up command
 * @author Yauhen Metelski
 *
 */
public class SignUpCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private UserService userService = new UserServiceImpl(new UserDaoImpl());

	@Override
	public Router execute(HttpServletRequest request) {
		Router router = new Router();
		HttpSession session = request.getSession();
		Map<String, String> userData = new HashMap<>();
		logger.log(Level.DEBUG, "execute method SignUp");
		String name = request.getParameter(ParameterAndAttribute.USER_NAME);
		String surname = request.getParameter(ParameterAndAttribute.USER_SURNAME);
		String email = request.getParameter(ParameterAndAttribute.USER_EMAIL);
		String phone = request.getParameter(ParameterAndAttribute.USER_PHONE);
		String password = request.getParameter(ParameterAndAttribute.USER_PASSWORD);
		String confirmedPassword = request.getParameter(ParameterAndAttribute.USER_CONFIRMED_PASSWORD);
		userData.put(ParameterAndAttribute.USER_NAME, name);
		userData.put(ParameterAndAttribute.USER_SURNAME, surname);
		userData.put(ParameterAndAttribute.USER_EMAIL, email);
		userData.put(ParameterAndAttribute.USER_PHONE, phone);
		userData.put(ParameterAndAttribute.USER_PASSWORD, password);
		userData.put(ParameterAndAttribute.URL, request.getRequestURL().toString());
		if (password.equals(confirmedPassword)) {
			try {
				if (userService.findUserByEmail(email).isEmpty()) {
					if (userService.addUser(userData)) {
						String page = request.getContextPath();
						router.setPagePath(page);
						router.setType(Type.REDIRECT);
						session.setAttribute(ParameterAndAttribute.CHECK_EMAIL, Message.EMAIL_ACTIVATE);
					} else {
						router.setPagePath(PagePath.SIGN_UP);
						request.setAttribute(ParameterAndAttribute.MESSAGE, Message.CANT_ADD_USER);
					}
				} else {
					router.setPagePath(PagePath.SIGN_UP);
					request.setAttribute(ParameterAndAttribute.MESSAGE, Message.USER_ALREADY_EXISTS);
				}
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "UserServiceException in method execute SignUpCommand" + e);
				request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
				request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
				router.setPagePath(PagePath.ERROR);
			}
		} else {
			router.setPagePath(PagePath.SIGN_UP);
			request.setAttribute(ParameterAndAttribute.MESSAGE, "password and confirmed password do not match");
		}
		return router;
	}
}
