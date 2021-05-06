package by.metelski.webtask.command.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

public class AddProcedureCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	ProcedureService procedureService = new ProcedureServiceImpl();
	
	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.DEBUG, "execute method AddProcedureCommand");
		Router router = new Router();
		Map<String, String> procedureData = new HashMap<>();
		String procedureName = request.getParameter(ParameterAndAttribute.PROCEDURE_NAME);
		String procedureImage = request.getParameter(ParameterAndAttribute.PROCEDURE_IMAGE);
		String procedurePrice = request.getParameter(ParameterAndAttribute.PROCEDURE_PRICE);
		String procedureDescription = request.getParameter(ParameterAndAttribute.DESCRIPTION);
		String procedureDuration = request.getParameter(ParameterAndAttribute.DURATION);
		procedureData.put(ParameterAndAttribute.PROCEDURE_NAME, procedureName);
		procedureData.put(ParameterAndAttribute.PROCEDURE_IMAGE, procedureImage);
		procedureData.put(ParameterAndAttribute.PROCEDURE_PRICE, procedurePrice);
		procedureData.put(ParameterAndAttribute.DESCRIPTION, procedureDescription);
		procedureData.put(ParameterAndAttribute.DURATION, procedureDuration);
		logger.log(Level.DEBUG, "data in map from request: " + procedureData.toString());
		try {
			if(procedureService.add(procedureData)) {
				String page = request.getContextPath() +PagePath.TO_PERSONAL_PAGE;
				//TODO Show message Procedure added
				router.setPagePath(page);
				router.setType(Type.REDIRECT);
			}
		} catch (ServiceException e) {
			router.setPagePath(PagePath.ERROR);
		}		
		return router;
	}
}
