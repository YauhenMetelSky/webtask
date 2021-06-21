package by.metelski.webtask.model.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.entity.Appointment.Status;
import by.metelski.webtask.exception.DaoException;

/**
 * The interface appointment dao
 * @author Yauhen Metelski
 *
 */
public interface AppointmentDao {
	/**
	 * Add appointment
	 * @param appointment
	 * @return boolean result of operation
	 * @throws DaoException
	 */
	boolean add(Appointment appointment) throws DaoException;

	/**
	 * Change status
	 * @param id
	 * @param status
	 * @return boolean result of operation
	 * @throws DaoException
	 */
	boolean changeStatus(long id, Status status) throws DaoException;

	/**
	 * Change appointment information
	 * @param appointment
	 * @return boolean result of operation
	 * @throws DaoException
	 */
	boolean changeAppointment(Appointment appointment) throws DaoException;

	/**
	 * Find all appointments by doctor id
	 * @param doctorId
	 * @return List of appointments
	 * @throws DaoException
	 */
	List<Appointment> findAllByDoctorId(long doctorId) throws DaoException;

	/**
	 * Find all appointments by doctor id and date
	 * @param doctorId
	 * @param date
	 * @return List of appointments
	 * @throws DaoException
	 */
	List<Appointment> findAllByDoctorIdAndDate(long doctorId, Date date) throws DaoException;

	/**
	 * Find all appointments by client id
	 * @param userId
	 * @return List of appointments
	 * @throws DaoException
	 */
	List<Appointment> findAllByUserId(long userId) throws DaoException;

	/**
	 * Find all appointments by status
	 * @param status
	 * @returnList of appointments
	 * @throws DaoException
	 */
	List<Appointment> findAllByStatus(Status status) throws DaoException;

	/**
	 * Find appointment by id
	 * @param id
	 * @return Appointment object
	 * @throws DaoException
	 */
	Optional<Appointment> findById(long id) throws DaoException;
}
