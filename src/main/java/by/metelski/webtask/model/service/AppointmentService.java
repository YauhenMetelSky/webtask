package by.metelski.webtask.model.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.entity.Appointment.Status;
import by.metelski.webtask.exception.ServiceException;

public interface AppointmentService {
	boolean add(Map<String,String> data) throws ServiceException;
	boolean changeStatus(long id,Status status) throws ServiceException;
	boolean change(Map<String,String> data) throws ServiceException;
	List<Appointment> findAllByStatus(Status status) throws ServiceException;
	List<Appointment> findAllByUserId(long userId) throws ServiceException;
	List<Appointment> findAllByDoctorId(long doctorId) throws ServiceException;
	List<Appointment> findAllByDoctorIdAndDate(long doctorId,Date date) throws ServiceException;
	Optional<Appointment> findById(long id) throws ServiceException;
}
