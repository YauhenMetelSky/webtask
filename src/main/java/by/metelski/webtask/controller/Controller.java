package by.metelski.webtask.controller;

import java.io.*;
import javax.servlet.http.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.CommandProvider;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.command.SessionAttribute;
import by.metelski.webtask.model.connection.ConnectionPool;
import by.metelski.webtask.command.Command;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;

@WebServlet(name = "controller", urlPatterns = { "/controller" })
public class Controller extends HttpServlet {
	private static final Logger logger = LogManager.getLogger();

	//FIXME when we changed language, we lost data from request find by name
	public void init() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("Hello from doGet");// TODO invoke processRequest
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Command command = CommandProvider.defineCommand(request);
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
		}
	}

	public void destroy() {
		ConnectionPool.getInstance().destroyPool();
	}
}
