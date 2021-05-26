package by.metelski.webtask.util;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.entity.DoctorSchedule;

public class IntervalCalculator {
	private static final Logger logger = LogManager.getLogger();

	public static List<String> calculateIntervals(DoctorSchedule schedule, int incrementMin){
		List<String> intervals = new ArrayList<>();
		LocalTime startInterval = LocalTime.parse(schedule.getStartTime().toString());
		logger.log(Level.DEBUG, "start interval: " + startInterval);
		LocalTime endInterval = LocalTime.parse(schedule.getEndTime().toString());
		logger.log(Level.DEBUG, "end interval: " + endInterval);
		while (startInterval.isBefore(endInterval)) {
			intervals.add(startInterval.format(DateTimeFormatter.ISO_TIME));
			startInterval = startInterval.plusMinutes(15);
			logger.log(Level.DEBUG, "start interval: " + startInterval);
		}
		return intervals;
	}	
}
