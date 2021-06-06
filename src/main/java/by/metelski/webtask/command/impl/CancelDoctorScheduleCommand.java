package by.metelski.webtask.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.command.Router.Type;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.ScheduleDaoImpl;
import by.metelski.webtask.model.service.ScheduleService;
import by.metelski.webtask.model.service.impl.ScheduleServiceImpl;

public class CancelDoctorScheduleCommand  implements Command {
	private static final Logger logger = LogManager.getLogger();
	private ScheduleService scheduleService = new ScheduleServiceImpl(new ScheduleDaoImpl());

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.DEBUG, "CancelDoctorScheduleCommand");
		Router router = new Router();
		long scheduleId =Long.parseLong(request.getParameter(ParameterAndAttribute.DOCTOR_SCHEDULE_ID));
		try {
			if(scheduleService.changeFieldIsActive(scheduleId, false)) {
			String page = request.getContextPath() + PagePath.TO_PERSONAL_PAGE;
			router.setPagePath(page);
			router.setType(Type.REDIRECT);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ScheduleServiceException in method execute",e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}
