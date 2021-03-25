package by.metelski.webtask.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.impl.EmptyCommand;

public class ActionFactory {
	private static final Logger logger = LogManager.getLogger();
	
	public static Command defineCommand(HttpServletRequest req) {
		Command current = null;
		String action = req.getParameter(RequestParameter.COMMAND);
		logger.log(Level.INFO, "definded command: " + action);
		
		if (action == null || action.isEmpty()) {
			logger.log(Level.INFO, "empty command ");
			return new EmptyCommand();
		}
		try {
			CommandType currentType = CommandType.valueOf(action.toUpperCase());
			current = currentType.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			logger.log(Level.INFO, "empty command from catch ");
			current = new EmptyCommand();
		}
		return current;
	}
}
