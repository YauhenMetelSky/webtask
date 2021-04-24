package by.metelski.webtask.controller.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.SessionAttribute;


/**
 * Servlet Filter implementation class PageFilter
 */
@WebFilter(filterName = "PageFilter", dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.INCLUDE}, urlPatterns = "*.jsp")
public class PageFilter implements Filter {
	private static final Logger logger = LogManager.getLogger();
    /**
     * Default constructor. 
     */
    public PageFilter() {

    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.log(Level.DEBUG, "filter works!");
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		String pagePath =req.getServletPath();
		logger.log(Level.DEBUG, "Current page from filter: " +pagePath);
		session.setAttribute(SessionAttribute.CURRENT_PAGE, pagePath);		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}
}
