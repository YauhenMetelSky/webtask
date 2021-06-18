package by.metelski.webtask.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface for different commands
 * @author Yauhen Metelski
 *
 */

public interface Command {
	
	/**
	 * Executes command
	 * @param request{{@link HttpServletRequest}
	 * @return {@link Router}
	 */
	Router execute(HttpServletRequest request);
}
