package by.metelski.webtask.command.impl;

import java.util.List;
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
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.ScheduleDaoImpl;
import by.metelski.webtask.model.service.ScheduleService;
import by.metelski.webtask.model.service.impl.ScheduleServiceImpl;

/**
 * The command find all schedules with any status using doctor id
 * Used by doctor only.
 * @author Yauhen Metelski
 *
 */
public class FindAllSchedulesByDoctorCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private ScheduleService service = new ScheduleServiceImpl(new ScheduleDaoImpl());
	private final int startRow = 0;

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.DEBUG, "FindAllSchedulesByDoctorCommand");
		int numberOfPages;
		List<DoctorSchedule> schedules;
		Router router = new Router();
		HttpSession session = request.getSession();
		String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
		User user = (User) session.getAttribute(ParameterAndAttribute.USER);
		Long userId = user.getUserId();
		try {
			schedules = service.findAllSchedulesByDoctorIdFromRow(userId, startRow);
			numberOfPages = service.findNumberOfPagesDoctorsShedules(userId);
			router.setPagePath(page);
			request.setAttribute(ParameterAndAttribute.DOCTOR_SCHEDULES_LIST, schedules);
			session.setAttribute(ParameterAndAttribute.NUMBER_OF_PAGES, numberOfPages);
			session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.SUCCESSFUL);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ScheduleServiceException in method execute");
			request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
			request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}
