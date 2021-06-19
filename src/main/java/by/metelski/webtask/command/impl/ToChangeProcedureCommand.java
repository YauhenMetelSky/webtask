package by.metelski.webtask.command.impl;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.Message;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.ProcedureDaoImpl;
import by.metelski.webtask.model.service.ProcedureService;
import by.metelski.webtask.model.service.impl.ProcedureServiceImpl;

/**
 *  Go to changeprocedure page command
 * @author Yauhen Metelski
 *
 */
public class ToChangeProcedureCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	ProcedureService procedureService = new ProcedureServiceImpl(new ProcedureDaoImpl());

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.DEBUG, "execute method ToChangeProcedureCommand");
		Router router = new Router();
		logger.log(Level.DEBUG, "procedure from request:" + request.getParameter(ParameterAndAttribute.PROCEDURE_ID));
		long procedureId = Long.parseLong(request.getParameter(ParameterAndAttribute.PROCEDURE_ID));
		try {
			Optional<Procedure> optional = procedureService.findById(procedureId);
			logger.log(Level.DEBUG, "finded procedure:" + optional.get());
			if (optional.isPresent()) {
				request.setAttribute(ParameterAndAttribute.PROCEDURE, optional.get());
				router.setPagePath(PagePath.CHANGE_PROCEDURE);
			} else {
				logger.log(Level.ERROR, "Nothing founded");
				request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, Message.UNKNOWN_PROBLEM);
				router.setPagePath(PagePath.ERROR);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ProcedureServiceException in method execute");
			request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
			request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
			router.setPagePath(PagePath.ERROR);
		}
		return router;
	}
}