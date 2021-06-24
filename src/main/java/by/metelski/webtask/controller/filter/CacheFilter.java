package by.metelski.webtask.controller.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Cache filter. Set cache control instructions
 * @author Yauhen Metelski
 *
 */

@WebFilter(urlPatterns = { "/*" })
public class CacheFilter  implements Filter{
	private static final Logger logger = LogManager.getLogger();
	private static final String HTTP_HEADER_CACHE_CONTROL = "Cache-Control";
	private static final String CACHING_INSTRUCTIONS = "no-store, no-cache, must-revalidate";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.log(Level.DEBUG, "cache doFilter");
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setHeader(HTTP_HEADER_CACHE_CONTROL, CACHING_INSTRUCTIONS);
		chain.doFilter(request, response);	
	}
}