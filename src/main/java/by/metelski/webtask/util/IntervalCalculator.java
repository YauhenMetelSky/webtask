package by.metelski.webtask.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.entity.DoctorSchedule;

/**
 * Class interval calculator
 * @author Yauhen Metelski
 *
 */
public class IntervalCalculator {
	private static final Logger logger = LogManager.getLogger();

	/**
	 * @param schedule
	 * @param incrementMin - increment in minutes, difference between interval's start and end
	 * @param appointments
	 * @return List<String> free time intervals
	 */
	public static List<String> calculateIntervals(DoctorSchedule schedule, int incrementMin,List<Appointment> appointments) {
		List<String> intervals = new ArrayList<>();
		LocalTime startInterval = LocalTime.parse(schedule.getStartTime().toString());
		logger.log(Level.DEBUG, "start interval: " + startInterval);
		LocalTime endInterval = LocalTime.parse(schedule.getEndTime().toString());
		logger.log(Level.DEBUG, "end interval: " + endInterval);

		while (startInterval.isBefore(endInterval)) {
			if (isFree(startInterval,appointments)) {
				intervals.add(startInterval.format(DateTimeFormatter.ISO_TIME));
				logger.log(Level.DEBUG, "start interval: " + startInterval);
			}
			startInterval = startInterval.plusMinutes(incrementMin);
		}
		return intervals;
	}

	/**
	 * @param startInterval
	 * @param appointments
	 * @return boolean true if interval free
	 */
	private static boolean isFree(LocalTime startInterval,List<Appointment> appointments) {
		logger.log(Level.DEBUG, "isFree method interval: " + startInterval);
		logger.log(Level.DEBUG, "appointments: " + appointments);
		if(appointments.isEmpty()) {
			return true;
		}
		for (Appointment appointment : appointments) {
			if (startInterval.equals(LocalTime.parse(appointment.getStartTime().toString()))||
					startInterval.isAfter(LocalTime.parse(appointment.getStartTime().toString()))
					&& startInterval.isBefore(LocalTime.parse(appointment.getEndTime().toString()))) {
				logger.log(Level.DEBUG, "interval: " + startInterval +" is busy");
				return false;
			}
		}
		logger.log(Level.DEBUG, "interval: " + startInterval +" is free");
		return true;
	}
}
