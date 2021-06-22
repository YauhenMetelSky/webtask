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
import by.metelski.webtask.validator.DataValidator;

/**
 * Class appointment service
 * @author Yauhen Metelski
 *
 */
public class ScheduleServiceImpl implements ScheduleService {
	private static final Logger logger = LogManager.getLogger();
	private final ScheduleDao dao;
	private final int numberOfSchedulesInPage = 10;

	public ScheduleServiceImpl(ScheduleDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean addDoctorSchedule(Map<String, String> data) throws ServiceException {
		logger.log(Level.DEBUG, "add shedule data: " + data);
		boolean isAdd = false;
		if (!checkAddScheduleData(data)) {
			return isAdd;
		}
		Time startTime = Time.valueOf(data.get(ParameterAndAttribute.START_TIME));
		Time endTime = Time.valueOf(data.get(ParameterAndAttribute.END_TIME));
		if (startTime.before(endTime)) {
			long doctorId = Long.parseLong(data.get(ParameterAndAttribute.DOCTOR_ID));
			User user = createSimpleUser(doctorId);
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
		User user = createSimpleUser(userId);
		try {
			schedules = dao.findAllSchedulesByDoctor(user);
			logger.log(Level.DEBUG, "finded shedules:" + schedules);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method findAllSchedulesByDoctor");
			throw new ServiceException(e);
		}
		return schedules;
	}

	@Override
	public List<DoctorSchedule> findAllActiveSchedulesByDoctor(long userId) throws ServiceException {
		logger.log(Level.DEBUG, "findAllActiveSchedulesByDoctor. Id:" + userId);
		List<DoctorSchedule> schedules = new ArrayList<>();
		User user = createSimpleUser(userId);
		try {
			schedules = dao.findAllActiveSchedulesByDoctor(user);
			logger.log(Level.DEBUG, "finded shedules:" + schedules);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method findAllActiveSchedulesByDoctor");
			throw new ServiceException(e);
		}
		return schedules;
	}

	@Override
	public Optional<DoctorSchedule> findScheduleById(long id) throws ServiceException {
		logger.log(Level.DEBUG, "findAllScheduleById. Id:" + id);
		Optional<DoctorSchedule> schedule;
		try {
			schedule = dao.findScheduleById(id);
			logger.log(Level.DEBUG, "finded schedule:" + schedule);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method FindScheduleById, id: " + id);
			throw new ServiceException(e);
		}
		return schedule;
	}

	@Override
	public Optional<DoctorSchedule> findScheduleByDateAndDoctor(Date date, long doctorId) throws ServiceException {
		logger.log(Level.DEBUG, "findScheduleByDateAndDoctor. date:" + date + ", id:" + doctorId);
		Optional<DoctorSchedule> schedule;
		try {
			schedule = dao.findScheduleByDateAndDoctor(date, doctorId);
			logger.log(Level.DEBUG, "finded schedule:" + schedule);
		} catch (DaoException e) {
			logger.log(Level.ERROR,
					"dao exception in method findScheduleByDateAndDoctor, date: " + date + ", id:" + doctorId);
			throw new ServiceException(e);
		}
		return schedule;
	}

	@Override
	public boolean changeDoctorSchedule(Map<String, String> data) throws ServiceException {
		logger.log(Level.DEBUG, "Change schedule; data" + data);
		boolean isChanged = false;
		if (!checkChangeScheduleData(data)) {
			return isChanged;
		}
		try {
			long id = Long.parseLong(data.get(ParameterAndAttribute.DOCTOR_SCHEDULE_ID));
			Time startTime = Time.valueOf(data.get(ParameterAndAttribute.START_TIME));
			Time endTime = Time.valueOf(data.get(ParameterAndAttribute.END_TIME));
			Date date = Date.valueOf(data.get(ParameterAndAttribute.DATE));
			DoctorSchedule doctorSchedule = new DoctorSchedule.Builder()
					.setId(id)
					.setStartTime(startTime)
					.setEndTime(endTime)
					.setDate(date)
					.build();
			isChanged = dao.changeDoctorSchedule(doctorSchedule);
		} catch (DaoException e) {
			logger.log(Level.ERROR,
					"dao exception in method changeDoctorSchedule, when we try change schedule,schedule data:" + data
							+ ". " + e);
			throw new ServiceException(e);
		}
		return isChanged;
	}

	@Override
	public boolean changeFieldIsActive(long scheduleId, boolean isActive) throws ServiceException {
		logger.log(Level.DEBUG, "Change schedule is_active field for:" + isActive + "; schedule id:" + scheduleId);
		boolean isChanged = false;
		try {
			isChanged = dao.changeFieldIsActive(scheduleId, isActive);
		} catch (DaoException e) {
			logger.log(Level.ERROR,
					"dao exception in method changeFieldIsActive, when we try change schedule,schedule id:" + scheduleId
							+ ". " + e);
			throw new ServiceException(e);
		}
		return isChanged;
	}

	@Override
	public List<DoctorSchedule> findAllSchedulesFromRow(int pageNumber) throws ServiceException {
		logger.log(Level.DEBUG, "findAllSchedulesFromRow(), row:" + pageNumber);
		List<DoctorSchedule> schedules = new ArrayList<>();
		int fromRow;
		if (pageNumber > 1) {
			fromRow = (pageNumber - 1) * numberOfSchedulesInPage;
		} else {
			fromRow = 0;
		}
		try {
			schedules = dao.findAllSchedulesFromRow(fromRow, numberOfSchedulesInPage);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method findAllSchedulesFromRow." + e);
			throw new ServiceException(e);
		}
		return schedules;
	}

	@Override
	public int findNumberOfPages() throws ServiceException {
		int numberOfPages;
		int numberOfSchedules;
		try {
			numberOfSchedules = dao.findNumberOfRows();
			numberOfPages = calculateNumberOfPages(numberOfSchedules);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method findNumberOfPages." + e);
			throw new ServiceException(e);
		}
		return numberOfPages;
	}

	@Override
	public int findNumberOfPagesDoctorsShedules(long doctorId) throws ServiceException {
		int numberOfPages;
		int numberOfSchedules;
		try {
			numberOfSchedules = dao.findNumberOfRowsDoctorsSchedule(doctorId);
			numberOfPages = calculateNumberOfPages(numberOfSchedules);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method findNumberOfPages." + e);
			throw new ServiceException(e);
		}
		return numberOfPages;
	}

	@Override
	public List<DoctorSchedule> findAllSchedulesByDoctorIdFromRow(long userId, int pageNumber) throws ServiceException {
		logger.log(Level.DEBUG, "findAllSchedulesFromRow(), row:" + pageNumber);
		List<DoctorSchedule> schedules = new ArrayList<>();
		int fromRow;
		if (pageNumber > 1) {
			fromRow = (pageNumber - 1) * numberOfSchedulesInPage;
		} else {
			fromRow = 0;
		}
		try {
			schedules = dao.findAllSchedulesFromRow(fromRow, numberOfSchedulesInPage);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method findAllSchedulesFromRow." + e);
			throw new ServiceException(e);
		}
		return schedules;
	}

	/**
	 * @param numberOfSchedules
	 * @return int number of page
	 */
	private int calculateNumberOfPages(int numberOfSchedules) {
		int numberOfPages;
		if (numberOfSchedules > numberOfSchedulesInPage) {
			numberOfPages = (int) Math.ceil((double) numberOfSchedules / numberOfSchedulesInPage);
		} else {
			numberOfPages = 1;
		}
		return numberOfPages;
	}

	/**
	 * @param data
	 * @return boolean result of checking
	 */
	private boolean checkAddScheduleData(Map<String, String> data) {
		boolean isValid = false;
		if (!DataValidator.isTimeFormatValid(data.get(ParameterAndAttribute.START_TIME))) {
			return isValid;
		}
		if (!DataValidator.isTimeFormatValid(data.get(ParameterAndAttribute.END_TIME))) {
			return isValid;
		}
		if (!DataValidator.isOnlyNumbers(data.get(ParameterAndAttribute.DOCTOR_ID))) {
			return isValid;
		}
		if (!DataValidator.isDateFormatValid(data.get(ParameterAndAttribute.DATE))) {
			return isValid;
		}
		isValid = true;
		return isValid;
	}
	/**
	 * @param id
	 * @return new User, that has only one field id
	 */
	private User createSimpleUser(long id) {
		User user = new User.Builder()
				.setUserID(id)
				.build();
		return user;
	}

	/**
	 * @param data
	 * @return boolean result of checking
	 */
	private boolean checkChangeScheduleData(Map<String, String> data) {
		boolean isValid = false;
		if (!DataValidator.isTimeFormatValid(data.get(ParameterAndAttribute.START_TIME))) {
			return isValid;
		}
		if (!DataValidator.isTimeFormatValid(data.get(ParameterAndAttribute.END_TIME))) {
			return isValid;
		}
		if (!DataValidator.isOnlyNumbers(data.get(ParameterAndAttribute.DOCTOR_SCHEDULE_ID))) {
			return isValid;
		}
		if (!DataValidator.isDateFormatValid(data.get(ParameterAndAttribute.DATE))) {
			return isValid;
		}
		isValid = true;
		return isValid;
	}
}
