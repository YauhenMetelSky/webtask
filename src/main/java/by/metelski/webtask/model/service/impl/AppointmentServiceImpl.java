package by.metelski.webtask.model.service.impl;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.AppointmentDao;
import by.metelski.webtask.model.dao.impl.AppointmentDaoImpl;
import by.metelski.webtask.model.service.AppointmentService;

public class AppointmentServiceImpl implements AppointmentService{
	private static final Logger logger = LogManager.getLogger();
	AppointmentDao appointmentDao = new AppointmentDaoImpl();
	@Override
	public boolean add(Map<String, String> data) throws ServiceException {
		//TODO validate data
		Appointment appointment = new Appointment();
		LocalDate date = LocalDate.parse(data.get(ParameterAndAttribute.APPOINTMENT_DATE));
		int userId = Integer.parseInt(data.get(ParameterAndAttribute.USER_ID));
		int doctorId = Integer.parseInt(data.get(ParameterAndAttribute.DOCTOR_ID));
		int procedureId = Integer.parseInt(data.get(ParameterAndAttribute.PROCEDURE_ID));
		String startTime = data.get(ParameterAndAttribute.START_TIME);
		appointment.setUserId(userId);
		appointment.setDate(date);
		appointment.setDoctorId(doctorId);
		appointment.setProcedureId(procedureId);
		appointment.setStartTime(startTime);
		appointment.setStatus(Appointment.Status.CLAIMED);
				boolean isAdded = false;
				try {
					isAdded = appointmentDao.add(appointment);
				} catch (DaoException e) {
					logger.log(Level.ERROR,
							"dao exception in method add, when we try to add appointment:" + appointment + ". " + e);
					throw new ServiceException(e);
				}		
		return isAdded;
	}

	@Override
	public boolean changeAppointment(Appointment appointment) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean change(Map<String, String> data) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Appointment> findAll() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Appointment> findById(long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
