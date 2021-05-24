package by.metelski.webtask.entity;

import java.math.BigDecimal;
import java.time.Duration;

public class Procedure {
	private long procedureId;
	private String name;
	private String imageName;
	private BigDecimal price;
	private boolean isActive;
	private String description; 
	private Duration duration;
	
	public Procedure() {
	
	}
	public Procedure(long procedureId) {
		this.procedureId=procedureId;
	}
	public Procedure(long procedureId, String procedureName, String imageName, BigDecimal price,boolean isActive,String description,Duration duration) {
		this.procedureId = procedureId;
		this.name = procedureName;
		this.imageName = imageName;
		this.price = price;
		this.isActive= isActive;
		this.description=description;
		this.duration=duration;
	}
	public String getName() {
		return name;
	}
	public void setName(String Name) {
		this.name = Name;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public long getProcedureId() {
		return procedureId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActive() {
		return isActive;
	}
	
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((imageName == null) ? 0 : imageName.hashCode());
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + (int) (procedureId ^ (procedureId >>> 32));
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
		Procedure other = (Procedure) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (imageName == null) {
			if (other.imageName != null)
				return false;
		} else if (!imageName.equals(other.imageName))
			return false;
		if (isActive != other.isActive)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (procedureId != other.procedureId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Procedure [procedureId=");
		builder.append(procedureId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", imageName=");
		builder.append(imageName);
		builder.append(", price=");
		builder.append(price);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append(", description=");
		builder.append(description);
		builder.append(", duration=");
		builder.append(duration);
		builder.append("]");
		return builder.toString();
	}
}
