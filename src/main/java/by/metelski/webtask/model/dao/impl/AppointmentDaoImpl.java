package by.metelski.webtask.model.dao.impl;

import static by.metelski.webtask.model.dao.ColumnName.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.entity.Appointment.Status;
import by.metelski.webtask.entity.User.Role;
import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.model.connection.ConnectionPool;
import by.metelski.webtask.model.dao.AppointmentDao;

/**
 * Class appointment dao
 * @author Yauhen Metelski
 *
 */
public class AppointmentDaoImpl implements AppointmentDao {
	private static final Logger logger = LogManager.getLogger();
	private static final String SQL_ADD_APPOINTMENT = "INSERT INTO appointments (id_client,id_doctor,date,start,id_procedure,status,end) values(?,?,?,?,?,?,?)";
	private static final String SQL_CHANGE_APPOINTMENT = "UPDATE appointments SET id_client=?,id_doctor=?,date=?,start=?,id_procedure=?,status=?,end=? WHERE id_appointment=?";
	private static final String SQL_CHANGE_APPOINTMENT_STATUS = "UPDATE appointments set status=? WHERE id_appointment=?";
	private static final String SQL_FIND_ALL_APPOINTMENTS_BY_USER_ID = "SELECT id_appointment, c.user_id client_id,c.name client_name,c.surname client_surname,c.email client_email,c.phone client_phone,c.is_blocked client_is_blocked,c.role client_role,d.user_id,d.name,d.surname,d.email,d.phone,d.is_blocked,d.role,date,start,p.procedure_id,p.name procedure_name,p.image_name,p.is_active, p.price,p.description,p.duration,status,end FROM appointments JOIN users c ON id_client=user_id JOIN users d ON id_doctor=d.user_id JOIN procedures p ON id_procedure=procedure_id  WHERE id_client=? ORDER BY date";
	private static final String SQL_FIND_ALL_APPOINTMENTS_BY_DOCTOR_ID = "SELECT id_appointment, c.user_id client_id,c.name client_name,c.surname client_surname,c.email client_email,c.phone client_phone,c.is_blocked client_is_blocked,c.role client_role,d.user_id,d.name,d.surname,d.email,d.phone,d.is_blocked,d.role,date,start,p.procedure_id,p.name procedure_name,p.image_name,p.is_active, p.price,p.description,p.duration,status,end FROM appointments JOIN users d ON id_doctor=d.user_id JOIN users c ON id_client=c.user_id JOIN procedures p ON id_procedure=procedure_id  WHERE id_doctor=? AND date>=? AND status=? ORDER BY date";
	private static final String SQL_FIND_ALL_APPOINTMENTS_BY_DOCTOR_ID_AND_DATE = "SELECT id_appointment, c.user_id client_id,c.name client_name,c.surname client_surname,c.email client_email,c.phone client_phone,c.is_blocked client_is_blocked,c.role client_role,d.user_id,d.name,d.surname,d.email,d.phone,d.is_blocked,d.role,date,start,p.procedure_id,p.name procedure_name,p.image_name,p.is_active, p.price,p.description,p.duration,status,end FROM appointments JOIN users d ON id_doctor=d.user_id JOIN users c ON id_client=c.user_id JOIN procedures p ON id_procedure=procedure_id  WHERE id_doctor=? AND date=? AND status=? ORDER BY start";
	private static final String SQL_FIND_APPOINTMENTS_BY_STATUS = "SELECT id_appointment, c.user_id client_id,c.name client_name,c.surname client_surname,c.email client_email,c.phone client_phone,c.is_blocked client_is_blocked,c.role client_role,d.user_id,d.name,d.surname,d.email,d.phone,d.is_blocked,d.role,date,start,p.procedure_id,p.name procedure_name,p.image_name,p.is_active, p.price,p.description,p.duration,status,end FROM appointments JOIN users c ON id_client=user_id JOIN users d ON id_doctor=d.user_id JOIN procedures p ON id_procedure=procedure_id  WHERE status=? ORDER BY date";
	private static final String SQL_FIND_APPOINTMENT_BY_ID = "SELECT id_appointment, c.user_id client_id,c.name client_name,c.surname client_surname,c.email client_email,c.phone client_phone,c.is_blocked client_is_blocked,c.role client_role,d.user_id,d.name,d.surname,d.email,d.phone,d.is_blocked,d.role,date,start,p.procedure_id,p.name procedure_name,p.image_name,p.is_active, p.price,p.description,p.duration,status,end FROM appointments JOIN users c ON id_client=user_id JOIN users d ON id_doctor=d.user_id JOIN procedures p ON id_procedure=procedure_id  WHERE id_appointment=?";
	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public boolean add(Appointment appointment) throws DaoException {
		logger.log(Level.INFO, "Try to add appointment in db" + appointment);
		boolean isAdded = false;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_ADD_APPOINTMENT)) {
			statement.setLong(1, appointment.getUser().getUserId());
			statement.setLong(2, appointment.getDoctor().getUserId());
			statement.setDate(3, appointment.getDate());
			statement.setTime(4, appointment.getStartTime());
			statement.setLong(5, appointment.getProcedure().getProcedureId());
			statement.setString(6, appointment.getStatus().name());
			statement.setTime(7, appointment.getEndTime());
			int rowCount = statement.executeUpdate();
			if (rowCount != 0) {
				isAdded = true;
				logger.log(Level.INFO, "appointment added:" + appointment);
			} else {
				logger.log(Level.ERROR, "appointment was not added");
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException(
					"Dao exception in method addAppointment, when we try to add appointment:" + appointment, e);
		}
		return isAdded;
	}

	@Override
	public boolean changeStatus(long id, Status status) throws DaoException {
		logger.log(Level.INFO, "change appointment status, appointmentId:" + id + ", status:" + status);
		boolean isChanged = false;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_APPOINTMENT_STATUS)) {
			statement.setString(1, status.name());
			statement.setLong(2, id);
			int rowCount = statement.executeUpdate();
			if (rowCount != 0) {
				isChanged = true;
				logger.log(Level.INFO, "appointment: " + id + ", status is:" + status);
			} else {
				logger.log(Level.ERROR, "appointment: " + id + " status was't changed");
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException("Dao exception in method changeStatus", e);
		}
		return isChanged;
	}

	@Override
	public boolean changeAppointment(Appointment appointment) throws DaoException {
		logger.log(Level.INFO, "Try to change appointment in db" + appointment);
		boolean isChanged = false;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_APPOINTMENT)) {
			logger.log(Level.INFO, "Appointment to changing" + appointment);
			statement.setLong(1, appointment.getUser().getUserId());
			statement.setLong(2, appointment.getDoctor().getUserId());
			statement.setDate(3, appointment.getDate());
			statement.setTime(4, appointment.getStartTime());
			statement.setLong(5, appointment.getProcedure().getProcedureId());
			statement.setString(6, appointment.getStatus().name());
			statement.setTime(7, appointment.getEndTime());
			statement.setLong(8, appointment.getId());
			int rowCount = statement.executeUpdate();
			if (rowCount != 0) {
				isChanged = true;
				logger.log(Level.INFO, "appointment changed:" + appointment);
			} else {
				logger.log(Level.ERROR, "appointment was not changed");
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException(
					"Dao exception in method changeAppointment, when we try to change appointment:" + appointment, e);
		}
		return isChanged;
	}

	@Override
	public Optional<Appointment> findById(long id) throws DaoException {
		logger.log(Level.INFO, "Find all appointments by id, id=  " + id);
		Optional<Appointment> optionalAppointment;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_APPOINTMENT_BY_ID)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				optionalAppointment = Optional.of(createAppointment(resultSet));
			} else {
				optionalAppointment = Optional.empty();
			}
		} catch (SQLException e) {
			throw new DaoException("Dao exception", e);
		}
		return optionalAppointment;
	}

	@Override
	public List<Appointment> findAllByStatus(Status status) throws DaoException {
		logger.log(Level.INFO, "Find all appointments by status, status=  " + status);
		List<Appointment> appointments = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_APPOINTMENTS_BY_STATUS)) {
			statement.setString(1, status.name());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				appointments.add(createAppointment(resultSet));
			}
		} catch (SQLException e) {
			throw new DaoException("Dao exception", e);
		}
		return appointments;
	}

	@Override
	public List<Appointment> findAllByUserId(long userId) throws DaoException {
		logger.log(Level.INFO, "Find all appointments by user id, useId=  " + userId);
		List<Appointment> appointments = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_APPOINTMENTS_BY_USER_ID)) {
			statement.setLong(1, userId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				appointments.add(createAppointment(resultSet));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException("Dao exception in method findAllByUserId", e);
		}
		logger.log(Level.INFO, "Finded appointments: " + appointments);
		return appointments;
	}

	@Override
	public List<Appointment> findAllByDoctorId(long doctorId) throws DaoException {
		Date today = Date.valueOf(LocalDate.now());
		logger.log(Level.INFO, "Date today: " + today);
		logger.log(Level.INFO, "Find all appointments by doctor id, doctroId=  " + doctorId);
		List<Appointment> appointments = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_APPOINTMENTS_BY_DOCTOR_ID)) {
			statement.setLong(1, doctorId);
			statement.setDate(2, today);
			statement.setString(3, Status.CONFIRMED.name());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				appointments.add(createAppointment(resultSet));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException("Dao exception in method findAllByDoctorId", e);
		}
		logger.log(Level.INFO, "Finded appointments: " + appointments);
		return appointments;
	}

	@Override
	public List<Appointment> findAllByDoctorIdAndDate(long doctorId, Date date) throws DaoException {
		logger.log(Level.INFO, "Find all appointments by doctor id, doctroId=  " + doctorId + ",and Date:" + date);
		List<Appointment> appointments = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SQL_FIND_ALL_APPOINTMENTS_BY_DOCTOR_ID_AND_DATE)) {
			statement.setLong(1, doctorId);
			statement.setDate(2, date);
			statement.setString(3, Status.CONFIRMED.name());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				appointments.add(createAppointment(resultSet));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException("Dao exception in method findAllByDoctorId", e);
		}
		logger.log(Level.INFO, "Finded appointments: " + appointments);
		return appointments;
	}

	/**
	 * Method create appointment
	 * @param resultSet
	 * @return new Appointment
	 * @throws SQLException
	 */
	private Appointment createAppointment(ResultSet resultSet) throws SQLException {
		long appointmentId = resultSet.getLong(APPOINTMENT_ID);
		long clientId = resultSet.getLong(CLIENT_ID);
		String clientName = resultSet.getString(CLIENT_NAME);
		String clientSurname = resultSet.getString(CLIENT_SURNAME);
		String clientEmail = resultSet.getString(CLIENT_EMAIL);
		String clientPhone = resultSet.getString(CLIENT_PHONE);
		boolean clientIsBlocked = resultSet.getBoolean(CLIENT_IS_BLOCKED);
		Role clientRole = Role.valueOf(resultSet.getString(CLIENT_ROLE));
		User client = new User.Builder()
				.setUserID(clientId)
				.setName(clientName)
				.setSurname(clientSurname)
				.setEmail(clientEmail)
				.setPhone(clientPhone)
				.setIsBlocked(clientIsBlocked)
				.setRole(clientRole)
				.build();
		long doctorIdFromDB = resultSet.getLong(USER_ID);
		String doctorName = resultSet.getString(USER_NAME);
		String doctorSurname = resultSet.getString(USER_SURNAME);
		String doctorEmail = resultSet.getString(USER_EMAIL);
		String doctorPhone = resultSet.getString(USER_PHONE);
		boolean doctorIsBlocked = resultSet.getBoolean(IS_BLOCKED);
		Role doctorRole = Role.valueOf(resultSet.getString(ROLE));
		User doctor = new User.Builder()
				.setUserID(doctorIdFromDB)
				.setName(doctorName)
				.setSurname(doctorSurname)
				.setEmail(doctorEmail)
				.setPhone(doctorPhone)
				.setIsBlocked(doctorIsBlocked)
				.setRole(doctorRole)
				.build();
		long procedureId = resultSet.getLong(PROCEDURE_ID);
		String name = resultSet.getString(PROCEDURE_NAME);
		String imageName = resultSet.getString(IMAGE_NAME);
		BigDecimal price = resultSet.getBigDecimal(PRICE);
		boolean procedureIsActive = resultSet.getBoolean(IS_ACTIVE);
		String description = resultSet.getString(DESCRIPTION);
		Duration duration = Duration.ofMinutes(resultSet.getInt(DURATION));
		Procedure procedure = new Procedure.Builder()
				.setProcedureId(procedureId)
				.setName(name)
				.setImageName(imageName)
				.setPrice(price)
				.setIsActive(procedureIsActive)
				.setDescription(description)
				.setDuration(duration)
				.build();
		Time start = resultSet.getTime(START);
		Time end = resultSet.getTime(END);
		Date appointmentDate = resultSet.getDate(DATE);
		Status procedureStatus = Status.valueOf(resultSet.getString(STATUS));
		Appointment appointment = new Appointment.Builder()
				.setId(appointmentId)
				.setUser(client)
				.setDoctor(doctor)
				.setProcedure(procedure)
				.setStartTime(start)
				.setEndTime(end)
				.setDate(appointmentDate)
				.setStatus(procedureStatus)
				.build();
		return appointment;
	}
}
