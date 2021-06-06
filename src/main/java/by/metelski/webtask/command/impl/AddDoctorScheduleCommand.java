package by.metelski.webtask.command.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.command.Router.Type;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.ScheduleDaoImpl;
import by.metelski.webtask.model.service.ScheduleService;
import by.metelski.webtask.model.service.impl.ScheduleServiceImpl;

public class AddDoctorScheduleCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	ScheduleService service = new ScheduleServiceImpl(new ScheduleDaoImpl());

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.DEBUG, "execute method AddDoctorScheduleCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ParameterAndAttribute.USER);
		Map<String, String> scheduleData = new HashMap<>();
		scheduleData.put(ParameterAndAttribute.DOCTOR_ID, Long.toString(user.getUserId()));
		scheduleData.put(ParameterAndAttribute.SCHEDULE_ID, request.getParameter(ParameterAndAttribute.SCHEDULE_ID));
		scheduleData.put(ParameterAndAttribute.START_TIME, request.getParameter(ParameterAndAttribute.START_TIME));
		scheduleData.put(ParameterAndAttribute.END_TIME, request.getParameter(ParameterAndAttribute.END_TIME));
		scheduleData.put(ParameterAndAttribute.DATE, request.getParameter(ParameterAndAttribute.DATE));
		logger.log(Level.DEBUG, "data in map from request: " + scheduleData.toString());
		Date date = Date.valueOf(request.getParameter(ParameterAndAttribute.DATE));
		try {
			if (service.findScheduleByDateAndDoctor(date, user.getUserId()).isEmpty()) {
				if (service.addDoctorSchedule(scheduleData)) {
					String page = request.getContextPath() + PagePath.TO_PERSONAL_PAGE;
					// TODO Show message Schedule added
					router.setPagePath(page);
					router.setType(Type.REDIRECT);
				}
			} else {
				router.setPagePath(PagePath.DOCTOR);
				request.setAttribute(ParameterAndAttribute.MESSAGE, "schedule with this date already exists");
			}
		} catch (ServiceException e) {
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}
