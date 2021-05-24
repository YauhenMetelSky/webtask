package by.metelski.webtask.command.async;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.ScheduleDaoImpl;
import by.metelski.webtask.model.service.ScheduleService;
import by.metelski.webtask.model.service.impl.ScheduleServiceImpl;

public class FindTimeIntervalsByScheduleIdAsyncCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private ScheduleService service = new ScheduleServiceImpl(new ScheduleDaoImpl());

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.DEBUG, "FindTimeIntervalsByScheduleIdAsyncCommand");
		response.setContentType("application/json");
		Router router = new Router();
		List<String> intervals = new ArrayList<>();
		DoctorSchedule schedule;
		Long scheduleId = Long.parseLong(request.getParameter(ParameterAndAttribute.SCHEDULE_ID));
		try {// FIXME empty optional
			schedule = service.FindScheduleById(scheduleId).get();
			LocalTime startInterval = LocalTime.parse(schedule.getStartTime().toString());
			logger.log(Level.DEBUG, "start interval: " + startInterval);
			LocalTime endInterval = LocalTime.parse(schedule.getEndTime().toString());
			logger.log(Level.DEBUG, "end interval: " + endInterval);
			while (startInterval.isBefore(endInterval)) {
				intervals.add(startInterval.format(DateTimeFormatter.ISO_TIME));
				startInterval = startInterval.plusMinutes(15);
				logger.log(Level.DEBUG, "start interval: " + startInterval);
			}
			String intervalsGson = new Gson().toJson(intervals);
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
