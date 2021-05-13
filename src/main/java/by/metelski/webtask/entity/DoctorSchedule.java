package by.metelski.webtask.entity;

import java.util.SortedMap;

public class DoctorSchedule {
	private int id;
	private SortedMap<Intervals,Boolean> intervals;
	private int doctorId;
	private int scheduleId; 
	
   public enum Intervals {
	   INTERVAL_09_00("9_00"),
	   INTERVAL_09_30("9_30"),
	   INTERVAL_10_00("10_00"),
	   INTERVAL_10_30("10_30"),
	   INTERVAL_11_00("11_00"),
	   INTERVAL_11_30("11_30"),
	   INTERVAL_12_00("12_00"),
	   INTERVAL_12_30("12_30"),
	   INTERVAL_13_00("13_00"),
	   INTERVAL_13_30("13_30"),
	   INTERVAL_14_00("14_00"),
	   INTERVAL_14_30("14_30"),
	   INTERVAL_15_00("15_00"),
	   INTERVAL_15_30("15_30"),
	   INTERVAL_16_00("16_00"),
	   INTERVAL_16_30("16_30"),
	   INTERVAL_17_00("17_00"),
	   INTERVAL_17_30("17_30"),
	   INTERVAL_18_00("18_00"),
	   INTERVAL_18_30("18_30"),
	   INTERVAL_19_00("19_00"),
	   INTERVAL_19_30("19_30"),
	   INTERVAL_20_00("20_00"),
	   INTERVAL_20_30("20_30");   
	   String value;
	   
	   private Intervals(String value) {
		   this.value=value;	
	   }
   }
	
	public DoctorSchedule() {
		
	}
	public DoctorSchedule(int doctorId,SortedMap<Intervals,Boolean> intervals) {
		this.doctorId = doctorId;
	    this.intervals = intervals;
	}
	public DoctorSchedule(int id,int doctorId,SortedMap<Intervals,Boolean> intervals) {
		this.id = id;
		this.doctorId = doctorId;
	    this.intervals = intervals;
	}
	public DoctorSchedule(SortedMap<Intervals, Boolean> intervals, int doctorId, int scheduleId) {
		this.intervals = intervals;
		this.doctorId = doctorId;
		this.scheduleId = scheduleId;
	}
	
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public int getId() {
		return id;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public SortedMap<Intervals, Boolean> getIntervals() {
		return intervals;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + doctorId;
		result = prime * result + id;
		result = prime * result + ((intervals == null) ? 0 : intervals.hashCode());
		result = prime * result + scheduleId;
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
		if (doctorId != other.doctorId)
			return false;
		if (id != other.id)
			return false;
		if (intervals == null) {
			if (other.intervals != null)
				return false;
		} else if (!intervals.equals(other.intervals))
			return false;
		if (scheduleId != other.scheduleId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DoctorSchedule [id=");
		builder.append(id);
		builder.append(", intervals=");
		builder.append(intervals);
		builder.append(", doctorId=");
		builder.append(doctorId);
		builder.append(", scheduleId=");
		builder.append(scheduleId);
		builder.append("]");
		return builder.toString();
	}		
}
