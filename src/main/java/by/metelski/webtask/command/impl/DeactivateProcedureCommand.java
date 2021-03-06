package by.metelski.webtask.command.impl;

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
import by.metelski.webtask.command.Router.Type;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.ProcedureDaoImpl;
import by.metelski.webtask.model.service.ProcedureService;
import by.metelski.webtask.model.service.impl.ProcedureServiceImpl;

/**
 * The command deactivates procedure.
 * @author Yauhen Metelski
 *
 */
public class DeactivateProcedureCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private ProcedureService procedureService = new ProcedureServiceImpl(new ProcedureDaoImpl());

	@Override
	public Router execute(HttpServletRequest request) {
		Router router = new Router();
		HttpSession session = request.getSession();
		boolean isActive = false;
		logger.log(Level.DEBUG, "execute method DeactivateProcedureCommand");
		Long id = Long.parseLong(request.getParameter(ParameterAndAttribute.PROCEDURE_ID));
		logger.log(Level.DEBUG, "procedure id: " + id);
		try {
			isActive = procedureService.setIsActive(id, false);
			if (isActive) {
				String page = request.getContextPath() + PagePath.TO_PERSONAL_PAGE;
				router.setPagePath(page);
				router.setType(Type.REDIRECT);
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.SUCCESSFUL);
			} else {
				String page = request.getContextPath() + PagePath.TO_PERSONAL_PAGE;
				router.setPagePath(page);
				router.setType(Type.REDIRECT);
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.UNSUCCESSFUL);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ProcedureServiceException in method execute" + e);
			request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
			request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}