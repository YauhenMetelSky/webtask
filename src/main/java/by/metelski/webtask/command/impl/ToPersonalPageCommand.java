package by.metelski.webtask.command.impl;

import java.util.List;
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
import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.entity.User.Role;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.ProcedureDaoImpl;
import by.metelski.webtask.model.dao.impl.UserDaoImpl;
import by.metelski.webtask.model.service.ProcedureService;
import by.metelski.webtask.model.service.UserService;
import by.metelski.webtask.model.service.impl.ProcedureServiceImpl;
import by.metelski.webtask.model.service.impl.UserServiceImpl;

public class ToPersonalPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl(new UserDaoImpl());
    private ProcedureService procedureService = new ProcedureServiceImpl(new ProcedureDaoImpl());
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.INFO, "ToPersonalPageCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(ParameterAndAttribute.USER);
		if(user!= null) {
			if(user.isBlocked()) {
				router.setPagePath(PagePath.TO_MAIN_PAGE);
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER,Message.ACCOUNT_IS_BLOCKED);
				return router;
			}
			if(user.isBlocked()) {
				router.setPagePath(PagePath.TO_MAIN_PAGE);
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER,Message.ACCOUNT_IS_BLOCKED);
				return router;
			}
			switch(user.getRole()) {
			case ADMIN:
				router.setPagePath(PagePath.ADMIN);
				break;
			case GUEST:
				router.setPagePath(PagePath.SIGN_IN);
				break;
			case USER:
				try {
					List<Procedure> procedures = procedureService.findAllActive();
					List<User> doctors = userService.findUsersByRole(Role.DOCTOR);
					session.setAttribute(ParameterAndAttribute.DOCTORS_LIST, doctors);
					session.setAttribute(ParameterAndAttribute.ACTIVE_PROCEDURES_LIST, procedures);
					logger.log(Level.DEBUG, "doctors: " + doctors);
				} catch (ServiceException e) {
					logger.log(Level.ERROR, "ServiceException" + e);
					router.setPagePath(PagePath.ERROR);		
				}
				router.setPagePath(PagePath.USER);
			break;
			case DOCTOR:
				logger.log(Level.DEBUG, "case doctor");
				router.setPagePath(PagePath.DOCTOR);
				break;
			}
		}else {
			router.setPagePath(PagePath.SIGN_IN);
		}
		return router;
	}
}
