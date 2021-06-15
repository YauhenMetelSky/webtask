package by.metelski.webtask.command.impl;

import java.util.HashMap;
import java.util.Map;

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
import by.metelski.webtask.command.Router.Type;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.UserDaoImpl;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.service.impl.UserServiceImpl;


public class ChangePersonalInfoCommand implements Command{
	private static final Logger logger = LogManager.getLogger();
    UserService userService = new UserServiceImpl(new UserDaoImpl());
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.DEBUG, "execute method UpdateProcedureCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		Map<String,String> userData = new HashMap<>();
		User user = (User)session.getAttribute(ParameterAndAttribute.USER);
		String name = request.getParameter(ParameterAndAttribute.USER_NAME);
		logger.log(Level.DEBUG, "User name=" + name);
		String surname = request.getParameter(ParameterAndAttribute.USER_SURNAME);
		logger.log(Level.DEBUG, "User surname=" + surname);
		String phone = request.getParameter(ParameterAndAttribute.USER_PHONE);
		logger.log(Level.DEBUG, "User phone=" + phone);
		userData.put(ParameterAndAttribute.USER_NAME, name);
		userData.put(ParameterAndAttribute.USER_SURNAME, surname);
		userData.put(ParameterAndAttribute.USER_PHONE, phone);
		try {
			if(userService.changePersonalInfo(user, userData)) {
				String page = request.getContextPath() + PagePath.TO_PERSONAL_PAGE;
				router.setPagePath(page);
				router.setType(Type.REDIRECT);
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.SUCCESSFUL);
			} else {
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.UNSUCCESSFUL);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException: " + e);
			request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
			request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}

