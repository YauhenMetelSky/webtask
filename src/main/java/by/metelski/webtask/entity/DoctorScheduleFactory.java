package by.metelski.webtask.entity;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.entity.DoctorSchedule.Intervals;

public class DoctorScheduleFactory {
	private static final Logger logger = LogManager.getLogger();
	private final static DoctorScheduleFactory instance = new DoctorScheduleFactory();
	
	private DoctorScheduleFactory() {
		
	}
	
	public static DoctorScheduleFactory getInstance() {
		logger.log(Level.DEBUG, "return instance invoke ");
		return instance;
	}
	
	public DoctorSchedule build(Map<String,String> data) {
		logger.log(Level.DEBUG, "buil method with data: " + data);
		SortedMap<Intervals,Boolean> intervals = new TreeMap<>();
		for(Intervals interval : Intervals.values()) {
			intervals.put(interval, Boolean.parseBoolean(data.get(interval.name())));
		}
		int doctorId = Integer.parseInt(data.get(ParameterAndAttribute.DOCTOR_ID));
		int scheduleId = Integer.parseInt(data.get(ParameterAndAttribute.SCHEDULE_ID));
		return new DoctorSchedule(intervals,doctorId, scheduleId);
	}
}
