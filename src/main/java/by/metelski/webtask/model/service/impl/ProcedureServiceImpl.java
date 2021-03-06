package by.metelski.webtask.model.service.impl;

import static by.metelski.webtask.command.ParameterAndAttribute.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.ProcedureDao;
import by.metelski.webtask.model.service.ProcedureService;
import by.metelski.webtask.validator.ProcedureValidator;

/**
 * Class procedure service
 * 
 * @author Yauhen Metelski
 *
 */
public class ProcedureServiceImpl implements ProcedureService {
	private static final Logger logger = LogManager.getLogger();
	private final ProcedureDao procedureDao;

	public ProcedureServiceImpl(ProcedureDao procedureDao) {
		this.procedureDao = procedureDao;
	}

	@Override
	public boolean add(Map<String, String> procedureData) throws ServiceException {
		logger.log(Level.DEBUG, "add(), procedureData:" + procedureData);
		boolean isAdded = false;
		if (!checkData(procedureData, 0)) {
			logger.log(Level.DEBUG, "In if block");
			return isAdded;
		}
		Duration duration = Duration.ofMinutes(Long.parseLong(procedureData.get(DURATION)));
		Procedure procedure = new Procedure.Builder()
				.setName(procedureData.get(PROCEDURE_NAME))
				.setImageName(procedureData.get(PROCEDURE_IMAGE))
				.setPrice(new BigDecimal(procedureData.get(PROCEDURE_PRICE)))
				.setDescription(procedureData.get(DESCRIPTION))
				.setDuration(duration)
				.build();
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
	public Optional<Procedure> findById(long id) throws ServiceException {
		logger.log(Level.DEBUG, "findBYId, procedure id:" + id);
		Optional<Procedure> procedure;
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
			isChanged = procedureDao.setIsActive(id, isActive);
		} catch (DaoException e) {
			logger.log(Level.ERROR, "dao exception in method setIsActive" + e);
			throw new ServiceException(e);
		}
		return isChanged;
	}

	@Override
	public boolean changeProcedure(Map<String, String> procedureData) throws ServiceException {
		logger.log(Level.DEBUG, "Change procedure; data" + procedureData);
		boolean isChanged = false;
		if (!checkData(procedureData, 0)) {
			logger.log(Level.DEBUG, "Wrong procedure data" + procedureData);
			return isChanged;
		}
		try {
			long id = Long.parseLong(procedureData.get(PROCEDURE_ID));
			String name = procedureData.get(PROCEDURE_NAME);
			String imageName = procedureData.get(PROCEDURE_IMAGE);
			BigDecimal price = new BigDecimal(procedureData.get(PROCEDURE_PRICE));
			String description = procedureData.get(DESCRIPTION);
			Duration duration = Duration.ofMinutes(Long.parseLong(procedureData.get(DURATION)));
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

	/**
	 * @param data
	 * @param withId two possible values: 0-if data dont't contain appointment id,
	 *               1-if data contains appointmentId
	 * @return boolean true if data valid
	 */
	private boolean checkData(Map<String, String> data, int withId) {
		boolean isValid = true;
		switch (withId) {
		case 0:
			if (!ProcedureValidator.isOnlyNumbers(data.get(ParameterAndAttribute.DURATION))) {
				logger.log(Level.DEBUG, "first" + data.get(ParameterAndAttribute.DURATION));
				isValid = false;
				break;
			}
			if (!ProcedureValidator.isValidName(data.get(ParameterAndAttribute.PROCEDURE_NAME))) {
				logger.log(Level.DEBUG, "Second");
				isValid = false;
				break;
			}
			if (!ProcedureValidator.isValidImageName(data.get(ParameterAndAttribute.PROCEDURE_IMAGE))) {
				logger.log(Level.DEBUG, "3");
				isValid = false;
				break;
			}
			if (!ProcedureValidator.isValidPrice(data.get(ParameterAndAttribute.PROCEDURE_PRICE))) {
				isValid = false;
				logger.log(Level.DEBUG, "4");
				break;
			}
			if (!ProcedureValidator.isValidDescription(data.get(ParameterAndAttribute.DESCRIPTION))) {
				isValid = false;
				logger.log(Level.DEBUG, "5");
				break;
			}
			break;
		case 1:
			if (!ProcedureValidator.isOnlyNumbers(data.get(ParameterAndAttribute.DURATION))) {
				isValid = false;
				logger.log(Level.DEBUG, "6");
				break;
			}
			if (!ProcedureValidator.isOnlyNumbers(data.get(ParameterAndAttribute.PROCEDURE_ID))) {
				isValid = false;
				logger.log(Level.DEBUG, "7");
				break;
			}
			if (!ProcedureValidator.isValidName(data.get(ParameterAndAttribute.PROCEDURE_NAME))) {
				isValid = false;
				logger.log(Level.DEBUG, "8");
				break;
			}
			if (!ProcedureValidator.isValidImageName(data.get(ParameterAndAttribute.PROCEDURE_IMAGE))) {
				isValid = false;
				logger.log(Level.DEBUG, "9");
				break;
			}
			if (!ProcedureValidator.isValidPrice(data.get(ParameterAndAttribute.PROCEDURE_PRICE))) {
				isValid = false;
				logger.log(Level.DEBUG, "10");
				break;
			}
			if (!ProcedureValidator.isValidDescription(data.get(ParameterAndAttribute.DESCRIPTION))) {
				isValid = false;
				logger.log(Level.DEBUG, "11");
				break;
			}
			break;
		default:
			isValid = false;
		}
		return isValid;
	}
}
