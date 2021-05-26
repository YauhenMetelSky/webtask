package by.metelski.webtask.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.ParameterAndAttribute;

public class AppointmentFactory {
	private static final Logger logger = LogManager.getLogger();
	private static final AppointmentFactory instance = new AppointmentFactory();
	
	private AppointmentFactory() {
		
	}
	public static AppointmentFactory getInstance() {
		return instance;
	}
	//FIXME Delete
//	public Appointment buildUserAppointment(Map<String,String> data) {
//		long userId = Long.parseLong(data.get(ParameterAndAttribute.USER_ID));
//		long doctorId = Long.parseLong(data.get(ParameterAndAttribute.DOCTOR_ID));
//		long procedureId = Long.parseLong(data.get(ParameterAndAttribute.PROCEDURE_ID));
//		Time startTime = Time.valueOf(data.get(ParameterAndAttribute.START_TIME));
//		Date date = Date.valueOf(data.get(ParameterAndAttribute.APPOINTMENT_DATE));
//		Appointment appointment = new Appointment(userId,doctorId,procedureId,startTime,date);
//		return appointment;
//	}
	public Appointment buildAppointment(Map<String,String> data) {
		logger.log(Level.DEBUG, "build appointment; data" + data);
		long appointmentId = Long.parseLong(data.get(ParameterAndAttribute.APPOINTMENT_ID));
		long userId = Long.parseLong(data.get(ParameterAndAttribute.USER_ID));
		long doctorId = Long.parseLong(data.get(ParameterAndAttribute.DOCTOR_ID));
		long procedureId = Long.parseLong(data.get(ParameterAndAttribute.PROCEDURE_ID));
		Time startTime = Time.valueOf(data.get(ParameterAndAttribute.START_TIME));
		logger.log(Level.DEBUG, "add start time to data, startTime:" + startTime);
		Time endTime = Time.valueOf(data.get(ParameterAndAttribute.END_TIME));
		logger.log(Level.DEBUG, "add end time to data, endTime:" + endTime);
		Date date = Date.valueOf(data.get(ParameterAndAttribute.APPOINTMENT_DATE));//FIXME format date
		logger.log(Level.DEBUG, "add date to data, date:" + date);
		User client = new User(userId);
		User doctor = new User(doctorId);
		Procedure procedure= new Procedure(procedureId);
		Appointment appointment = new Appointment(appointmentId,client,doctor,procedure,startTime,endTime,date);
		return appointment;
	}
}
