package by.metelski.webtask.command.impl;

import java.sql.Date;
import java.time.LocalDate;
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
import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.entity.Appointment.Status;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.AppointmentDaoImpl;
import by.metelski.webtask.model.dao.impl.ProcedureDaoImpl;
import by.metelski.webtask.model.service.AppointmentService;
import by.metelski.webtask.model.service.impl.AppointmentServiceImpl;

public class FindTodaysAppointmentsCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private AppointmentService service = new AppointmentServiceImpl(new AppointmentDaoImpl(), new ProcedureDaoImpl());

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.DEBUG, "FindTodaysAppointmentsCommand");
		List<Appointment> appointments;
		Router router = new Router();
		HttpSession session = request.getSession();
		String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
		Date today = Date.valueOf(LocalDate.now());
		try {
			appointments = service.findAllByDateAndStatus(today, Status.CONFIRMED);
			router.setPagePath(page);
			request.setAttribute(ParameterAndAttribute.APPOINTMENTS_LIST, appointments);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ScheduleServiceException in method execute");
			request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
			request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}