package by.metelski.webtask.command;

/**
 * 
 * @author Yauhen Metelski
 *
 */
/**
 * @author Yauhen Metelski
 *
 */
public class Router {
	/**
	 * Inner enum describe all possibles router type
	 * @author Yauhen Metelski
	 *
	 */
	public enum Type{
		FORWARD,
		REDIRECT
	}
	private String pagePath;
	private Type type;
	
	/**
	 * Constructor set default route type: Forward 
	 */
	public Router() {
		this.type = Type.FORWARD;
	}
	
	/**
	 * Constructor set default route type: Forward 
	 * @param pagePath
	 */
	public Router(String pagePath) {
		this.pagePath = pagePath;
		this.type = Type.FORWARD;
	}

	/**
	 * Constructor
	 * @param pagePath
	 * @param type (type of router)
	 */
	public Router(String pagePath, Type type) {
		this.pagePath = pagePath;
		this.type = type;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}	
}
