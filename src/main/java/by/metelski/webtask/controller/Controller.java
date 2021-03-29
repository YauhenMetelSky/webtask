package by.metelski.webtask.controller;

import java.io.*;
import javax.servlet.http.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.ActionFactory;
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print("Hello from doGet");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	private void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String page = null;
		Command command = ActionFactory.defineCommand(request);
		page = command.execute(request);
		logger.log(Level.INFO, "page from command " + page);
		logger.log(Level.INFO, "attribute list:" + request.getAttribute("lst"));
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	public void destroy() {
	}
}
