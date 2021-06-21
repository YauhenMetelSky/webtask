package by.metelski.webtask.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.exception.ServiceException;

/**
 * The interface procedure service
 * @author Yauhen Metelski
 *
 */
public interface ProcedureService {
	/**
	 * @param procedureData
	 * @return boolean result of operation
	 * @throws ServiceException
	 */
	boolean add(Map<String, String> procedureData) throws ServiceException;

	/**
	 * @param id
	 * @param isActive
	 * @return boolean result of operation
	 * @throws ServiceException
	 */
	boolean setIsActive(long id, boolean isActive) throws ServiceException;

	/**
	 * @param procedureData
	 * @return boolean result of operation
	 * @throws ServiceException
	 */
	boolean changeProcedure(Map<String, String> procedureData) throws ServiceException;

	/**
	 * @return List of procedures, all procedures from database
	 * @throws ServiceException
	 */
	List<Procedure> findAll() throws ServiceException;

	/**
	 * @return List of all active procedures (is_active=true)
	 * @throws ServiceException
	 */
	List<Procedure> findAllActive() throws ServiceException;

	/**
	 * @param id
	 * @return Optional<Procedure>
	 * @throws ServiceException
	 */
	Optional<Procedure> findById(long id) throws ServiceException;
}
