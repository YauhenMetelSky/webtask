package by.metelski.webtask.model.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.entity.DoctorScheduleFactory;
import by.metelski.webtask.entity.Schedule;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.ScheduleDao;
import by.metelski.webtask.model.service.ScheduleService;

public class ScheduleServiceImpl implements ScheduleService {
	private static final Logger logger = LogManager.getLogger();
	private final ScheduleDao dao;
	
	public ScheduleServiceImpl(ScheduleDao dao) {
		this.dao =dao;
	}

	@Override
	public boolean addSchedule(Map<String, String> data) throws ServiceException {
		logger.log(Level.DEBUG, "add shedule daat: " + data);
		boolean isAdd = true;
         try {
			isAdd = dao.addSchedule(DoctorScheduleFactory.getInstance().build(data));
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method addSchedule" + e);
			throw new ServiceException(e);
		}
		return isAdd;
	}

	@Override
	public boolean changeSchedule(Schedule schedule) throws ServiceException  {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Schedule> findAll() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Schedule> findByDate(Date date) throws ServiceException  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Schedule> findByUser(User user) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Schedule> findById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Schedule> findAllActiveSchedules() throws ServiceException {
		List<Schedule> schedules = new ArrayList<>();
		try {
			schedules = dao.findAllActiveSchedules();
		}catch(DaoException e) {
			logger.log(Level.ERROR, "dao exception in method findAllActiveSchedules");
			throw new ServiceException(e);
		}
		// TODO Auto-generated method stub
		return schedules;
	}
}
