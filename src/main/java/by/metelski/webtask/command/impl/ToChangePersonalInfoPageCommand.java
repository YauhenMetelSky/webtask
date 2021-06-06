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

public class ToChangePersonalInfoPageCommand  implements Command {
	private static final Logger logger = LogManager.getLogger();
	AppointmentService service = new AppointmentServiceImpl(new AppointmentDaoImpl(),new ProcedureDaoImpl());
	UserService userService = new UserServiceImpl(new UserDaoImpl());
	ProcedureService procedureService = new ProcedureServiceImpl(new ProcedureDaoImpl());
	ScheduleService scheduleService = new ScheduleServiceImpl(new ScheduleDaoImpl());

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.DEBUG, "execute method ToChangePersonalInfoPageCommand");
		Router router = new Router();
//FIXME delete		logger.log(Level.DEBUG, "appointmentId from request:" + request.getParameter(ParameterAndAttribute.APPOINTMENT_ID));
		router.setPagePath(PagePath.CHANGE_PERSONAL_INFO);
		return router;
	}
}