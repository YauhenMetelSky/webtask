package by.metelski.webtask.command.impl;

import java.util.List;

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
import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.AppointmentDaoImpl;
import by.metelski.webtask.model.dao.impl.ProcedureDaoImpl;
import by.metelski.webtask.model.service.AppointmentService;
import by.metelski.webtask.model.service.impl.AppointmentServiceImpl;

public class FindAllAppointmentsByDoctorIdCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private AppointmentService service = new AppointmentServiceImpl(new AppointmentDaoImpl(),new ProcedureDaoImpl());

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.DEBUG, "FindAllAppointmentsByDoctorCommand");
		List<Appointment> appointments;
		Router router = new Router();
		HttpSession session = request.getSession();
		String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
		User user = (User) session.getAttribute(ParameterAndAttribute.USER);
		long userId = user.getUserId();
		try {
			appointments = service.findAllByDoctorId(userId);
			router.setPagePath(page);
			request.setAttribute(ParameterAndAttribute.APPOINTMENTS_LIST, appointments);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ScheduleServiceException in method execute");
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}