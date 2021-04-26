package by.metelski.webtask.command.impl;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.RequestAttribute;
import by.metelski.webtask.command.RequestParameter;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.command.SessionAttribute;

public class SetLocaleCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private static final String RU="ru_RU";
	private static final String EN="en_US";

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "SetLocaleCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		String page = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
		router.setPagePath(page);
//		Map<String,String[]>parameters=request.getParameterMap();
//		Enumeration names=request.getAttributeNames();
//		while(names.hasMoreElements()) {
//			
//		}
//		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
//			logger.log(Level.DEBUG, "parameter " +entry.getKey() +", value " +Arrays.toString(entry.getValue()));           
//	    }
		logger.log(Level.DEBUG, "Parameter list=" + request.getParameter(RequestAttribute.LIST));
		logger.log(Level.DEBUG, "Attribute list=" + request.getAttribute(RequestAttribute.LIST));
		logger.log(Level.DEBUG, "language from page=" + request.getParameter(RequestParameter.LANGUAGE));
		session.setAttribute(SessionAttribute.LOCALE, request.getParameter(RequestParameter.LANGUAGE));
			return router;
	}
}
