package by.metelski.webtask.command.async;

import java.io.IOException;
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

public class FindAllSchedulesByUserIdAsyncCommand implements Command{
	private static final Logger logger = LogManager.getLogger();
	private ScheduleService service = new ScheduleServiceImpl(new ScheduleDaoImpl());
	//FIXME delete or use

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response){
		logger.log(Level.DEBUG, "FindAllSchedulesByIdCommand");
		response.setContentType("application/json");
		List<DoctorSchedule> schedules;
		Router router = new Router();
		Long userId = Long.parseLong(request.getParameter(ParameterAndAttribute.DOCTOR_ID));
		try {
			schedules = service.findAllSchedulesByDoctorId(userId);		
			String stringGson = new Gson().toJson(schedules);
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
