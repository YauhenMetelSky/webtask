package by.metelski.webtask.command.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.command.Router.Type;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.service.AppointmentService;
import by.metelski.webtask.model.service.impl.AppointmentServiceImpl;


public class AddAppointmentCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	AppointmentService service = new AppointmentServiceImpl();
	
	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.DEBUG, "execute method AddProcedureCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
		Map<String, String> appointmentData = new HashMap<>();
		User user = (User)session.getAttribute(ParameterAndAttribute.USER);
		String userId = Long.toString(user.getUserId());
		String procedureName = request.getParameter(ParameterAndAttribute.PROCEDURE_ID);
		String doctor = request.getParameter(ParameterAndAttribute.DOCTOR_ID);
		String date = request.getParameter(ParameterAndAttribute.APPOINTMENT_DATE);
		String startTime = request.getParameter(ParameterAndAttribute.START_TIME);
		appointmentData.put(ParameterAndAttribute.USER_ID, userId);
		appointmentData.put(ParameterAndAttribute.PROCEDURE_ID, procedureName);
		appointmentData.put(ParameterAndAttribute.DOCTOR_ID, doctor);
		appointmentData.put(ParameterAndAttribute.APPOINTMENT_DATE, date);
		appointmentData.put(ParameterAndAttribute.START_TIME, startTime);
		logger.log(Level.DEBUG, "data in map from request: " + appointmentData.toString());
		try {
			if(service.add(appointmentData)) {
				//TODO Show message Appointment added, we recall you, send email
				router.setPagePath(page);
				router.setType(Type.REDIRECT);
			}
		} catch (ServiceException e) {
			router.setPagePath(PagePath.ERROR);
		}		
		return router;
	}
}
