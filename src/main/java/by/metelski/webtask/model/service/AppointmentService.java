package by.metelski.webtask.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.exception.ServiceException;

public interface AppointmentService {
	boolean add(Map<String,String> data) throws ServiceException;
	boolean changeAppointment(Appointment appointment) throws ServiceException;
	boolean change(Map<String,String> data) throws ServiceException;
	List<Appointment> findAll() throws ServiceException;
	Optional<Appointment> findById(long id) throws ServiceException;
}