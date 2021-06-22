package by.metelski.webtask.tag;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class CopyrightTag, custom tag.
 * 
 * @author Yauhen Metelski
 *
 */
public class CopyrightTag extends TagSupport {
	private static final Logger logger = LogManager.getLogger();
	private static final String MESSAGE = "<h6>Copyright by Yauheni Miatselski Minsk 2021</h6>";

	@Override
	public int doStartTag() throws JspTagException {
		try {
			pageContext.getOut().write(MESSAGE);
		} catch (IOException e) {
			logger.log(Level.ERROR, "IO exception in doEndTag()");
		}
		return SKIP_BODY;
	}
}