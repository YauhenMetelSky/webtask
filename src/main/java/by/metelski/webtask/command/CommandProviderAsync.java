package by.metelski.webtask.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.async.UnknownCommandAsync;

/**
 * CommandProviderAsync class. For asynchronous commands.
 * @author Yauhen Metelski
 *
 */
public class CommandProviderAsync {
	private static final Logger logger = LogManager.getLogger();

	/**
	 * Defines command
	 * @param command(command name)
	 * @return {@link CommandAsync}
	 */
	public static CommandAsync defineCommand(String command) {
		CommandAsync current = null;
		logger.log(Level.INFO, "definded command: " + command);
		if (command == null || command.isEmpty()) {
			logger.log(Level.INFO, "empty command ");
			return new UnknownCommandAsync();
		}
		try {
			CommandTypeAsync currentType = CommandTypeAsync.valueOf(command.toUpperCase());
			current = currentType.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			logger.log(Level.ERROR, "empty command from catch ");
			current = new UnknownCommandAsync();
		}
		return current;
	}
}
