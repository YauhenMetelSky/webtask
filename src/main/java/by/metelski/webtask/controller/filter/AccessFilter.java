package by.metelski.webtask.controller.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.ParameterAndAttribute;

/**
 * Access filter. 
 * @author Yauhen Metelski
 *
 */

@WebFilter(filterName = "AccessFilter", dispatcherTypes = { DispatcherType.REQUEST,
		DispatcherType.FORWARD }, urlPatterns = "*.jsp")
public class AccessFilter implements Filter {
	private static final Logger logger = LogManager.getLogger();
	private static final Set<String> ALLOWED_PATH_GUEST = new HashSet<>(
			Arrays.asList("/index.jsp", "/jsp/about.jsp", "/jsp/contact.jsp", "/jsp/main.jsp", "/jsp/services.jsp",
					"/jsp/signin.jsp", "/jsp/signup.jsp", "/jsp/error.jsp"));

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.log(Level.DEBUG, "access doFilter");
		HttpServletRequest req = (HttpServletRequest) request;
		String pagePath = req.getServletPath();
		logger.log(Level.DEBUG, "page path:" + pagePath);
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		boolean loggedIn = (session != null && session.getAttribute(ParameterAndAttribute.USER) != null);
		logger.log(Level.DEBUG, "loggedIn:" + loggedIn);
		boolean allowedPath = ALLOWED_PATH_GUEST.contains(pagePath);
		logger.log(Level.DEBUG, "allowedPath:" + allowedPath);
		if (loggedIn || allowedPath) {
			logger.log(Level.DEBUG, "YOU CAN do this:");
			chain.doFilter(request, response);
		} else {
			logger.log(Level.DEBUG, "YOU CAN'T do this:");
			logger.log(Level.DEBUG, "page path:" + req.getContextPath() + PagePath.MAIN);
			resp.sendRedirect(req.getContextPath() + PagePath.TO_MAIN_PAGE);
		}
	}
}
