package by.metelski.webtask.command;

public class Router {
	public enum Type{
		FORWARD,
		REDIRECT
	}
	private String pagePath;
	private Type type;
	public Router() {
		this.type = Type.FORWARD;
	}
	
	public Router(String pagePath) {
		this.pagePath = pagePath;
		this.type = Type.FORWARD;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pagePath == null) ? 0 : pagePath.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Router other = (Router) obj;
		if (pagePath == null) {
			if (other.pagePath != null)
				return false;
		} else if (!pagePath.equals(other.pagePath))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
}
