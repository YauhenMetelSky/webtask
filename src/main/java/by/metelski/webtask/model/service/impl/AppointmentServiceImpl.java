package by.metelski.webtask.model.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.entity.Appointment.Status;
import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.AppointmentDao;
import by.metelski.webtask.model.dao.ProcedureDao;
import by.metelski.webtask.model.service.AppointmentService;
import static by.metelski.webtask.command.ParameterAndAttribute.*;

/**
 * Class appointment service
 * @author Yauhen Metelski
 *
 */
public class AppointmentServiceImpl implements AppointmentService {
	private static final Logger logger = LogManager.getLogger();
	AppointmentDao appointmentDao;
	ProcedureDao procedureDao;

	public AppointmentServiceImpl(AppointmentDao appointmentDao, ProcedureDao procedureDao) {
		this.appointmentDao = appointmentDao;
		this.procedureDao = procedureDao;
	}

	@Override
	public boolean add(Map<String, String> data) throws ServiceException {
		logger.log(Level.DEBUG, "Add appointment; data" + data);
		boolean isAdded = false;
		try { //FIXME validate data
			long id = Long.parseLong(data.get(PROCEDURE_ID));
			long duration = procedureDao.findDuration(id).get().toMinutes();// FIXME pay attention return Optional
			logger.log(Level.DEBUG, "procedure duration" + duration);
			Time endTime = calculateEndTime(data.get(START_TIME), duration);
			Time startTime = Time.valueOf(data.get(START_TIME));
			Date date = Date.valueOf(data.get(APPOINTMENT_DATE));
			User client = createSimpleUser(data.get(USER_ID));
			User doctor = createSimpleUser(data.get(DOCTOR_ID));
			Procedure procedure = createSimpleProcedure(data.get(PROCEDURE_ID));
			Appointment appointment = new Appointment.Builder().setUser(client).setDoctor(doctor)
					.setProcedure(procedure).setStartTime(startTime).setEndTime(endTime).setDate(date)
					.setStatus(Status.CLAIMED).build();
			isAdded = appointmentDao.add(appointment);
		} catch (DaoException e) {
			logger.log(Level.ERROR,
					"dao exception in method add, when we try to add appointment,appointment data:" + data + ". " + e);
			throw new ServiceException(e);
		}
		return isAdded;
	}

	@Override
	public boolean change(Map<String, String> data) throws ServiceException {
		logger.log(Level.DEBUG, "Change appointment; data" + data);
		//FIXME validate data
		boolean isChanged = false;
		try {
			long id = Long.parseLong(data.get(PROCEDURE_ID));
			long duration = procedureDao.findDuration(id).get().toMinutes();// FIXME pay attention return Optional
			logger.log(Level.DEBUG, "procedure duration" + duration);
			Time endTime = calculateEndTime(data.get(START_TIME), duration);
			long appointmentId = Long.parseLong(data.get(APPOINTMENT_ID));
			Time startTime = Time.valueOf(data.get(START_TIME));
			Date date = Date.valueOf(data.get(APPOINTMENT_DATE));
			User client = createSimpleUser(data.get(USER_ID));
			User doctor = createSimpleUser(data.get(DOCTOR_ID));
			Procedure procedure = createSimpleProcedure(data.get(PROCEDURE_ID));
			Appointment appointment = new Appointment.Builder().setId(appointmentId).setUser(client).setDoctor(doctor)
					.setProcedure(procedure).setStartTime(startTime).setEndTime(endTime).setStatus(Status.CLAIMED)
					.setDate(date).build();
			isChanged = appointmentDao.changeAppointment(appointment);
		} catch (DaoException e) {
			logger.log(Level.ERROR,
					"dao exception in method add, when we try change appointment,appointment data:" + data + ". " + e);
			throw new ServiceException(e);
		}
		return isChanged;
	}

	@Override
	public Optional<Appointment> findById(long id) throws ServiceException {
		Optional<Appointment> appointment = null;
		try {
			appointment = appointmentDao.findById(id);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method findById()" + e);
			throw new ServiceException(e);
		}
		return appointment;
	}

	@Override
	public List<Appointment> findAllByStatus(Status status) throws ServiceException {
		logger.log(Level.DEBUG, "findByStatus(), status:" + status);
		List<Appointment> appointments = new ArrayList<>();
		try {
			appointments = appointmentDao.findAllByStatus(status);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method findAllByStatus()" + e);
			throw new ServiceException(e);
		}
		return appointments;
	}

	@Override
	public List<Appointment> findAllByUserId(long userId) throws ServiceException {
		logger.log(Level.DEBUG, "findAllByUserId, userId:" + userId);
		List<Appointment> appointments = new ArrayList<>();
		try {
			appointments = appointmentDao.findAllByUserId(userId);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method findAllByUserId(), " + e);
			throw new ServiceException(e);
		}
		return appointments;
	}

	private Time calculateEndTime(String startTime, long duration) {
		LocalTime tmpTime = LocalTime.parse(startTime);
		logger.log(Level.DEBUG, "tmpTime:" + tmpTime);
		Time endTime = Time.valueOf(tmpTime.plusMinutes(duration));
		logger.log(Level.DEBUG, "tmpTime:" + tmpTime + " End time: " + endTime);
		return endTime;
	}

	@Override
	public boolean changeStatus(long id, Status status) throws ServiceException {
		logger.log(Level.DEBUG, "changeStatus(), id:" + id + ", status:" + status);
		boolean changeStatusResult = false;
		try {
			changeStatusResult = appointmentDao.changeStatus(id, status);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method changeStatus(), " + e);
			throw new ServiceException(e);
		}
		return changeStatusResult;
	}

	@Override
	public List<Appointment> findAllByDoctorId(long doctorId) throws ServiceException {
		logger.log(Level.DEBUG, "findAllByDoctorId, doctorId:" + doctorId);
		List<Appointment> appointments = new ArrayList<>();
		try {
			appointments = appointmentDao.findAllByDoctorId(doctorId);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method findAllByUserId(), " + e);
			throw new ServiceException(e);
		}
		return appointments;
	}

	@Override
	public List<Appointment> findAllByDoctorIdAndDate(long doctorId, Date date) throws ServiceException {
		logger.log(Level.DEBUG, "findAllByDoctorIdAndDate, doctorId:" + doctorId + ",Date:" + date);
		List<Appointment> appointments = new ArrayList<>();
		try {
			appointments = appointmentDao.findAllByDoctorIdAndDate(doctorId, date);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method findAllByDoctorIdAndDate(), " + e);
			throw new ServiceException(e);
		}
		return appointments;
	}
	private User createSimpleUser(String id) {
		long userId = Long.parseLong(id);
		User user = new User.Builder()
				.setUserID(userId)
				.build();
		return user;
	}
	private Procedure createSimpleProcedure(String id) {
		long procedureId = Long.parseLong(id);
		Procedure procedure = new Procedure.Builder()
				.setProcedureId(procedureId)
				.build();
		return procedure;
	}
}
