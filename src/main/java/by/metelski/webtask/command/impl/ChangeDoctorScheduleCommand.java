package by.metelski.webtask.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.service.impl.UserServiceImpl;

public class ChangeDoctorScheduleCommand implements Command{
	private static final Logger logger = LogManager.getLogger();
    UserService userService = new UserServiceImpl();
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.DEBUG, "execute method ChangeDoctorScheduleCommand");
		Router router = new Router();
		//FIXME realization
			return router;
	}
}