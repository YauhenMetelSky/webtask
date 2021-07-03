package by.metelski.webtask.command;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.impl.UnknownCommand;


/**
 * CommandProvider class
 * @author Yauhen Metelski
 *
 */
public class CommandProvider {
	private static final Logger logger = LogManager.getLogger();

	/**
	 * Defines command
	 * @param command(command name)
	 * @return {@link Command}
	 */
	public static Command defineCommand(String command) {
		Command current = null;
		logger.log(Level.INFO, "Command from controller: " + command);
		if (command == null || command.isEmpty()) {
			logger.log(Level.INFO, "empty command ");
			return new UnknownCommand();
		}
		try {
			CommandType currentType = CommandType.valueOf(command.toUpperCase());		
			current = currentType.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			logger.log(Level.ERROR, "empty command from catch ");
			current = new UnknownCommand();
		}
		return current;
	}
}
