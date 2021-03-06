package by.metelski.webtask.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.Router;

/**
 * Log out command
 * @author Yauhen Metelski
 *
 */
public class LogOutCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "LogoutCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		session.invalidate();
		router.setPagePath(PagePath.TO_MAIN_PAGE);
		return router;
	}
}
