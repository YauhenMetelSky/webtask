package by.metelski.webtask.model.service.impl;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.entity.AppointmentFactory;
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
	ProcedureDao procedureDao = new ProcedureDaoImpl();
	private final int STANDARD_INTERVAL_MINUTE = 30;

	@Override
	public boolean add(Map<String, String> data) throws ServiceException {
		// TODO validate data
		Date date = Date.valueOf(data.get(ParameterAndAttribute.APPOINTMENT_DATE));
		int doctorId = Integer.parseInt(data.get(ParameterAndAttribute.DOCTOR_ID));
		LocalTime interval = LocalTime.parse(data.get(ParameterAndAttribute.START_TIME));//TODO DATE from SQL
		boolean isAdded = false;
		int procedureId = Integer.parseInt(data.get(ParameterAndAttribute.PROCEDURE_ID));
		int intervalsNumber = findIntervalsNumber(procedureId);
		boolean isAppointmentTimeFree=false;
		int doctorScheduleId=0;//FIXME
		for(int i =0;i<intervalsNumber;i++) {	
			isAppointmentTimeFree=isIntervalFree(date, doctorId, interval);
			interval=interval.plusMinutes(STANDARD_INTERVAL_MINUTE);
		}
		if (isAppointmentTimeFree) {//TODO check and change interval
			Appointment appointment = AppointmentFactory.getInstance().build(data);
			try {
				LocalTime tmpInterval = LocalTime.parse(data.get(ParameterAndAttribute.START_TIME));
				for(int i =0;i<intervalsNumber;i++) {	
					changeIntervalsStatus(tmpInterval,doctorScheduleId);
					tmpInterval=tmpInterval.plusMinutes(STANDARD_INTERVAL_MINUTE);
				}
				isAdded = appointmentDao.add(appointment);
			} catch (DaoException e) {
				logger.log(Level.ERROR,
						"dao exception in method add, when we try to add appointment:" + appointment + ". " + e);
				throw new ServiceException(e);
			}
		}
		return isAdded;
	}
	private boolean changeIntervalsStatus(LocalTime startTime,int scheduleId) throws DaoException {
		return appointmentDao.changeIntervalstatus(startTime.toString().replace(':','_'),scheduleId);//TODO magic chars		
	}

	private int findIntervalsNumber(int procedureId) throws ServiceException {
		int intervalsNumber = 0;
		try {
			intervalsNumber = procedureDao.findDuration(procedureId) / STANDARD_INTERVAL_MINUTE;
			if (intervalsNumber < 1) {
				intervalsNumber = 1;
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR,
					"dao exception in method findIntervalsNumber, procedureId:" + procedureId + ". " + e);
			throw new ServiceException(e);
		}
		return intervalsNumber;
	}

	private boolean isIntervalFree(Date date, int doctorId, LocalTime startIntervall) throws ServiceException {
		boolean isFree = false;
		String interval = startIntervall.toString().replace(':', '_');//TODO Magic chars
		try {
			isFree = appointmentDao.isIntervalFree(date, interval, doctorId);
			if(!isFree) {
				return false;//TODO change realization
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR,
					"dao exception in method isAppointmentTimeFree. " + e);
			throw new ServiceException(e);
		}
		return isFree;
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
