package by.metelski.webtask.model.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.DaoException;

/**
 * The interface schedule dao
 * @author Yauhen Metelski
 *
 */
public interface ScheduleDao {
	/**
	 * @param schedule
	 * @return boolean result of operation
	 * @throws DaoException
	 */
	boolean addDoctorSchedule(DoctorSchedule schedule) throws DaoException;

	/**
	 * @param schedule
	 * @return boolean result of operation
	 * @throws DaoException
	 */
	boolean changeDoctorSchedule(DoctorSchedule schedule) throws DaoException;

	/**
	 * @param scheduleId
	 * @param isActive
	 * @return boolean result of operation
	 * @throws DaoException
	 */
	boolean changeFieldIsActive(long scheduleId, boolean isActive) throws DaoException;

	/**
	 * @return Number of rows with schedules
	 * @throws DaoException
	 */
	int findNumberOfRows() throws DaoException;

	/**
	 * @param doctorId
	 * @return Number of rows with doctor's schedules  in database
	 * @throws DaoException
	 */
	int findNumberOfRowsDoctorsSchedule(long doctorId) throws DaoException;

	/**
	 * @param fromRow
	 * @param numberOfSchedulesInPage
	 * @return List of schedules from row = fromRow.
	 * @throws DaoException
	 */
	List<DoctorSchedule> findAllSchedulesFromRow(int fromRow, int numberOfSchedulesInPage) throws DaoException;

	/**
	 * @param user
	 * @return List of doctors's schedules
	 * @throws DaoException
	 */
	List<DoctorSchedule> findAllSchedulesByDoctor(User user) throws DaoException;

	/**
	 * @param user
	 * @return List of  active doctors's schedules (field is_active = true) 
	 * @throws DaoException
	 */
	List<DoctorSchedule> findAllActiveSchedulesByDoctor(User user) throws DaoException;

	/**
	 * @param fromRow
	 * @param numberOfSchedulesInPage
	 * @param doctorId
	 * @return List of doctors's schedules started from row =fromRow.
	 * @throws DaoException
	 */
	List<DoctorSchedule> findAllDoctorSchedulesFromRow(int fromRow, int numberOfSchedulesInPage, long doctorId)
			throws DaoException;

	/**
	 * @param id
	 * @return Optional<DoctorSchedule> 
	 * @throws DaoException
	 */
	Optional<DoctorSchedule> findScheduleById(long id) throws DaoException;

	/**
	 * @param date
	 * @param doctorId
	 * @return Optional<DoctorSchedule> 
	 * @throws DaoException
	 */
	Optional<DoctorSchedule> findScheduleByDateAndDoctor(Date date, long doctorId) throws DaoException;
}
