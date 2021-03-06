package by.metelski.webtask.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.Message;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.util.MailSender;

/**
 * Send email command
 * @author Yauhen Metelski
 *
 */
public class SendEmailCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		logger.log(Level.DEBUG, "sendEmailCommand");
		Router router = new Router();
		HttpSession session = request.getSession();
		String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
		router.setPagePath(page);
		request.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.EMAIL_SEND);
		String emailTo = request.getParameter(ParameterAndAttribute.EMAIL_TO);
		String emailText = request.getParameter(ParameterAndAttribute.EMAIL_TEXT);
		logger.log(Level.DEBUG, "Sending email to: " + emailTo);
		MailSender.sendEmail(emailTo, Message.EMAIL_SUBJECT, emailText);
		return router;
	}
}
