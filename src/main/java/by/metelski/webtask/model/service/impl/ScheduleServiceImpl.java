package by.metelski.webtask.model.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.ScheduleDao;
import by.metelski.webtask.model.service.ScheduleService;

public class ScheduleServiceImpl implements ScheduleService {
	private static final Logger logger = LogManager.getLogger();
	private final ScheduleDao dao;

	public ScheduleServiceImpl(ScheduleDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean addDoctorSchedule(Map<String, String> data) throws ServiceException {
		logger.log(Level.DEBUG, "add shedule data: " + data);
		boolean isAdd = true;
		Time startTime = Time.valueOf(data.get(ParameterAndAttribute.START_TIME));
		Time endTime = Time.valueOf(data.get(ParameterAndAttribute.END_TIME));		
		if(startTime.before(endTime)) {
		User user = new User.Builder()
				.setUserID(Long.parseLong(data.get(ParameterAndAttribute.DOCTOR_ID)))
				.build();
		Date date = Date.valueOf(data.get(ParameterAndAttribute.DATE));
		DoctorSchedule schedule = new DoctorSchedule.Builder()
				.setDoctor(user)
				.setStartTime(startTime)
				.setEndTime(endTime)
				.setDate(date)
				.build();
		try {
			isAdd = dao.addDoctorSchedule(schedule);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method addSchedule" + e);
			throw new ServiceException(e);
		}
		} 
		return isAdd;
	}

	@Override
	public List<DoctorSchedule> findAllSchedulesByDoctorId(long userId) throws ServiceException {
		logger.log(Level.DEBUG, "findAllSchedulesByDoctor. Id:" + userId);
		List<DoctorSchedule> schedules = new ArrayList<>();
		User user = new User.Builder()
				.setUserID(userId)
				.build();
		try {
			schedules = dao.findAllSchedulesByDoctor(user);
			logger.log(Level.DEBUG, "finded shedules:" + schedules);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method findByUser");
			throw new ServiceException(e);
		}
		return schedules;
	}

	@Override
	public Optional<DoctorSchedule> findScheduleById(long id) throws ServiceException {
		logger.log(Level.DEBUG, "findAllScheduleById. Id:" + id);
		Optional<DoctorSchedule> schedule;
		try {
			schedule = dao.FindScheduleById(id);
			logger.log(Level.DEBUG, "finded schedule:" + schedule);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method FindScheduleById, id: " + id);
			throw new ServiceException(e);
		}
		return schedule;
	}

	@Override
	public Optional<DoctorSchedule> findScheduleByDateAndDoctor(Date date, long doctorId) throws ServiceException {
		logger.log(Level.DEBUG, "findScheduleByDateAndDoctor. date:" + date+", id:"+doctorId);
		Optional<DoctorSchedule> schedule;
		try {
			schedule = dao.findScheduleByDateAndDoctor(date, doctorId);
			logger.log(Level.DEBUG, "finded schedule:" + schedule);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method findScheduleByDateAndDoctor, date: " + date+", id:"+doctorId);
			throw new ServiceException(e);
		}
		return schedule;
	}
}
