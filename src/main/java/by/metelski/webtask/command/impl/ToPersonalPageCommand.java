package by.metelski.webtask.command.impl;

import java.time.LocalDate;
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
import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.entity.User.Role;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.ScheduleDaoImpl;
import by.metelski.webtask.model.service.ProcedureService;
import by.metelski.webtask.model.service.ScheduleService;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.service.impl.ProcedureServiceImpl;
import by.metelski.webtask.model.service.impl.ScheduleServiceImpl;
import by.metelski.webtask.model.service.impl.UserServiceImpl;

public class ToPersonalPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private ScheduleService sheduleService =new ScheduleServiceImpl(new ScheduleDaoImpl());
    private UserService userService = new UserServiceImpl();
    private ProcedureService procedureService= new ProcedureServiceImpl();
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.INFO, "ToPersonalPageCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(ParameterAndAttribute.USER);
		if(user!= null) {
			//TODO switch role blocked confirmed
			switch(user.getRole()) {
			case ADMIN:
				router.setPagePath(PagePath.ADMIN);
				break;
			case GUEST:
				router.setPagePath(PagePath.SIGN_IN);
				break;
			case USER:
				try {
					List<User> doctors = userService.findUsersByRole(Role.DOCTOR);
					session.setAttribute(ParameterAndAttribute.DOCTORS_LIST, doctors);
					logger.log(Level.DEBUG, "doctors: " + doctors);
					List<Procedure> procedures = procedureService.findAll();
					session.setAttribute(ParameterAndAttribute.PROCEDURES_LIST, procedures);
				} catch (ServiceException e) {
					logger.log(Level.ERROR, "ServiceException" + e);
					router.setPagePath(PagePath.ERROR);		
				}
				router.setPagePath(PagePath.USER);
			break;
			case DOCTOR:
				logger.log(Level.DEBUG, "case doctor");
				//FIXME DELETE
//				try {
//					List<Schedule> schedules = service.findAllActiveSchedules();
//					request.setAttribute(ParameterAndAttribute.SCHEDULE_LIST, schedules);
//					logger.log(Level.DEBUG, "shedules: " + schedules);
//				} catch (ServiceException e) {
//					logger.log(Level.ERROR, "ScheduleServiceException in method execute" + e);
//					router.setPagePath(PagePath.ERROR);
//				}
				router.setPagePath(PagePath.DOCTOR);
				break;
			}
		}else {
			router.setPagePath(PagePath.SIGN_IN);
		}
		return router;
	}
}
