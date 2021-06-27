package by.metelski.webtask.command.impl;

import java.util.List;

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
import by.metelski.webtask.entity.Appointment.Status;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.AppointmentDaoImpl;
import by.metelski.webtask.model.dao.impl.ProcedureDaoImpl;
import by.metelski.webtask.model.service.AppointmentService;
import by.metelski.webtask.model.service.impl.AppointmentServiceImpl;

public class FindAllFinishedAppointmentsCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	AppointmentService service = new AppointmentServiceImpl(new AppointmentDaoImpl(), new ProcedureDaoImpl());
	private final int pageNumber = 0;
	
	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.DEBUG, "Method execute, FindAllFinishedAppointmentsCommand");
		List<Appointment> appointments;
		int numberOfPages;
		Router router = new Router();
		HttpSession session = request.getSession();
		String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
		try {
			numberOfPages = service.findNumberOfPages(Status.FINISHED);
			appointments = service.findAllByStatusFromRow(Status.FINISHED,pageNumber);
			session.setAttribute(ParameterAndAttribute.NUMBER_OF_PAGES, numberOfPages);
			session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.SUCCESSFUL);
			request.setAttribute(ParameterAndAttribute.APPOINTMENTS_LIST, appointments);
			router.setPagePath(page);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "AppointmentServiceException in method execute" + e);
			request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
			request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}