package by.metelski.webtask.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interface for asynchronous commands
 * @author Yauhen Metelski
 *
 */
public interface CommandAsync {
	
	/**
	 * @param request{{@link HttpServletRequest}
	 * @param response{{@link HttpServletResponse}
	 * @return {@link Router}
	 */
	Router execute(HttpServletRequest request, HttpServletResponse response);
}
