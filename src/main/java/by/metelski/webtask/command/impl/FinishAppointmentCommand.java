package by.metelski.webtask.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.command.Router.Type;
import by.metelski.webtask.entity.Appointment.Status;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.AppointmentDaoImpl;
import by.metelski.webtask.model.dao.impl.ProcedureDaoImpl;
import by.metelski.webtask.model.service.AppointmentService;
import by.metelski.webtask.model.service.impl.AppointmentServiceImpl;

/**
 * The command finish appointment
 * @author Yauhen Metelski
 *
 */
public class FinishAppointmentCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private AppointmentService service = new AppointmentServiceImpl(new AppointmentDaoImpl(), new ProcedureDaoImpl());

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.DEBUG, "FinishAppointmentCommand");
		Router router = new Router();
		long appointmentId = Long.parseLong(request.getParameter(ParameterAndAttribute.APPOINTMENT_ID));
		try {
			if (service.changeStatus(appointmentId, Status.FINISHED)) {
				String page = request.getContextPath() + PagePath.TO_PERSONAL_PAGE;
				router.setPagePath(page);
				router.setType(Type.REDIRECT);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "AppointmentServiceException in method execute", e);
			request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
			request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}