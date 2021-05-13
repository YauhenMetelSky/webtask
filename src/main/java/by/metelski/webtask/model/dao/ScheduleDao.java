package by.metelski.webtask.model.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.entity.Schedule;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.DaoException;

public interface ScheduleDao {
	boolean addSchedule(DoctorSchedule shedule) throws DaoException;
	boolean changeSchedule(String interval, boolean status) throws DaoException;
	List<Schedule> findAll() throws DaoException;
	List<Schedule> findAllActiveSchedules() throws DaoException;
	List<Schedule> findByDate(Date date) throws DaoException;
	List<Schedule> findByDoctor(User user) throws DaoException;//TODO Is it need?
	Optional<Integer> findIdByDateAndDoctor(Date date,int doctorId);
	Optional<Schedule> findById(int id)throws DaoException;
}
