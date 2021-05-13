package by.metelski.webtask.model.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.entity.ProcedureFactory;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.ProcedureDao;
import by.metelski.webtask.model.dao.impl.ProcedureDaoImpl;
import by.metelski.webtask.model.service.ProcedureService;

public class ProcedureServiceImpl implements ProcedureService {
	private static final Logger logger = LogManager.getLogger();
	private ProcedureDao procedureDao = new ProcedureDaoImpl();

	@Override
	public boolean add(Map<String, String> procedureData) throws ServiceException {
		//TODO validate data
		Procedure procedure = ProcedureFactory.getInstance().build(procedureData);
		boolean isAdded = false;
		try {
			isAdded = procedureDao.add(procedure);

		} catch (DaoException e) {
			logger.log(Level.ERROR,
					"dao exception in method add, when we try to add procedure:" + procedure + ". " + e);
			throw new ServiceException(e);
		}
		return isAdded;
	}

	@Override
	public boolean activate(long id) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deActivate(long id) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Procedure> findAll() throws ServiceException {
		List<Procedure> procedures;
		try {
			procedures = procedureDao.findAll();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method FindAll" + e);
			throw new ServiceException(e);
		}
		
		return procedures;
	}

	@Override
	public Optional<Procedure> findByName() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Procedure> findById() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
