package by.metelski.webtask.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.Command;
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
		logger.log(Level.DEBUG, "language from page=" + request.getParameter("language"));//TODO magic string
		session.setAttribute(SessionAttribute.LOCALE, request.getParameter("language"));
			return router;
	}
}
