package by.metelski.webtask.command.impl;

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
import by.metelski.webtask.command.Router.Type;
import by.metelski.webtask.entity.Appointment.Status;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.AppointmentDaoImpl;
import by.metelski.webtask.model.dao.impl.ProcedureDaoImpl;
import by.metelski.webtask.model.service.AppointmentService;
import by.metelski.webtask.model.service.impl.AppointmentServiceImpl;

/**
 * The command confirm appointment 
 *  
 * Changes appointment status on the "Confirmed". 
 * Used by Administrator.
 * @author Yauhen Metelski
 *
 */
public class ConfirmAppointmentCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private AppointmentService service = new AppointmentServiceImpl(new AppointmentDaoImpl(), new ProcedureDaoImpl());

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.DEBUG, "ConfirmAppointmentCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		long appointmentId = Long.parseLong(request.getParameter(ParameterAndAttribute.APPOINTMENT_ID));
		try {
			if (service.changeStatus(appointmentId, Status.CONFIRMED)) {
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.SUCCESSFUL);
				String page = request.getContextPath() + PagePath.TO_PERSONAL_PAGE;
				router.setPagePath(page);
				router.setType(Type.REDIRECT);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ScheduleServiceException in method execute");
			request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
			request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}
