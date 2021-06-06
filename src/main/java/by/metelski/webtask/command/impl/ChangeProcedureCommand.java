package by.metelski.webtask.command.impl;

import java.util.HashMap;
import java.util.Map;

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
import by.metelski.webtask.command.Router.Type;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.impl.ProcedureDaoImpl;
import by.metelski.webtask.model.service.ProcedureService;
import by.metelski.webtask.model.service.impl.ProcedureServiceImpl;

public class ChangeProcedureCommand implements Command{
	private static final Logger logger = LogManager.getLogger();
	ProcedureService procedureService= new ProcedureServiceImpl(new ProcedureDaoImpl());
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.DEBUG, "execute method ChangeProcedureCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		Map<String,String> procedureData = new HashMap<>();
		String procedureId = request.getParameter(ParameterAndAttribute.PROCEDURE_ID);
		logger.log(Level.DEBUG, "procedure id:"+ procedureId);
		String name = request.getParameter(ParameterAndAttribute.PROCEDURE_NAME);
		String imageName = request.getParameter(ParameterAndAttribute.PROCEDURE_IMAGE);
		String price = request.getParameter(ParameterAndAttribute.PROCEDURE_PRICE);
		String description = request.getParameter(ParameterAndAttribute.DESCRIPTION);
		String duration = request.getParameter(ParameterAndAttribute.DURATION);
		procedureData.put(ParameterAndAttribute.PROCEDURE_ID, procedureId);
		procedureData.put(ParameterAndAttribute.PROCEDURE_NAME, name);
		procedureData.put(ParameterAndAttribute.PROCEDURE_IMAGE, imageName);
		procedureData.put(ParameterAndAttribute.PROCEDURE_PRICE, price);
		procedureData.put(ParameterAndAttribute.DESCRIPTION, description);
		procedureData.put(ParameterAndAttribute.DURATION, duration);
		try {
			if(procedureService.changeProcedure(procedureData)) {
				String page = request.getContextPath() + PagePath.TO_PERSONAL_PAGE;
				router.setPagePath(page);
				router.setType(Type.REDIRECT);
				session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.SUCCESSFUL);
			} 
		} catch (ServiceException e) {
			router.setPagePath(PagePath.ERROR);
		}
			return router;
	}
}