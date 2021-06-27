package by.metelski.webtask.model.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.exception.ServiceException;

/**
 * The interface schedule service
 * @author Yauhen Metelski
 *
 */
public interface ScheduleService {

	/**
	 * @param data
	 * @return boolean result of operation
	 * @throws ServiceException
	 */
	boolean addDoctorSchedule(Map<String, String> data) throws ServiceException;

	/**
	 * @param data
	 * @return boolean result of operation
	 * @throws ServiceException
	 */
	boolean changeDoctorSchedule(Map<String, String> data) throws ServiceException;

	/**
	 * @param scheduleId
	 * @param isActive
	 * @return boolean result of operation
	 * @throws ServiceException
	 */
	boolean changeFieldIsActive(long scheduleId, boolean isActive) throws ServiceException;

	/**
	 * @param pageNumber
	 * @return List of schedules from row, calculated by page number
	 * @throws ServiceException
	 */
	List<DoctorSchedule> findAllSchedulesFromRow(int pageNumber) throws ServiceException;

	/**
	 * @param doctorId
	 * @return List of active schedules by doctor id
	 * @throws ServiceException
	 */
	List<DoctorSchedule> findAllActiveSchedulesByDoctor(long doctorId) throws ServiceException;

	/**
	 * @param doctorId
	 * @return List of schedules by doctor id
	 * @throws ServiceException
	 */
	List<DoctorSchedule> findAllSchedulesByDoctorId(long doctorId) throws ServiceException;

	/**
	 * @param doctorId
	 * @param pageNumber
	 * @return List of doctor's schedules from row, calculated by page number
	 * @throws ServiceException
	 */
	List<DoctorSchedule> findAllSchedulesByDoctorIdFromRow(long doctorId, int pageNumber) throws ServiceException;

	/**
	 * @return int number of pages for pagination
	 * @throws ServiceException
	 */
	int findNumberOfPages() throws ServiceException;

	/**
	 * @param doctorId
	 * @return int number of pages for pagination, doctor's schedule
	 * @throws ServiceException
	 */
	int findNumberOfPagesDoctorsShedules(long doctorId) throws ServiceException;

	/**
	 * @param id
	 * @return Optional<DoctorSchedule>
	 * @throws ServiceException
	 */
	Optional<DoctorSchedule> findScheduleById(long id) throws ServiceException;

	/**
	 * @param date
	 * @param doctorId
	 * @return Optional<DoctorSchedule>
	 * @throws ServiceException
	 */
	Optional<DoctorSchedule> findScheduleByDateAndDoctor(Date date, long doctorId) throws ServiceException;
}
