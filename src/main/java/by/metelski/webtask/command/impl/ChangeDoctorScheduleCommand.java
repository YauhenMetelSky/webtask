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
import by.metelski.webtask.model.dao.impl.ScheduleDaoImpl;
import by.metelski.webtask.model.service.ScheduleService;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.service.impl.ScheduleServiceImpl;
import by.metelski.webtask.model.service.impl.UserServiceImpl;

public class ChangeDoctorScheduleCommand implements Command{
	private static final Logger logger = LogManager.getLogger();
     ScheduleService scheduleService = new ScheduleServiceImpl(new ScheduleDaoImpl());
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.DEBUG, "execute method ChangeDoctorScheduleCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		Map<String,String> scheduleData = new HashMap<>();
		String scheduleId = request.getParameter(ParameterAndAttribute.DOCTOR_SCHEDULE_ID);
		logger.log(Level.DEBUG, "doctor_schedule id:"+ scheduleId);
		String startTime = request.getParameter(ParameterAndAttribute.START_TIME);
		String endTime = request.getParameter(ParameterAndAttribute.END_TIME);
		String date = request.getParameter(ParameterAndAttribute.DATE);
		scheduleData.put(ParameterAndAttribute.DOCTOR_SCHEDULE_ID, scheduleId);
		scheduleData.put(ParameterAndAttribute.START_TIME, startTime);
		scheduleData.put(ParameterAndAttribute.END_TIME, endTime);
		scheduleData.put(ParameterAndAttribute.DATE, date);
		String page = request.getContextPath() + PagePath.TO_PERSONAL_PAGE;
		router.setPagePath(page);
		try {
			if(scheduleService.changeDoctorSchedule(scheduleData)) {
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.SUCCESSFUL);
				router.setType(Type.REDIRECT);
			} else {
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.UNSUCCESSFUL);
			}
		} catch (ServiceException e) {
			router.setPagePath(PagePath.ERROR);
		}
			return router;
	}
}