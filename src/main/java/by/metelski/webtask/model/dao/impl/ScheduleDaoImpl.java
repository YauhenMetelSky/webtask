package by.metelski.webtask.model.dao.impl;

import static by.metelski.webtask.model.dao.ColumnName.DATE;
import static by.metelski.webtask.model.dao.ColumnName.DOCTOR_ID;
import static by.metelski.webtask.model.dao.ColumnName.END_TIME;
import static by.metelski.webtask.model.dao.ColumnName.ID_DOCTOR_SCHEDULE;
import static by.metelski.webtask.model.dao.ColumnName.IS_ACTIVE;
import static by.metelski.webtask.model.dao.ColumnName.IS_BLOCKED;
import static by.metelski.webtask.model.dao.ColumnName.ROLE;
import static by.metelski.webtask.model.dao.ColumnName.START_TIME;
import static by.metelski.webtask.model.dao.ColumnName.SCHEDULE_ID;
import static by.metelski.webtask.model.dao.ColumnName.USER_EMAIL;
import static by.metelski.webtask.model.dao.ColumnName.USER_ID;
import static by.metelski.webtask.model.dao.ColumnName.USER_NAME;
import static by.metelski.webtask.model.dao.ColumnName.USER_PHONE;
import static by.metelski.webtask.model.dao.ColumnName.USER_SURNAME;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
import by.metelski.webtask.model.dao.ColumnName;
import by.metelski.webtask.model.dao.ScheduleDao;

public class ScheduleDaoImpl implements ScheduleDao {
	private static final Logger logger = LogManager.getLogger();
	private static final String SQL_ADD_DOCTOR_SCHEDULE = "INSERT INTO doctor_schedules(doctor_id,start_time,end_time,date) values(?,?,?,?)";
	private static final String SQL_FIND_SCHEDULES_BY_DOCTOR_ID = "SELECT id_doctor_schedule,d.user_id,d.name,d.surname,d.email,d.phone,d.is_blocked,d.role,start_time,end_time,date FROM doctor_schedules JOIN users d ON doctor_id=user_id WHERE doctor_id=?";
	private static final String SQL_FIND_SCHEDULE_BY_ID = "SELECT id_doctor_schedule,user_id,name,surname,email,phone,is_blocked,role, start_time,end_time,date FROM doctor_schedules JOIN users ON doctor_id=user_id WHERE id_doctor_schedule=?";
	private static final String SQL_FIND_SCHEDULE_BY_DOCTOR_ID_AND_DATE = "SELECT id_doctor_schedule,user_id,name,surname,email,phone,is_blocked,role, start_time,end_time,date FROM doctor_schedules JOIN users ON doctor_id=user_id WHERE doctor_id=? AND date=?";
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
	public List<DoctorSchedule> findAllSchedulesByDoctor(User user) throws DaoException {
		List<DoctorSchedule> schedules = new ArrayList<>();
		logger.log(Level.INFO, "Find schedules by doctor, doctorID: " + user.getUserId());
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_SCHEDULES_BY_DOCTOR_ID)) {
			statement.setLong(1, user.getUserId());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long doctorScheduleId = resultSet.getLong(ID_DOCTOR_SCHEDULE);
				long doctorId = resultSet.getLong(ColumnName.USER_ID);
				String doctorName=resultSet.getString(ColumnName.USER_NAME);
				String doctorSurname=resultSet.getString(ColumnName.USER_SURNAME);
				String doctorEmail=resultSet.getString(ColumnName.USER_EMAIL);
				String doctorPhone=resultSet.getString(ColumnName.USER_PHONE);
				boolean doctorIsBlocked = resultSet.getBoolean(ColumnName.IS_BLOCKED);
				Role doctorRole = Role.valueOf(resultSet.getString(ColumnName.ROLE));
				User doctor = new User.Builder()
						.setUserID(doctorId)
						.setName(doctorName)
						.setSurname(doctorSurname)
						.setEmail(doctorEmail)
						.setPhone(doctorPhone)
						.setIsBlocked(doctorIsBlocked)
						.serRole(doctorRole)
						.build();
				Time startTime = resultSet.getTime(START_TIME);
				Time endTime = resultSet.getTime(END_TIME);
				Date date = resultSet.getDate(DATE);
				DoctorSchedule doctorSchedule = new DoctorSchedule.Builder()
						.setId(doctorScheduleId)
						.setDoctor(doctor)
						.setStartTime(startTime)
						.setEndTime(endTime)
						.setDate(date)
						.build();
				logger.log(Level.INFO, "finded doctor shedule" + doctorSchedule.toString());
				schedules.add(doctorSchedule);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException("Dao exception in method findByDoctor, doctorId:" + user.getUserId() + " " + e.getErrorCode(),
					e);
		}
		return schedules;
	}

	@Override
	public Optional<DoctorSchedule> findScheduleByDateAndDoctor(Date date,long doctorId) throws DaoException {
		Optional<DoctorSchedule> schedule;
		logger.log(Level.INFO, "Find schedule by date and doctorId, date: " + date+" ,doctorId:" + doctorId);
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_SCHEDULE_BY_DOCTOR_ID_AND_DATE)) {
			statement.setLong(1, doctorId);
			statement.setDate(2, date);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				long doctorScheduleId = resultSet.getLong(ID_DOCTOR_SCHEDULE);
				long userId =resultSet.getLong(USER_ID);
				String name = resultSet.getString(USER_NAME);
				String surname = resultSet.getString(USER_SURNAME);
				String email = resultSet.getString(USER_EMAIL);
				String phone = resultSet.getString(USER_PHONE);
				boolean isBlocked = resultSet.getBoolean(IS_BLOCKED);
				Role role = Role.valueOf(resultSet.getString(ROLE));
				Time startTime = resultSet.getTime(START_TIME);
				Time endTime = resultSet.getTime(END_TIME);
				Date scheduleDate = resultSet.getDate(DATE);
				User doctor = new User.Builder()
						.setUserID(userId)
						.setName(name)
						.setSurname(surname)
						.setEmail(email)
						.setPhone(phone)
						.setIsBlocked(isBlocked)
						.serRole(role)
						.build();
				DoctorSchedule doctorSchedule = new DoctorSchedule.Builder()
						.setId(doctorScheduleId)
						.setDoctor(doctor)
						.setStartTime(startTime)
						.setEndTime(endTime)
						.setDate(scheduleDate)
						.build();
				logger.log(Level.INFO, "finded doctor shedule" + doctorSchedule.toString());
				schedule = Optional.of(doctorSchedule);
			} else {
				logger.log(Level.INFO, "didn't find schedule by date and doctorId, date: " + date+" ,doctorId:" + doctorId);
				schedule = Optional.empty();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException(
					"Dao exception in method findScheduleByDateAndDoctor, date:" + date+" doctorId:"+doctorId+ ", error code: " + e.getErrorCode(), e);
		}
		return schedule;
	}
	

	@Override
	public Optional<DoctorSchedule> FindScheduleById(long id) throws DaoException {
		Optional<DoctorSchedule> schedule;
		logger.log(Level.INFO, "Find schedule by id, ID: " + id);
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_SCHEDULE_BY_ID)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				long doctorScheduleId = resultSet.getLong(ID_DOCTOR_SCHEDULE);
				long userId =resultSet.getLong(USER_ID);
				String name = resultSet.getString(USER_NAME);
				String surname = resultSet.getString(USER_SURNAME);
				String email = resultSet.getString(USER_EMAIL);
				String phone = resultSet.getString(USER_PHONE);
				boolean isBlocked = resultSet.getBoolean(IS_BLOCKED);
				Role role = Role.valueOf(resultSet.getString(ROLE));
				Time startTime = resultSet.getTime(START_TIME);
				Time endTime = resultSet.getTime(END_TIME);
				Date date = resultSet.getDate(DATE);
				User doctor = new User.Builder()
						.setUserID(userId)
						.setName(name)
						.setSurname(surname)
						.setEmail(email)
						.setPhone(phone)
						.setIsBlocked(isBlocked)
						.serRole(role)
						.build();
				DoctorSchedule doctorSchedule = new DoctorSchedule.Builder()
						.setId(doctorScheduleId)
						.setDoctor(doctor)
						.setStartTime(startTime)
						.setEndTime(endTime)
						.setDate(date)
						.build();
				logger.log(Level.INFO, "finded doctor shedule" + doctorSchedule.toString());
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
}
