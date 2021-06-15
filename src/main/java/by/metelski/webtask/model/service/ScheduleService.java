package by.metelski.webtask.model.service;


import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.exception.ServiceException;

public interface ScheduleService {
	boolean addDoctorSchedule(Map<String,String> data) throws ServiceException ;
	boolean changeDoctorSchedule(Map<String,String> data) throws ServiceException ;
	boolean changeFieldIsActive(long scheduleId, boolean isActive) throws ServiceException;
	List<DoctorSchedule> findAllSchedules() throws ServiceException;
	List<DoctorSchedule> findAllSchedulesFromRow(int row) throws ServiceException;
	List<DoctorSchedule> findAllActiveSchedulesByDoctor(long userId) throws ServiceException;
	List<DoctorSchedule> findAllSchedulesByDoctorId(long userId) throws ServiceException;
	int findNumberOfPages()throws ServiceException;
	Optional<DoctorSchedule> findScheduleById(long id) throws ServiceException;
	Optional<DoctorSchedule> findScheduleByDateAndDoctor(Date date,long doctorId) throws ServiceException;
}

