package by.metelski.webtask.command.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.AppointmentDaoImpl;
import by.metelski.webtask.model.dao.impl.ProcedureDaoImpl;
import by.metelski.webtask.model.dao.impl.ScheduleDaoImpl;
import by.metelski.webtask.model.service.AppointmentService;
import by.metelski.webtask.model.service.ScheduleService;
import by.metelski.webtask.model.service.impl.AppointmentServiceImpl;
import by.metelski.webtask.model.service.impl.ScheduleServiceImpl;

public class UpdateAppointmentCommand implements Command{
	private static final Logger logger = LogManager.getLogger();
	AppointmentService service = new AppointmentServiceImpl(new AppointmentDaoImpl(),new ProcedureDaoImpl());
	ScheduleService scheduleService = new ScheduleServiceImpl(new ScheduleDaoImpl());
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.DEBUG, "execute method UpdateAppointmentCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		Map<String, String> appointmentData = new HashMap<>();
		String userId = request.getParameter(ParameterAndAttribute.USER_ID);
		logger.log(Level.DEBUG, "userId:"+userId);
		String appointmentId =request.getParameter(ParameterAndAttribute.APPOINTMENT_ID);
		String procedureId =request.getParameter(ParameterAndAttribute.PROCEDURE_ID);
		String doctorId =request.getParameter(ParameterAndAttribute.DOCTOR_ID);
		String scheduleId = request.getParameter(ParameterAndAttribute.SCHEDULE_ID);
		String time = request.getParameter(ParameterAndAttribute.START_TIME);
		appointmentData.put(ParameterAndAttribute.APPOINTMENT_ID, appointmentId);
		appointmentData.put(ParameterAndAttribute.USER_ID, userId);
		appointmentData.put(ParameterAndAttribute.DOCTOR_ID, doctorId);
		appointmentData.put(ParameterAndAttribute.PROCEDURE_ID, procedureId);
		appointmentData.put(ParameterAndAttribute.START_TIME, time);
		logger.log(Level.DEBUG, "data in map from request: " + appointmentData.toString());
		try {
			String date = scheduleService.findScheduleById(Long.parseLong(scheduleId)).get().getDate().toString();
			appointmentData.put(ParameterAndAttribute.APPOINTMENT_DATE, date);
			String page = request.getContextPath() + PagePath.TO_PERSONAL_PAGE;
			router.setPagePath(page);			
			if(service.change(appointmentData)) {
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.SUCCESSFUL);
				router.setType(Type.REDIRECT);
			} else {
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.UNSUCCESSFUL);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ServiceException in method execute");
			request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
			request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}
