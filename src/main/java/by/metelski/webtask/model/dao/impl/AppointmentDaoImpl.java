package by.metelski.webtask.model.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.entity.Appointment;
import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.model.dao.AppointmentDao;

public class AppointmentDaoImpl implements AppointmentDao {
	private static final Logger logger = LogManager.getLogger();
	private static final String SQL_ADD_APPOINTMENT = "INSERT INTO appointments (id_client,id_doctor,date,start_interval,id_procedure,status) values(?,?,?,?,?,?)";

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

}
