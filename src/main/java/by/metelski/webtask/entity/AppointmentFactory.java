package by.metelski.webtask.entity;

import java.sql.Date;
import java.util.Map;

import by.metelski.webtask.command.ParameterAndAttribute;

public class AppointmentFactory {
	private final static AppointmentFactory instance = new AppointmentFactory();
	
	private AppointmentFactory() {
		
	}
	public static AppointmentFactory getInstance() {
		return instance;
	}
	
	public Appointment build(Map<String,String> data) {
		Date date = Date.valueOf(data.get(ParameterAndAttribute.APPOINTMENT_DATE));
		int userId = Integer.parseInt(data.get(ParameterAndAttribute.USER_ID));
		int doctorId = Integer.parseInt(data.get(ParameterAndAttribute.DOCTOR_ID));
		int procedureId = Integer.parseInt(data.get(ParameterAndAttribute.PROCEDURE_ID));
		String startTime = data.get(ParameterAndAttribute.START_TIME);
		Appointment appointment = new Appointment();
		appointment.setUserId(userId);
		appointment.setDate(date);
		appointment.setDoctorId(doctorId);
		appointment.setProcedureId(procedureId);
		appointment.setStartTime(startTime);
		appointment.setStatus(Appointment.Status.CLAIMED);
		return appointment;
	}
}
