package by.metelski.webtask.command.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.command.Router.Type;
import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.service.ProcedureService;
import by.metelski.webtask.model.service.impl.ProcedureServiceImpl;

public class FindAllProceduresCommand implements Command {
	private static final Logger logger = LogManager.getLogger();
	ProcedureService procedureService = new ProcedureServiceImpl();
	
	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.DEBUG, "execute method FindAllProcedure");
		List<Procedure> procedures;
		Router router = new Router();
		HttpSession session = request.getSession();
		String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
		try {
			procedures= procedureService.findAll();
			router.setPagePath(page);
			session.setAttribute(ParameterAndAttribute.PROCEDURE_LIST,procedures );
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "UserServiceException in method execute FindAllProcedure");
			router.setPagePath(PagePath.ERROR);
		}		
		return router;
	}
}
