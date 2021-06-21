package by.metelski.webtask.command.async;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.Gson;
import by.metelski.webtask.command.CommandAsync;
import by.metelski.webtask.command.Message;
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
import by.metelski.webtask.util.IntervalCalculator;

/**
* The command is find free time intervals from doctor schedule
* 
 * @author Yauhen Metelski
 *
 */
public class FindTimeIntervalsByScheduleIdAsyncCommand implements CommandAsync {
	private static final Logger logger = LogManager.getLogger();
	/**
	 * Every working hour has some number of intervals, this field is duration one of them.
	 */
	private final int intervalIncrement = 15;
	private ScheduleService service = new ScheduleServiceImpl(new ScheduleDaoImpl());
	private AppointmentService appointmentService = new AppointmentServiceImpl(new AppointmentDaoImpl(),
			new ProcedureDaoImpl());

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.DEBUG, "FindTimeIntervalsByScheduleIdAsyncCommand");
		response.setContentType("application/json");
		Router router = new Router();
		List<String> intervals = new ArrayList<>();
		List<Appointment> appointments = new ArrayList<>();
		DoctorSchedule schedule;
		Optional<DoctorSchedule> optional;
		Long scheduleId = Long.parseLong(request.getParameter(ParameterAndAttribute.SCHEDULE_ID));
		String intervalsGson;
		try {
			optional = service.findScheduleById(scheduleId);
			if (optional.isPresent()) {
				schedule = optional.get();
				appointments = appointmentService.findAllByDoctorIdAndDate(schedule.getDoctor().getUserId(),
						schedule.getDate());
//				if(!appointments.isEmpty()) {FIXME delete
				intervals = IntervalCalculator.calculateIntervals(schedule, intervalIncrement, appointments);
				intervalsGson = new Gson().toJson(intervals);
//				} else {
//					intervalsGson = new Gson().toJson(Message.NOTHING_FOUNDED);
//				}
			} else {
				intervalsGson = new Gson().toJson(Message.NOTHING_FOUNDED);
			}
			logger.log(Level.DEBUG, "string gson: " + intervalsGson);
			response.getWriter().write(intervalsGson);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ScheduleServiceException in method execute");
			router.setPagePath(PagePath.ERROR);
		} catch (IOException e1) {
			logger.log(Level.ERROR, "IOException in method execute" + e1);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}
