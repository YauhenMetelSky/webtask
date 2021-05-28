package by.metelski.webtask.entity;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
	private long id;
	private User user;
	private User doctor;
	private Procedure procedure;
	private Time startTime;
	private Time endTime;
	private Date date;
	private Status status;
	
	public enum Status{
		CLAIMED,CONFIRMED,CANCELED,ENDED
	}
	
	private Appointment() {
		
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getDoctor() {
		return doctor;
	}
	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}
	public Procedure getProcedure() {
		return procedure;
	}
	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public long getId() {
		return id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((procedure == null) ? 0 : procedure.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Appointment other = (Appointment) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (doctor == null) {
			if (other.doctor != null)
				return false;
		} else if (!doctor.equals(other.doctor))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (id != other.id)
			return false;
		if (procedure == null) {
			if (other.procedure != null)
				return false;
		} else if (!procedure.equals(other.procedure))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (status != other.status)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Appointment [id=");
		builder.append(id);
		builder.append(", user=");
		builder.append(user);
		builder.append(", doctor=");
		builder.append(doctor);
		builder.append(", procedure=");
		builder.append(procedure);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", date=");
		builder.append(date);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}	
	public static class Builder{
		private Appointment newAppointment;
		
		public Builder() {
			newAppointment= new Appointment();
		}
		public Builder setId(long id) {
			newAppointment.id=id;
			return this;
		}
		public Builder setUser(User user) {
			newAppointment.user=user;
			return this;
		}
		public Builder setDoctor(User doctor) {
			newAppointment.doctor=doctor;
			return this;
		}
		public Builder setProcedure(Procedure procedure) {
			newAppointment.procedure=procedure;
			return this;
		}
		public Builder setStartTime(Time startTime) {
			newAppointment.startTime=startTime;
			return this;
		}
		public Builder setEndTime(Time enTime) {
			newAppointment.endTime=enTime;
			return this;
		}
		public Builder setDate(Date date) {
			newAppointment.date=date;
			return this;
		}
		public Builder setStatus(Status status) {
			newAppointment.status=status;
			return this;
		}
		public Appointment build() {
			return newAppointment;
		}
	}
}
