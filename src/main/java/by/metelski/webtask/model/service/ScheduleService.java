package by.metelski.webtask.model.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.metelski.webtask.entity.Schedule;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.ServiceException;

public interface ScheduleService {
	boolean addSchedule(Map<String,String> data) throws ServiceException ;
	boolean changeSchedule(Schedule schedule) throws ServiceException ;
	List<Schedule> findAll() throws ServiceException ;
	List<Schedule> findAllActiveSchedules() throws ServiceException ;
	List<Schedule> findByDate(Date date) throws ServiceException ;
	List<Schedule> findByUser(User user) throws ServiceException ;
	Optional<Schedule> findById(Long id) throws ServiceException ;
}
