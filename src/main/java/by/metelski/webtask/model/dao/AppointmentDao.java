package by.metelski.webtask.model.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.exception.DaoException;

public interface AppointmentDao {
	boolean add(Appointment appointment) throws DaoException;

	boolean activate(long id) throws DaoException;

	boolean changeAppointment(Appointment appointment) throws DaoException;
	
	boolean changeIntervalstatus(String interval, int doctor_schedule_id) throws DaoException;
	
	boolean isIntervalFree(Date date,String start,int doctorId) throws DaoException;

	List<Procedure> findAll() throws DaoException;

	Optional<Procedure> findByName() throws DaoException;

	Optional<Procedure> findById() throws DaoException;
}
