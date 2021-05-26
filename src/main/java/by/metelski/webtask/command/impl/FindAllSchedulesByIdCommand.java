package by.metelski.webtask.command.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.ScheduleDaoImpl;
import by.metelski.webtask.model.service.ScheduleService;
import by.metelski.webtask.model.service.impl.ScheduleServiceImpl;

public class FindAllSchedulesByIdCommand implements Command{
	private static final Logger logger = LogManager.getLogger();
	private ScheduleService service = new ScheduleServiceImpl(new ScheduleDaoImpl());

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.DEBUG, "FindAllSchedulesByIdCommand");
		List<DoctorSchedule> schedules;
		Router router = new Router();
		HttpSession session = request.getSession();
		String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
		Long userId = Long.parseLong(request.getParameter(ParameterAndAttribute.DOCTOR_ID));
		try {
			schedules = service.findAllSchedulesByDoctorId(userId);
			router.setPagePath(page);
			request.setAttribute(ParameterAndAttribute.DOCTOR_SCHEDULES_LIST, schedules);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ScheduleServiceException in method execute");
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}

}
