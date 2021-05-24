package by.metelski.webtask.command;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.impl.UnknownCommand;

public class CommandProvider {
	private static final Logger logger = LogManager.getLogger();

	public static Command defineCommand(HttpServletRequest request) {
		Command current = null;
		String action = request.getParameter(ParameterAndAttribute.COMMAND);
		logger.log(Level.INFO, "definded command: " + action);
		if (action == null || action.isEmpty()) {
			logger.log(Level.INFO, "empty command ");
			return new UnknownCommand();
		}
		try {
			CommandType currentType = CommandType.valueOf(action.toUpperCase());		
			current = currentType.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			logger.log(Level.ERROR, "empty command from catch ");
			current = new UnknownCommand();
		}
		return current;
	}
}
