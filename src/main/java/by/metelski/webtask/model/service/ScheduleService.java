package by.metelski.webtask.model.service;


import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.exception.ServiceException;

public interface ScheduleService {
	boolean addDoctorSchedule(Map<String,String> data) throws ServiceException ;
	List<DoctorSchedule> findAllSchedulesByDoctorId(long userId) throws ServiceException ;
	Optional<DoctorSchedule> FindScheduleById(long id) throws ServiceException;
	Optional<DoctorSchedule> findScheduleByDateAndDoctor(Date date,long doctorId) throws ServiceException;
}

