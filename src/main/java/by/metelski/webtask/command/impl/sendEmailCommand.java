package by.metelski.webtask.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.util.MailSender;

public class sendEmailCommand implements Command{
	private static final Logger logger = LogManager.getLogger();
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		logger.log(Level.DEBUG, "sendEmailCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
		router.setPagePath(page);
		request.setAttribute(ParameterAndAttribute.MESSAGE, "Message sent");
		String emailTo = request.getParameter(ParameterAndAttribute.EMAIL_TO);
		logger.log(Level.DEBUG, "Sending email to: " +emailTo);
		MailSender.sendEmail(emailTo,"TestEmail","Hello from DoctorPro");//FIXME magic string
		  return router;
	}
}
