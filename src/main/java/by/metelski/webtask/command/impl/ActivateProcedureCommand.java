package by.metelski.webtask.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.command.Router.Type;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.service.ProcedureService;
import by.metelski.webtask.model.service.impl.ProcedureServiceImpl;

public class ActivateProcedureCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	private ProcedureService procedureService = new ProcedureServiceImpl();

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
	    Router router = new Router();
	    boolean isActive = false;
	    logger.log(Level.DEBUG, "execute method ActivateProcedureCommand");
		Long id = Long.parseLong(request.getParameter(ParameterAndAttribute.PROCEDURE_ID));
		logger.log(Level.DEBUG, "procedure id: " + id);
		try {
			isActive = procedureService.setIsActive(id, true);
					if (isActive) {
						String page = request.getContextPath() + PagePath.TO_PERSONAL_PAGE;
						router.setPagePath(page);
						router.setType(Type.REDIRECT);
				//TODO session attribute message successful activation
			} else {
				String page = request.getContextPath() + PagePath.TO_PERSONAL_PAGE;
				router.setPagePath(page);
				router.setType(Type.REDIRECT);
				//TODO session attribute message try to activate your account later"
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ProcedureServiceException in method execute" + e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}
