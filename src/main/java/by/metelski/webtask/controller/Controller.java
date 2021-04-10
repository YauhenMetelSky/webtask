package by.metelski.webtask.controller;

import java.io.*;
import javax.servlet.http.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.CommandProvider;
import by.metelski.webtask.command.Command;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;

@WebServlet(name = "controller", urlPatterns = { "/controller" })
public class Controller extends HttpServlet {
	private static final Logger logger = LogManager.getLogger();

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
		String page;
		Command command = CommandProvider.defineCommand(request);
		page = command.execute(request);
		logger.log(Level.DEBUG, "page from command " + page);
		logger.log(Level.DEBUG, "is wrong: " + request.getAttribute("wrong"));
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	public void destroy() {
		// TODO invoke ConnectionPool.destroyPool();
	}
}
