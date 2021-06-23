package by.metelski.webtask.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;

/**
 *  Go to sign up page command
 * @author Yauhen Metelski
 *
 */
public class ToSignUpCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "toSignUpCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_SIGN_UP_PAGE);
		router.setPagePath(PagePath.SIGN_UP);
		return router;
	}
}
