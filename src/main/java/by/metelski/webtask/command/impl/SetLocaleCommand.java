package by.metelski.webtask.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;

/**
 * Set locale command
 * @author Yauhen Metelski
 *
 */
public class SetLocaleCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "SetLocaleCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
		router.setPagePath(page);
		logger.log(Level.DEBUG, "Parameter list=" + request.getParameter(ParameterAndAttribute.LIST));
		logger.log(Level.DEBUG, "Attribute list=" + request.getAttribute(ParameterAndAttribute.LIST));
		logger.log(Level.DEBUG, "language from page=" + request.getParameter(ParameterAndAttribute.LANGUAGE));
		session.setAttribute(ParameterAndAttribute.LOCALE, request.getParameter(ParameterAndAttribute.LANGUAGE));
		return router;
	}
}
