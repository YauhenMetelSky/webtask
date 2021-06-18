package by.metelski.webtask.command.async;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.CommandAsync;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.Router;

/**
 * This type of command returns when system can't define the command
 * @author Yauhen Metelski
 *
 */
public class UnknownCommandAsync implements CommandAsync {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		Router router = new Router();
		router.setPagePath(PagePath.MAIN);
		logger.log(Level.INFO, "unknown command ");
		return router;
	}
}
