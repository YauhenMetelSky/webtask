package by.metelski.webtask.model.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.entity.Appointment.Status;
import by.metelski.webtask.entity.AppointmentFactory;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.AppointmentDao;
import by.metelski.webtask.model.dao.ProcedureDao;
import by.metelski.webtask.model.dao.impl.AppointmentDaoImpl;
import by.metelski.webtask.model.dao.impl.ProcedureDaoImpl;
import by.metelski.webtask.model.service.AppointmentService;

public class AppointmentServiceImpl implements AppointmentService {
	private static final Logger logger = LogManager.getLogger();
	AppointmentDao appointmentDao = new AppointmentDaoImpl();
	ProcedureDao procedureDao = new ProcedureDaoImpl();// FIXME inject in constructor

	@Override
	public boolean add(Map<String, String> data) throws ServiceException {
		logger.log(Level.DEBUG, "Add appointment; data" + data);
		// TODO validate data
		boolean isAdded = false;
		Appointment appointment = null;
		try {
			long id = Long.parseLong(data.get(ParameterAndAttribute.PROCEDURE_ID));
			int duration = procedureDao.findDuration(id);
			logger.log(Level.DEBUG, "procedure duration" + duration);
			String startTime = data.get(ParameterAndAttribute.START_TIME);
			logger.log(Level.DEBUG, "startTime:" + startTime);
			LocalTime tmpTime = LocalTime.parse(startTime);
			logger.log(Level.DEBUG, "tmpTime:" + tmpTime);
			Time endTime = Time.valueOf(tmpTime.plusMinutes(duration));
			logger.log(Level.DEBUG, "tmpTime:" + tmpTime + " End time: " + endTime);
			data.put(ParameterAndAttribute.END_TIME, endTime.toString());
			appointment = AppointmentFactory.getInstance().buildAppointment(data);
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
}
