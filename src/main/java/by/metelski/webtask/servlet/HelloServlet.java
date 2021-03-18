package by.metelski.webtask.servlet;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;
import by.metelski.webtask.entity.Message;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", urlPatterns = { "/helloservlet" })
public class HelloServlet extends HttpServlet {

	public void init() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print("Hello from doGet");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String numStr = req.getParameter("number");
		int num = Integer.parseInt(numStr);
		num *= 2;
		req.setAttribute("numRes", num);
		List<Message> list = new ArrayList<Message>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root","Gfhjkmr,l"); 
			Statement statement = connection.createStatement()) {
			String sql = "SELECT message_id, text FROM messages";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String text = resultSet.getString("text");
				list.add(new Message(id, text));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("lst", list);
		req.getRequestDispatcher("/pages/main.jsp").forward(req, resp);
	}
	public void destroy() {
	}
}
