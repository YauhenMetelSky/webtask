package by.metelski.webtask.entity;

import java.time.LocalDate;

public class Appointment {
	long id;
	int userId;
	int doctorId;
	int procedureId;
	String startTime;
	LocalDate date;
	Status status;
	
	public enum Status{
		CLAIMED,CONFIRMED,ENDED
	}
	
	public Appointment() {
		
	}

	public Appointment(long id, int userId, int doctorId, int procedureId, String startTime, LocalDate date,
			Status status) {
		super();
		this.id = id;
		this.userId = userId;
		this.doctorId = doctorId;
		this.procedureId = procedureId;
		this.startTime = startTime;
		this.date = date;
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getProcedureId() {
		return procedureId;
	}

	public void setProcedureId(int procedureId) {
		this.procedureId = procedureId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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
		result = prime * result + doctorId;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + procedureId;
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + userId;
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
		if (doctorId != other.doctorId)
			return false;
		if (id != other.id)
			return false;
		if (procedureId != other.procedureId)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (status != other.status)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Appointment [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", doctorId=");
		builder.append(doctorId);
		builder.append(", procedureId=");
		builder.append(procedureId);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", date=");
		builder.append(date);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
}
