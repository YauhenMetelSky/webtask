package by.metelski.webtask.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.model.entity.User;
import by.metelski.webtask.model.service.UserServiceInterface;
import by.metelski.webtask.model.service.impl.UserService;

public class ShowAllUsersCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private UserServiceInterface userService = new UserService();

	@Override
	public String execute(HttpServletRequest request) {
		logger.log(Level.INFO, "Show all users.");
		List<User> users;
		users = userService.FindAllUsers();
		// TODO  try-catch bloc???
		String page = PagePath.RESULT;
		request.setAttribute("lst", users);
		return page;
	}
}