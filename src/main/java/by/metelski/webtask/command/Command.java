package by.metelski.webtask.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	Router execute(HttpServletRequest request, HttpServletResponse response);
}
