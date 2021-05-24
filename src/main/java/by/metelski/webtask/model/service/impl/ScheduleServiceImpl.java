package by.metelski.webtask.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.entity.DoctorScheduleFactory;
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
		// FIXME startTime must be less than endTime check: is schedule exist
		boolean isAdd = true;
		try {
			isAdd = dao.addDoctorSchedule(DoctorScheduleFactory.getInstance().build(data));
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method addSchedule" + e);
			throw new ServiceException(e);
		}
		return isAdd;
	}

	@Override
	public List<DoctorSchedule> findAllSchedulesByDoctor(long userId) throws ServiceException {
		logger.log(Level.DEBUG, "findAllSchedulesByDoctor. Id:" + userId);
		List<DoctorSchedule> schedules = new ArrayList<>();
		User user = new User(userId);
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
	public Optional<DoctorSchedule> FindScheduleById(long id) throws ServiceException {
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
}
