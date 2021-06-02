package by.metelski.webtask.model.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.DaoException;

public interface ScheduleDao {
	boolean addDoctorSchedule(DoctorSchedule shedule) throws DaoException;
	List<DoctorSchedule> findAllSchedulesByDoctor(User user) throws DaoException;
	List<DoctorSchedule> findAllActiveSchedulesByDoctor(User user) throws DaoException;
	Optional<DoctorSchedule> FindScheduleById(long id) throws DaoException;
	Optional<DoctorSchedule> findScheduleByDateAndDoctor(Date date,long doctorId) throws DaoException;
	//TODO add deactivate schedule method
}
