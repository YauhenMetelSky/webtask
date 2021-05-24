package by.metelski.webtask.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.SortedMap;

public class DoctorSchedule {
	private long id;
	private User doctor;
	private Time startTime;
	private Time endTime;
	private Date date;
	
	public DoctorSchedule() {
		
	}
	
	public DoctorSchedule(User doctor, Time startTime, Time endTime,Date date) {
		this.doctor = doctor;
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
	}
		
	public DoctorSchedule(long id, User doctor, Time startTime, Time endTime, Date date) {
		this.id = id;
		this.doctor = doctor;
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctorId(User doctor) {
		this.doctor = doctor;
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

	public long getId() {
		return id;
	}
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
		DoctorSchedule other = (DoctorSchedule) obj;
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
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DoctorSchedule [id=");
		builder.append(id);
		builder.append(", doctor=");
		builder.append(doctor);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}
}
