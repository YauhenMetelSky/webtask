package by.metelski.webtask.command.async;

import java.io.IOException;
import java.util.List;
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
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.ScheduleDaoImpl;
import by.metelski.webtask.model.service.ScheduleService;
import by.metelski.webtask.model.service.impl.ScheduleServiceImpl;

/**
 * The command is find all active schedules(isActive=true, date>=current date)
 * by doctor id
 * 
 * @see CommandAsync
 * @author Yauhen Metelski
 *
 */
public class FindAllActiveSchedulesByDoctorIdAsyncCommand implements CommandAsync {
	/**
	 * Logger
	 */
	private static final Logger logger = LogManager.getLogger();
	/**
	 * ScheduleService instance
	 */
	private ScheduleService service = new ScheduleServiceImpl(new ScheduleDaoImpl());

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.DEBUG, "FindAllActiveSchedulesByIdCommand");
		response.setContentType("application/json");
		List<DoctorSchedule> schedules;
		Router router = new Router();
		Long userId = Long.parseLong(request.getParameter(ParameterAndAttribute.DOCTOR_ID));
		try {
			schedules = service.findAllActiveSchedulesByDoctor(userId);
			String stringGson;
			if(!schedules.isEmpty()) {
				stringGson = new Gson().toJson(schedules);
			}else {
				stringGson = new Gson().toJson(Message.NOTHING_FOUNDED);
			};
			logger.log(Level.DEBUG, "string gson: " + stringGson);
			response.getWriter().write(stringGson);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ScheduleServiceException in method execute");
			router.setPagePath(PagePath.ERROR);
		} catch (IOException e1) {
			logger.log(Level.ERROR, "IOException in method execute");
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}