package by.metelski.webtask.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.entity.Schedule;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.ScheduleDaoImpl;
import by.metelski.webtask.model.service.ScheduleService;
import by.metelski.webtask.model.service.impl.ScheduleServiceImpl;

public class ToPersonalPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private ScheduleService service =new ScheduleServiceImpl(new ScheduleDaoImpl());

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "ToPersonalPageCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(ParameterAndAttribute.USER);
		if(user!= null) {
			//TODO switch role blocked confirmed
			switch(user.getRole()) {
			case ADMIN:
				router.setPagePath(PagePath.ADMIN);
				break;
			case GUEST:
				router.setPagePath(PagePath.SIGN_IN);
				break;
			case USER:
				router.setPagePath(PagePath.USER);
			break;
			case DOCTOR:
				logger.log(Level.DEBUG, "case doctor");
				try {
					List<Schedule> schedules = service.findAllActiveSchedules();
					request.setAttribute(ParameterAndAttribute.SCHEDULE_LIST, schedules);
					logger.log(Level.DEBUG, "shedules: " + schedules);
				} catch (ServiceException e) {
					logger.log(Level.ERROR, "ScheduleServiceException in method execute" + e);
					router.setPagePath(PagePath.ERROR);
				}
				router.setPagePath(PagePath.DOCTOR);
				break;
			}
		}else {
			router.setPagePath(PagePath.SIGN_IN);
		}
		return router;
	}
}
