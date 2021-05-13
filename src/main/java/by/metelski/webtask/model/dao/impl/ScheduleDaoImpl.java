package by.metelski.webtask.model.dao.impl;

import static by.metelski.webtask.model.dao.ColumnName.DATE;
import static by.metelski.webtask.model.dao.ColumnName.IS_ACTIVE;
import static by.metelski.webtask.model.dao.ColumnName.SCHEDULE_ID;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.entity.DoctorSchedule.Intervals;
import by.metelski.webtask.entity.Schedule;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.model.connection.ConnectionPool;
import by.metelski.webtask.model.dao.ScheduleDao;

public class ScheduleDaoImpl implements ScheduleDao {
	private static final Logger logger = LogManager.getLogger();
	private static final String SQL_ADD_SCHEDULE="INSERT INTO doctor_schedule(doctor_id,schedule_id,9_00,9_30,10_00,10_30,11_00,11_30,12_00,12_30,13_00,13_30,14_00,14_30,15_00,15_30,16_00,16_30,17_00,17_30,18_00,18_30,19_00,19_30,20_00,20_30) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";//TODO 26 columns is it norm?
	private static final String SQL_CHANGE_INTERVAL_STATUS="UPDATE doctor_schedule SET ?=? WHERE id_doctor_schedule=?";
	private static final String SQL_FIND_ALL="SELECT id_doctor_schedule,doctor_id,schedule_id,9_00,9_30,10_00,10_30,11_00,11_30,12_00,12_30,13_00,13_30,14_00,14_30,15_00,15_30,16_00,16_30,17_00,17_30,18_00,18_30,19_00,19_30,20_00,20_30 FROM doctore_schedule";
	private static final String SQL_FIND_BY_DATE = "SELECT date,id_doctor_schedule,doctor_id,schedule_id,9_00,9_30,10_00,10_30,11_00,11_30,12_00,12_30,13_00,13_30,14_00,14_30,15_00,15_30,16_00,16_30,17_00,17_30,18_00,18_30,19_00,19_30,20_00,20_30 FROM schedule JOIN doctor_schedule ON idschedule=schedule_id WHERE date=?";
	private static final String SQL_FIND_BY_DATE_AND_DOCTOR="SELECT date,id_doctor_schedule,doctor_id,schedule_id,9_00,9_30,10_00,10_30,11_00,11_30,12_00,12_30,13_00,13_30,14_00,14_30,15_00,15_30,16_00,16_30,17_00,17_30,18_00,18_30,19_00,19_30,20_00,20_30 FROM schedule JOIN doctor_schedule ON idschedule=schedule_id WHERE date=? AND WHERE doctor_id=?";
	private static final String SQL_ALL_ACTIVE_SCHEDULES="SELECT schedule_id,date,is_active FROM schedule WHERE is_active=true";
	
	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public boolean addSchedule(DoctorSchedule schedule) throws DaoException {
		logger.log(Level.INFO, "Try to add doctor shedule in db" + schedule);
		boolean scheduleAdded = false;
		try (Connection connection = connectionPool.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_ADD_SCHEDULE);
			statement.setInt(1, schedule.getDoctorId());
			statement.setInt(2, schedule.getScheduleId());
            SortedMap<Intervals,Boolean> intervals = schedule.getIntervals();
            int counter = 3;//TODO Magic string
            for(Intervals key : intervals.keySet()) {
            	logger.log(Level.DEBUG, "key:" +key+" counter:" +counter);
            	statement.setBoolean(counter, intervals.get(key));
            	counter++;
            } 
			int rowCount = statement.executeUpdate();
			if (rowCount != 0) {
				scheduleAdded = true;
				logger.log(Level.INFO, "schedule added" + schedule);
			} else {
				logger.log(Level.ERROR, "schedule was not added");
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException("Dao exception in method addSchedule, when we try to add shedule:" + schedule, e);
		}
		return scheduleAdded;
	}

	@Override
	public boolean changeSchedule(String interval, boolean status) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Schedule> findAllActiveSchedules() throws DaoException {
		List<Schedule> schedules = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_ALL_ACTIVE_SCHEDULES)) {
			while (resultSet.next()) {
				long scheduleId = resultSet.getLong(SCHEDULE_ID);
				Date date = resultSet.getDate(DATE);
				boolean isActive = resultSet.getBoolean(IS_ACTIVE);
				logger.log(Level.DEBUG,"scheduleId:"+scheduleId+ " date:" + date+" isActive:" + isActive );		
				schedules.add(new Schedule(scheduleId, date, isActive));
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "findAllActiveSchedules: " + e.getMessage() + " : " + e.getErrorCode());
			throw new DaoException("Dao exception", e);
		}
		return schedules;
	}

	@Override
	public List<Schedule> findByDate(Date date) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Schedule> findByDoctor(User user) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Schedule> findById(int id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Integer> findIdByDateAndDoctor(Date date, int doctorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Schedule> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
}
