package by.metelski.webtask.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.model.connection.ConnectionPool;
import by.metelski.webtask.model.dao.AppointmentDao;

public class AppointmentDaoImpl implements AppointmentDao {
	private static final Logger logger = LogManager.getLogger();
	private static final String SQL_ADD_APPOINTMENT = "INSERT INTO appointments (id_client,id_doctor,date,start_interval,id_procedure,status) values(?,?,?,?,?,?)";
	private static final String SQL_IS_TIME_FREE = "SELECT date FROM schedule JOIN doctor_schedule ON idschedule=schedule_id WHERE date=? AND doctor_id=?;"; 
	private static final String SQL_FIND_DOCTOR_SCHEDULE_ID = "SELECT iddoctor_schedule FROM shedule JOIN doctor_schedule ON idshedule=shedule_id WHERE date=? AND doctor_id=?;";
	private static final String SQL_CHANGE_INTERVAL_STATUS = "UPDATE doctor_schedule SET ?=false WHERE id_doctor_schedule=?";//TODO Query in schedule DAO
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	@Override
	public boolean add(Appointment appointment) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean activate(long id) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeAppointment(Appointment appointment) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Procedure> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Procedure> findByName() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Procedure> findById() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isIntervalFree(Date date, String interval, int doctorId) throws DaoException {
		//FIXME use doctor_schedule_id
		boolean isFree =false;
		try (Connection connection = connectionPool.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_IS_TIME_FREE);
			statement.setString(1, interval);		
			statement.setDate(2,date );
			statement.setInt(3, doctorId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				isFree = resultSet.getBoolean("interval");		
				logger.log(Level.INFO, "interval is free:" + isFree);			
			}
		} catch (SQLException e) {
			throw new DaoException("Dao exception", e);
		}
		return isFree;
	}

	@Override
	public boolean changeIntervalstatus(String interval, int doctor_shedule_id) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}
}
