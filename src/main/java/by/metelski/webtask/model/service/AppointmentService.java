package by.metelski.webtask.model.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.entity.Appointment.Status;
import by.metelski.webtask.exception.ServiceException;

/**
 * The interface appointment service
 * @author Yauhen Metelski
 *
 */
public interface AppointmentService {
	/**
	 * @param data, map contains appointment's data
	 * @return boolean result of operation
	 * @throws ServiceException
	 */
	boolean add(Map<String,String> data) throws ServiceException;
	/**
	 * @param id
	 * @param status
	 * @return boolean result of operation
	 * @throws ServiceException
	 */
	boolean changeStatus(long id,Status status) throws ServiceException;
	/**
	 * @param data, map contains appointment's data
	 * @return boolean result of operation
	 * @throws ServiceException
	 */
	boolean change(Map<String,String> data) throws ServiceException;
	/**
	 * @param status
	 * @return List of appointments with such status
	 * @throws ServiceException
	 */
	List<Appointment> findAllByStatus(Status status) throws ServiceException;
	/**
	 * @param userId
	 * @return List of appointments belongs user with this id
	 * @throws ServiceException
	 */
	List<Appointment> findAllByUserId(long userId) throws ServiceException;
	/**
	 * @param doctorId
	 * @return List of appointments with such doctor id
	 * @throws ServiceException
	 */
	List<Appointment> findAllByDoctorId(long doctorId) throws ServiceException;
	/**
	 * @param doctorId
	 * @param date
	 * @return List of appointments found by doctor id and date
	 * @throws ServiceException
	 */
	List<Appointment> findAllByDoctorIdAndDate(long doctorId,Date date) throws ServiceException;
	/**
	 * @param date 
	 * @param status appointment status 
	 * @return List of appointments found by date
	 * @throws ServiceException
	 */
	List<Appointment> findAllByDateAndStatus(Date date,Status status) throws ServiceException;
	/**
	 * @param id appointment id
	 * @return Optional<Appointment>
	 * @throws ServiceException
	 */
	Optional<Appointment> findById(long id) throws ServiceException;
}
