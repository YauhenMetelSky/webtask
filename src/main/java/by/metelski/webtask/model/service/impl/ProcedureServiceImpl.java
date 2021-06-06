package by.metelski.webtask.model.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.ProcedureDao;
import by.metelski.webtask.model.dao.impl.ProcedureDaoImpl;
import by.metelski.webtask.model.service.ProcedureService;

public class ProcedureServiceImpl implements ProcedureService {
	private static final Logger logger = LogManager.getLogger();
	private ProcedureDao procedureDao;
	
	public ProcedureServiceImpl(ProcedureDao procedureDao) {
		this.procedureDao=procedureDao;
	}

	@Override
	public boolean add(Map<String, String> procedureData) throws ServiceException {
		logger.log(Level.DEBUG, "add(), procedureData:" + procedureData);
		// TODO validate data
		Duration duration = Duration.ofMinutes(Long.parseLong(procedureData.get(ParameterAndAttribute.DURATION)));
		 Procedure procedure = new Procedure.Builder()
					.setName(procedureData.get(ParameterAndAttribute.PROCEDURE_NAME))
				    .setImageName(procedureData.get(ParameterAndAttribute.PROCEDURE_IMAGE))
				    .setPrice(new BigDecimal(procedureData.get(ParameterAndAttribute.PROCEDURE_PRICE)))
				    .setDescription(procedureData.get(ParameterAndAttribute.DESCRIPTION))
				    .setDuration(duration)
				    .build();
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
	public List<Procedure> findAll() throws ServiceException {
		logger.log(Level.DEBUG, "findAll()");
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
	public List<Procedure> findAllActive() throws ServiceException {
		logger.log(Level.DEBUG, "findAllActive()");
		List<Procedure> procedures;
		try {
			procedures = procedureDao.findAllActive();
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method FindAllActive" + e);
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
	public Optional<Procedure> findById(long id) throws ServiceException {
		logger.log(Level.DEBUG, "findBYId, procedure id:"+  id);
		Optional<Procedure>procedure;
		try {
			procedure = procedureDao.findById(id);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method findById" + e);
			throw new ServiceException(e);
		}
		return procedure;
	}

	@Override
	public boolean setIsActive(long id, boolean isActive) throws ServiceException {
		boolean isChanged = false;
		try {
			isChanged=procedureDao.setIsActive(id, isActive);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method setIsActive" + e);
			throw new ServiceException(e);
		}
		return isChanged;
	}

	@Override
	public boolean changeProcedure(Map<String, String> procedureData) throws ServiceException {
		logger.log(Level.DEBUG, "Change procedure; data" + procedureData);
		// TODO validate data
		boolean isChanged = false;
		try {
			long id = Long.parseLong(procedureData.get(ParameterAndAttribute.PROCEDURE_ID));
			String name = procedureData.get(ParameterAndAttribute.PROCEDURE_NAME);
			String imageName = procedureData.get(ParameterAndAttribute.PROCEDURE_IMAGE);
			BigDecimal price=new BigDecimal(procedureData.get(ParameterAndAttribute.PROCEDURE_PRICE));
			String description = procedureData.get(ParameterAndAttribute.DESCRIPTION);
			Duration duration = Duration.ofMinutes(Long.parseLong(procedureData.get(ParameterAndAttribute.DURATION)));
			 Procedure procedure = new Procedure.Builder()
					    .setProcedureId(id)
						.setName(name)
					    .setImageName(imageName)
					    .setPrice(price)
					    .setDescription(description)
					    .setDuration(duration)
					    .build();
			isChanged = procedureDao.changeProcedure(procedure);
		} catch (DaoException e) {
			logger.log(Level.ERROR,
					"dao exception in method changeProcedure, procedure data:" + procedureData + ". " + e);
			throw new ServiceException(e);
		}
		return isChanged;
	}
}
