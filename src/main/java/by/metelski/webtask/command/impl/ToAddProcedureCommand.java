package by.metelski.webtask.command.impl;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.Router;

/**
 *  Go to addprocedure page command
 * @author Yauhen Metelski
 *
 */
public class ToAddProcedureCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "ToAddProcedureCommand");
		Router router = new Router();
		router.setPagePath(PagePath.ADD_PROCEDURE);
		return router;
	}
}
