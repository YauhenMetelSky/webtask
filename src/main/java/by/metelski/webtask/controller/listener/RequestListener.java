package by.metelski.webtask.controller.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.ParameterAndAttribute;
@WebListener
public class RequestListener implements ServletRequestListener {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		logger.log(Level.DEBUG, "RequestDestroyedIvent");
		HttpServletRequest request = (HttpServletRequest)sre.getServletRequest();
		HttpSession session = request.getSession();
		session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, null);
		ServletRequestListener.super.requestDestroyed(sre);
		//FIXME didn't work
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		logger.log(Level.DEBUG, "RequestInitializedIvent");
		ServletRequestListener.super.requestInitialized(sre);
	}
}
