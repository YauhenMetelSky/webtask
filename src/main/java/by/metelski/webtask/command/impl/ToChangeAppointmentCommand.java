package by.metelski.webtask.command.impl;

import java.util.List;
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
import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.entity.User.Role;
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
import by.metelski.webtask.util.IntervalCalculator;

/**
 *  Go to changeapp page command
 * @author Yauhen Metelski
 *
 */
public class ToChangeAppointmentCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final int intervalIncrement = 15;
	AppointmentService service = new AppointmentServiceImpl(new AppointmentDaoImpl(), new ProcedureDaoImpl());
	UserService userService = new UserServiceImpl(new UserDaoImpl());
	ProcedureService procedureService = new ProcedureServiceImpl(new ProcedureDaoImpl());
	ScheduleService scheduleService = new ScheduleServiceImpl(new ScheduleDaoImpl());

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.DEBUG, "execute method ToChangeAppointmentCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		logger.log(Level.DEBUG,
				"appointmentId from request:" + request.getParameter(ParameterAndAttribute.APPOINTMENT_ID));
		long appointmentId = Long.parseLong(request.getParameter(ParameterAndAttribute.APPOINTMENT_ID));
		logger.log(Level.DEBUG, "appointmentId:" + appointmentId);
		try {
			Optional<Appointment> optional = service.findById(appointmentId);
			logger.log(Level.DEBUG, "appointment:" + optional.get());
			if (optional.isPresent()) {
				Appointment appointment = optional.get();
				DoctorSchedule schedule = scheduleService
						.findScheduleByDateAndDoctor(appointment.getDate(), appointment.getDoctor().getUserId()).get();
				request.setAttribute(ParameterAndAttribute.SCHEDULE_ID, schedule.getId());
				List<Appointment> appointments = service.findAllByDoctorIdAndDate(appointment.getDoctor().getUserId(),
						appointment.getDate());
				List<String> intervals = IntervalCalculator.calculateIntervals(schedule, intervalIncrement,
						appointments);
				request.setAttribute(ParameterAndAttribute.INTERVALS_LIST, intervals);
				List<DoctorSchedule> schedules = scheduleService
						.findAllActiveSchedulesByDoctor(appointment.getDoctor().getUserId());
				request.setAttribute(ParameterAndAttribute.DOCTOR_SCHEDULES_LIST, schedules);
				logger.log(Level.DEBUG, "doctor schedules list: " + schedules);
				List<User> doctors = userService.findUsersByRole(Role.DOCTOR);
				request.setAttribute(ParameterAndAttribute.DOCTORS_LIST, doctors);
				logger.log(Level.DEBUG, "doctors: " + doctors);
				List<Procedure> procedures = procedureService.findAll();
				request.setAttribute(ParameterAndAttribute.PROCEDURES_LIST, procedures);
				request.setAttribute(ParameterAndAttribute.APPOINTMENT, optional.get());
				session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_CHANGE_APPOINTMENT_PAGE);
				router.setPagePath(PagePath.CHANGE_APPOINTMENT);
			} else {
				logger.log(Level.ERROR, "Nothing founded");
				request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, Message.UNKNOWN_PROBLEM);
				session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ERROR);
				router.setPagePath(PagePath.ERROR);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "AppointmentServiceException in method execute");
			session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ERROR);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}
