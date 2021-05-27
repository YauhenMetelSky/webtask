package by.metelski.webtask.command.impl;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.entity.User.Role;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.ScheduleDaoImpl;
import by.metelski.webtask.model.service.AppointmentService;
import by.metelski.webtask.model.service.ProcedureService;
import by.metelski.webtask.model.service.ScheduleService;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.service.impl.AppointmentServiceImpl;
import by.metelski.webtask.model.service.impl.ProcedureServiceImpl;
import by.metelski.webtask.model.service.impl.ScheduleServiceImpl;
import by.metelski.webtask.model.service.impl.UserServiceImpl;
import by.metelski.webtask.util.IntervalCalculator;

public class ToChangeAppointmentCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private final int intervalIncrement = 15;
	AppointmentService service = new AppointmentServiceImpl();
	UserService userService = new UserServiceImpl();
	ProcedureService procedureService = new ProcedureServiceImpl();
	ScheduleService scheduleService = new ScheduleServiceImpl(new ScheduleDaoImpl());

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.DEBUG, "execute method ToChangeAppointmentCommand");
		Router router = new Router();
		logger.log(Level.DEBUG, "appointmentId from request:" + request.getParameter(ParameterAndAttribute.APPOINTMENT_ID));
		long appointmentId =Long.parseLong(request.getParameter(ParameterAndAttribute.APPOINTMENT_ID));
		logger.log(Level.DEBUG, "appointmentId:" +appointmentId);
		//TODO pop up window or new page,
		try {
			Optional<Appointment> optional = service.findById(appointmentId);
			logger.log(Level.DEBUG, "appointment:" +optional.get());
			if(optional.isPresent()) {
				Appointment appointment = optional.get();
//				long userId = appointment.getUser().getUserId();
//				logger.log(Level.DEBUG, "userId:" +userId);
//				request.setAttribute(ParameterAndAttribute.USER_ID, userId);//TODO is it need?
				DoctorSchedule schedule = scheduleService.findScheduleByDateAndDoctor(appointment.getDate(), appointment.getDoctor().getUserId()).get();
				List<String> intervals =IntervalCalculator.calculateIntervals(schedule, intervalIncrement);
				request.setAttribute(ParameterAndAttribute.INTERVALS_LIST,intervals);
				List<DoctorSchedule> schedules= scheduleService.findAllSchedulesByDoctorId(appointment.getDoctor().getUserId());
				request.setAttribute(ParameterAndAttribute.DOCTOR_SCHEDULES_LIST, schedules);
				logger.log(Level.DEBUG, "doctor schedules list: " + schedules);
				List<User> doctors = userService.findUsersByRole(Role.DOCTOR);
				request.setAttribute(ParameterAndAttribute.DOCTORS_LIST, doctors);
				logger.log(Level.DEBUG, "doctors: " + doctors);
				List<Procedure> procedures = procedureService.findAll();
				request.setAttribute(ParameterAndAttribute.PROCEDURES_LIST, procedures);
				request.setAttribute(ParameterAndAttribute.APPOINTMENT, optional.get());
				router.setPagePath(PagePath.CHANGE_APPOINTMENT);
			}else {
				//TODO message nothing founded
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "AppointmentServiceException in method execute");
			router.setPagePath(PagePath.ERROR);
		}				
		return router;
	}
}
