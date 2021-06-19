package by.metelski.webtask.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.CommandAsync;
import by.metelski.webtask.command.CommandProviderAsync;
import by.metelski.webtask.command.ParameterAndAttribute;

/**
 * Ajax controller for ajax queries from client
 * @author Yauhen Metelski
 *
 */
@WebServlet(name = "ajaxcontroller", urlPatterns = { "/ajaxcontroller" })
public class AjaxController extends HttpServlet {
	private static final Logger logger = LogManager.getLogger();

	public void init() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.DEBUG, "doGet AjaxController");
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.DEBUG, "doPost AjaxController");
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.DEBUG, "processRequest");
		String commandFromPage = request.getParameter(ParameterAndAttribute.COMMAND);
		CommandAsync command = CommandProviderAsync.defineCommand(commandFromPage);
		logger.log(Level.DEBUG, "command from request: " + command);
		command.execute(request, response);
	}

	public void destroy() {
	}
}
