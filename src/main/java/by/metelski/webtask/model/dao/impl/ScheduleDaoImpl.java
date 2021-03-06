package by.metelski.webtask.model.dao.impl;

import static by.metelski.webtask.model.dao.ColumnName.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.entity.User.Role;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.model.connection.ConnectionPool;
import by.metelski.webtask.model.dao.ScheduleDao;

/**
 * Class schedule dao
 * @author Yauhen Metelski
 *
 */
public class ScheduleDaoImpl implements ScheduleDao {
	private static final Logger logger = LogManager.getLogger();
	private static final String SQL_ADD_DOCTOR_SCHEDULE = "INSERT INTO doctor_schedules (doctor_id,start_time,end_time,date) values(?,?,?,?)";
	private static final String SQL_CHANGE_DOCTOR_SCHEDULE = "UPDATE doctor_schedules SET start_time=?,end_time=?,date=? WHERE id_doctor_schedule=?";
	private static final String SQL_CHANGE_DOCTOR_SCHEDULE_IS_ACTIVE = "UPDATE doctor_schedules SET is_active=? WHERE id_doctor_schedule=?";
	private static final String SQL_COUNT_ALL_SCHEDULES = "SELECT COUNT(*) FROM doctor_schedules";
	private static final String SQL_COUNT_ALL_DOCTORS_SCHEDULES = "SELECT COUNT(*) FROM doctor_schedules WHERE doctor_id=?";
	private static final String SQL_FIND_ALL_DOCTORS_SCHEDULES_FROM_ROW = "SELECT ds.id_doctor_schedule,d.user_id,d.name,d.surname,d.email,d.phone,d.is_blocked,d.role,ds.start_time,ds.end_time,ds.date,ds.is_active FROM doctor_schedules ds JOIN users d ON doctor_id=user_id WHERE doctor_id=? ORDER BY ds.date DESC LIMIT ?,?";
	private static final String SQL_FIND_ALL_SCHEDULES_FROM_ROW = "SELECT ds.id_doctor_schedule,d.user_id,d.name,d.surname,d.email,d.phone,d.is_blocked,d.role,ds.start_time,ds.end_time,ds.date,ds.is_active FROM doctor_schedules ds JOIN users d ON doctor_id=user_id ORDER BY ds.date DESC LIMIT ?,?";
	private static final String SQL_FIND_SCHEDULES_BY_DOCTOR_ID = "SELECT ds.id_doctor_schedule,d.user_id,d.name,d.surname,d.email,d.phone,d.is_blocked,d.role,ds.start_time,ds.end_time,ds.date,ds.is_active FROM doctor_schedules ds JOIN users d ON doctor_id=user_id WHERE doctor_id=? ORDER BY ds.date DESC";
	private static final String SQL_FIND_ACTIVE_SCHEDULES_BY_DOCTOR_ID = "SELECT ds.id_doctor_schedule,d.user_id,d.name,d.surname,d.email,d.phone,d.is_blocked,d.role,ds.start_time,ds.end_time,ds.date,ds.is_active FROM doctor_schedules ds JOIN users d ON doctor_id=user_id WHERE doctor_id=? AND date>=? AND ds.is_active=true ORDER BY ds.date DESC";
	private static final String SQL_FIND_SCHEDULE_BY_ID = "SELECT ds.id_doctor_schedule,user_id,name,surname,email,phone,is_blocked,role, ds.start_time,ds.end_time,ds.date,ds.is_active FROM doctor_schedules ds JOIN users ON doctor_id=user_id WHERE ds.id_doctor_schedule=?";
	private static final String SQL_FIND_SCHEDULE_BY_DOCTOR_ID_AND_DATE = "SELECT ds.id_doctor_schedule,user_id,name,surname,email,phone,is_blocked,role, ds.start_time,ds.end_time,ds.date,ds.is_active FROM doctor_schedules ds JOIN users ON doctor_id=user_id WHERE doctor_id=? AND ds.date=?";
	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public boolean addDoctorSchedule(DoctorSchedule schedule) throws DaoException {
		logger.log(Level.INFO, "Try to add doctor shedule in db" + schedule);
		boolean scheduleAdded = false;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_ADD_DOCTOR_SCHEDULE)) {
			statement.setLong(1, schedule.getDoctor().getUserId());
			statement.setTime(2, schedule.getStartTime());
			statement.setTime(3, schedule.getEndTime());
			statement.setDate(4, schedule.getDate());
			int rowCount = statement.executeUpdate();
			if (rowCount != 0) {
				scheduleAdded = true;
				logger.log(Level.INFO, "schedule added" + schedule);
			} else {
				logger.log(Level.ERROR, "schedule was not added");
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException("Dao exception in method addSchedule, when we try to add shedule:" + schedule, e);
		}
		return scheduleAdded;
	}

	@Override
	public boolean changeDoctorSchedule(DoctorSchedule schedule) throws DaoException {
		logger.log(Level.INFO, "Try to change doctor shedule in db" + schedule);
		boolean isChanged = false;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_DOCTOR_SCHEDULE)) {
			statement.setTime(1, schedule.getStartTime());
			statement.setTime(2, schedule.getEndTime());
			statement.setDate(3, schedule.getDate());
			statement.setLong(4, schedule.getId());
			int rowCount = statement.executeUpdate();
			if (rowCount != 0) {
				isChanged = true;
				logger.log(Level.INFO, "schedule changed:" + schedule);
			} else {
				logger.log(Level.ERROR, "schedule was not changed");
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException(
					"Dao exception in method changeDoctorSchedule, when we try to change chedule:" + schedule, e);
		}
		return isChanged;
	}

	@Override
	public List<DoctorSchedule> findAllSchedulesByDoctor(User user) throws DaoException {
		List<DoctorSchedule> schedules = new ArrayList<>();
		logger.log(Level.INFO, "Find schedules by doctor, doctorID: " + user.getUserId());
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_SCHEDULES_BY_DOCTOR_ID)) {
			statement.setLong(1, user.getUserId());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				DoctorSchedule doctorSchedule = createSchedule(resultSet);
				schedules.add(doctorSchedule);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException(
					"Dao exception in method findByDoctor, doctorId:" + user.getUserId() + " " + e.getErrorCode(), e);
		}
		return schedules;
	}

	@Override
	public List<DoctorSchedule> findAllActiveSchedulesByDoctor(User user) throws DaoException {
		List<DoctorSchedule> schedules = new ArrayList<>();
		logger.log(Level.INFO, "Find schedules by doctor, doctorID: " + user.getUserId());
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ACTIVE_SCHEDULES_BY_DOCTOR_ID)) {
			statement.setLong(1, user.getUserId());
			Date today = Date.valueOf(LocalDate.now());
			logger.log(Level.INFO, "Date today: " + today);
			statement.setDate(2, today);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				DoctorSchedule doctorSchedule = createSchedule(resultSet);
				schedules.add(doctorSchedule);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException(
					"Dao exception in method findByDoctor, doctorId:" + user.getUserId() + " " + e.getErrorCode(), e);
		}
		return schedules;
	}

	@Override
	public Optional<DoctorSchedule> findScheduleByDateAndDoctor(Date date, long doctorId) throws DaoException {
		Optional<DoctorSchedule> schedule;
		logger.log(Level.INFO, "Find schedule by date and doctorId, date: " + date + " ,doctorId:" + doctorId);
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_SCHEDULE_BY_DOCTOR_ID_AND_DATE)) {
			statement.setLong(1, doctorId);
			statement.setDate(2, date);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				DoctorSchedule doctorSchedule = createSchedule(resultSet);
				schedule = Optional.of(doctorSchedule);
			} else {
				logger.log(Level.INFO,
						"didn't find schedule by date and doctorId, date: " + date + " ,doctorId:" + doctorId);
				schedule = Optional.empty();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException("Dao exception in method findScheduleByDateAndDoctor, date:" + date + " doctorId:"
					+ doctorId + ", error code: " + e.getErrorCode(), e);
		}
		return schedule;
	}

	@Override
	public Optional<DoctorSchedule> findScheduleById(long id) throws DaoException {
		Optional<DoctorSchedule> schedule;
		logger.log(Level.INFO, "Find schedule by id, ID: " + id);
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_SCHEDULE_BY_ID)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				DoctorSchedule doctorSchedule = createSchedule(resultSet);
				schedule = Optional.of(doctorSchedule);
			} else {
				logger.log(Level.INFO, "didn't find schedule by id:" + id);
				schedule = Optional.empty();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException(
					"Dao exception in method FindScheduleById, id:" + id + " error code: " + e.getErrorCode(), e);
		}
		return schedule;
	}

	@Override
	public boolean changeFieldIsActive(long scheduleId, boolean isActive) throws DaoException {
		logger.log(Level.INFO,
				"Try to change doctor shedule is_active field for:" + isActive + " in db.Schedule id:" + scheduleId);
		boolean isChanged = false;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_DOCTOR_SCHEDULE_IS_ACTIVE)) {
			statement.setBoolean(1, isActive);
			statement.setLong(2, scheduleId);
			int rowCount = statement.executeUpdate();
			if (rowCount != 0) {
				isChanged = true;
				logger.log(Level.INFO, "schedule changed, id:" + scheduleId);
			} else {
				logger.log(Level.ERROR, "schedule was not changed,id:" + scheduleId);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException(
					"Dao exception in method changeIsActive, when we try to change chedule, scheduleId:" + scheduleId,
					e);
		}
		return isChanged;
	}

	@Override
	public List<DoctorSchedule> findAllSchedulesFromRow(int fromRow, int numberOfSchedulesInPage) throws DaoException {
		logger.log(Level.INFO, "findAllSchedulesFromRow");
		List<DoctorSchedule> schedules = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_SCHEDULES_FROM_ROW)) {
			statement.setInt(1, fromRow);
			statement.setInt(2, numberOfSchedulesInPage);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				DoctorSchedule schedule = createSchedule(resultSet);
				schedules.add(schedule);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR,
					"SQLException in findAllSchedulesFromRow(): " + e.getMessage() + " : " + e.getErrorCode());
			throw new DaoException("Dao exception", e);
		}
		return schedules;
	}

	@Override
	public int findNumberOfRows() throws DaoException {
		logger.log(Level.INFO, "findNumberOfRows");
		int numberOfRows = 0;
		try (Connection connection = connectionPool.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_COUNT_ALL_SCHEDULES)) {
			if (resultSet.next()) {
				numberOfRows = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in findNumberOfRows: " + e.getMessage() + " : " + e.getErrorCode());
			throw new DaoException("Dao exception", e);
		}
		return numberOfRows;
	}

	@Override
	public int findNumberOfRowsDoctorsSchedule(long doctorId) throws DaoException {
		logger.log(Level.INFO, "findNumberOfRows");
		int numberOfRows = 0;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_COUNT_ALL_DOCTORS_SCHEDULES)) {
			statement.setLong(1, doctorId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				numberOfRows = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in findAll: " + e.getMessage() + " : " + e.getErrorCode());
			throw new DaoException("Dao exception", e);
		}
		return numberOfRows;
	}
	@Override
	public List<DoctorSchedule> findAllDoctorSchedulesFromRow(int fromRow, int numberOfSchedulesInPage, long doctorId)
			throws DaoException {
		logger.log(Level.INFO, "findAllDoctorSchedulesFromRow");
		List<DoctorSchedule> schedules = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_DOCTORS_SCHEDULES_FROM_ROW)) {
			statement.setLong(1, doctorId);
			statement.setInt(2, fromRow);
			statement.setInt(3, numberOfSchedulesInPage);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				DoctorSchedule schedule = createSchedule(resultSet);
				schedules.add(schedule);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR,
					"SQLException in findAllDoctorSchedulesFromRow(): " + e.getMessage() + " : " + e.getErrorCode());
			throw new DaoException("Dao exception", e);
		}
		return schedules;
	}

	/**
	 * @param resultSet
	 * @return new DoctorSchedule
	 * @throws SQLException
	 */
	private DoctorSchedule createSchedule(ResultSet resultSet) throws SQLException {
		long doctorScheduleId = resultSet.getLong(ID_DOCTOR_SCHEDULE);
		long userId = resultSet.getLong(USER_ID);
		String name = resultSet.getString(USER_NAME);
		String surname = resultSet.getString(USER_SURNAME);
		String email = resultSet.getString(USER_EMAIL);
		String phone = resultSet.getString(USER_PHONE);
		boolean isBlocked = resultSet.getBoolean(IS_BLOCKED);
		Role role = Role.valueOf(resultSet.getString(ROLE));
		Time startTime = resultSet.getTime(START_TIME);
		Time endTime = resultSet.getTime(END_TIME);
		Date date = resultSet.getDate(DATE);
		boolean isActive = resultSet.getBoolean(IS_ACTIVE);
		User doctor = new User.Builder()
				.setUserID(userId)
				.setName(name)
				.setSurname(surname)
				.setEmail(email)
				.setPhone(phone)
				.setIsBlocked(isBlocked)
				.setRole(role)
				.build();
		DoctorSchedule doctorSchedule = new DoctorSchedule.Builder()
				.setId(doctorScheduleId)
				.setDoctor(doctor)
				.setStartTime(startTime)
				.setEndTime(endTime)
				.setDate(date)
				.setIsActive(isActive)
				.build();
		logger.log(Level.INFO, "finded doctor shedule" + doctorSchedule.toString());
		return doctorSchedule;
	}
}
