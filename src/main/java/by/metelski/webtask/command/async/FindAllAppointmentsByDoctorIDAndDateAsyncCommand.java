package by.metelski.webtask.command.async;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.AppointmentDaoImpl;
import by.metelski.webtask.model.dao.impl.ProcedureDaoImpl;
import by.metelski.webtask.model.dao.impl.ScheduleDaoImpl;
import by.metelski.webtask.model.service.AppointmentService;
import by.metelski.webtask.model.service.ScheduleService;
import by.metelski.webtask.model.service.impl.AppointmentServiceImpl;
import by.metelski.webtask.model.service.impl.ScheduleServiceImpl;

public class FindAllAppointmentsByDoctorIDAndDateAsyncCommand implements Command{
	private static final Logger logger = LogManager.getLogger();
	private AppointmentService appointmentService = new AppointmentServiceImpl(new AppointmentDaoImpl(),new ProcedureDaoImpl());

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response){
		logger.log(Level.DEBUG, "FindAllAppointmentsByDoctorIDAndDateAsyncCommand");
		response.setContentType("application/json");
		List<Appointment> appointments;
		Router router = new Router();
		String dataFromRequest = request.getParameter(ParameterAndAttribute.APPOINTMENT_DATA);
		logger.log(Level.DEBUG, "data" +dataFromRequest);
		String[] data = dataFromRequest.split(":");
		Long doctorId = Long.parseLong(data[0]);
		logger.log(Level.DEBUG, "id:" +doctorId);
		Date date= Date.valueOf(data[1]);
		logger.log(Level.DEBUG, "date:" +date);
		try {
			appointments = appointmentService.findAllByDoctorIdAndDate(doctorId, date);		
			String stringGson = new Gson().toJson(appointments);
			logger.log(Level.DEBUG, "string gson: " + stringGson);
			 response.getWriter().write(stringGson);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ScheduleServiceException in method execute");
			router.setPagePath(PagePath.ERROR);
		}catch(IOException e1) {
			logger.log(Level.ERROR, "IOException in method execute");
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}