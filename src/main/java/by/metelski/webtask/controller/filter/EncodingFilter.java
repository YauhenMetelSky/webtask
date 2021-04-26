package by.metelski.webtask.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter(filterName = "Encoding", urlPatterns = {"/*"},
initParams = {@WebInitParam(name = "encoding", value = "UTF-8",
        description = "Encoding Param")})
public class EncodingFilter implements Filter {
	private static final Logger logger = LogManager.getLogger();
    private String code;

    @Override
    public void init(FilterConfig fConfig) {
        code = fConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
    	logger.log(Level.DEBUG, "Encodingfilter works!");
        String codeRequest = request.getCharacterEncoding();
        if (codeRequest == null || !codeRequest.equalsIgnoreCase(code)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }

}
