package by.metelski.webtask.model.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.entity.Appointment.Status;
import by.metelski.webtask.exception.DaoException;

public interface AppointmentDao {
	boolean add(Appointment appointment) throws DaoException;
	boolean changeStatus(long id,Status status) throws DaoException;
	boolean changeAppointment(Appointment appointment) throws DaoException;	
	List<Appointment> findAll() throws DaoException;
	List<Appointment> findAllByDoctorId(long doctorId) throws DaoException;
	List<Appointment> findAllByDoctorIdAndDate(long doctorId,Date date) throws DaoException;
	List<Appointment> findAllByUserId(long userId) throws DaoException;
	List<Appointment> findAllByStatus(Status status) throws DaoException;
	Optional<Appointment> findById(long id) throws DaoException;
}
