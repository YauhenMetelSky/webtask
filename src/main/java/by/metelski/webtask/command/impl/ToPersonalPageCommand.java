package by.metelski.webtask.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.entity.User;

public class ToPersonalPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.INFO, "ToPersonalPageCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(ParameterAndAttribute.USER);
		String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
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
				router.setPagePath(PagePath.USER);
			break;
			case DOCTOR:
				router.setPagePath(PagePath.DOCTOR);
				break;
			}
		}else {
			router.setPagePath(PagePath.SIGN_IN);
		}
		return router;
	}
}
