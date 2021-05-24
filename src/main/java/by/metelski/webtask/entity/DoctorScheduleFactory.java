package by.metelski.webtask.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.ParameterAndAttribute;

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
		User user = new User(Long.parseLong(data.get(ParameterAndAttribute.DOCTOR_ID)));
		Time startTime = Time.valueOf(data.get(ParameterAndAttribute.START_TIME));
		Time endTime = Time.valueOf(data.get(ParameterAndAttribute.END_TIME));
		Date date = Date.valueOf(data.get(ParameterAndAttribute.DATE));
		return new DoctorSchedule(user,startTime,endTime,date);
	}
}
