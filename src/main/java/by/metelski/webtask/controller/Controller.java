package by.metelski.webtask.controller;

import java.io.*;
import javax.servlet.http.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.CommandProvider;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.model.connection.ConnectionPool;
import by.metelski.webtask.command.Command;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;

/**
 * Controller for queries from client
 * @author Yauhen Metelski
 *
 */
@WebServlet(name = "controller", urlPatterns = { "/controller" })
public class Controller extends HttpServlet {
	private static final Logger logger = LogManager.getLogger();

	public void init() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandFromPage = request.getParameter(ParameterAndAttribute.COMMAND);
		Command command = CommandProvider.defineCommand(commandFromPage);
		Router router = command.execute(request);
		logger.log(Level.DEBUG, "page from command " + router.getPagePath());
		logger.log(Level.DEBUG, "is wrong: " + request.getAttribute("wrong"));
		switch (router.getType()) {
		case FORWARD:
			logger.log(Level.DEBUG, "forward");
			RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
			dispatcher.forward(request, response);
			break;
		case REDIRECT:
			response.sendRedirect(router.getPagePath());
			logger.log(Level.DEBUG, "redirect");
			break;
		default:
			logger.log(Level.ERROR, "Incorrect router type:" + router.getType());
			response.sendRedirect(PagePath.MAIN);
		}
	}

	public void destroy() {
		ConnectionPool.getInstance().destroyPool();
	}
}
