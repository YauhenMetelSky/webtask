package by.metelski.webtask.command.impl;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.Message;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.AppointmentDaoImpl;
import by.metelski.webtask.model.dao.impl.ProcedureDaoImpl;
import by.metelski.webtask.model.dao.impl.ScheduleDaoImpl;
import by.metelski.webtask.model.dao.impl.UserDaoImpl;
import by.metelski.webtask.model.service.AppointmentService;
import by.metelski.webtask.model.service.ProcedureService;
import by.metelski.webtask.model.service.ScheduleService;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.service.impl.AppointmentServiceImpl;
import by.metelski.webtask.model.service.impl.ProcedureServiceImpl;
import by.metelski.webtask.model.service.impl.ScheduleServiceImpl;
import by.metelski.webtask.model.service.impl.UserServiceImpl;

/**
 *  Go to changeschedule page command
 * @author Yauhen Metelski
 *
 */
public class ToChangeScheduleCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	AppointmentService service = new AppointmentServiceImpl(new AppointmentDaoImpl(), new ProcedureDaoImpl());
	UserService userService = new UserServiceImpl(new UserDaoImpl());
	ProcedureService procedureService = new ProcedureServiceImpl(new ProcedureDaoImpl());
	ScheduleService scheduleService = new ScheduleServiceImpl(new ScheduleDaoImpl());

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.DEBUG, "execute method ToChangeScheduleCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		logger.log(Level.DEBUG, "cheduleId from request:" + request.getParameter(ParameterAndAttribute.SCHEDULE_ID));
		long scheduleId = Long.parseLong(request.getParameter(ParameterAndAttribute.DOCTOR_SCHEDULE_ID));
		logger.log(Level.DEBUG, "scheduleId:" + scheduleId);
		try {
			Optional<DoctorSchedule> optional = scheduleService.findScheduleById(scheduleId);
			logger.log(Level.DEBUG, "schedule:" + optional.get());
			if (optional.isPresent()) {
				DoctorSchedule schedule = optional.get();
				request.setAttribute(ParameterAndAttribute.SCHEDULE, schedule);
				logger.log(Level.DEBUG, "doctor schedule: " + schedule);
				session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_CHANGE_SCHEDULE_PAGE);
				router.setPagePath(PagePath.CHANGE_SCHEDULE);
			} else {
				logger.log(Level.ERROR, "Nothing founded");
				request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, Message.UNKNOWN_PROBLEM);
				session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ERROR);
				router.setPagePath(PagePath.ERROR);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "AppointmentServiceException in method execute");
			request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
			request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}
