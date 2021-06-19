package by.metelski.webtask.model.dao;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.exception.DaoException;

/**
 * The interface procedure dao
 * @author Yauhen Metelski
 *
 */
public interface ProcedureDao {

	/**
	 * Add procedure
	 * @param procedure
	 * @return boolean result of operation
	 * @throws DaoException
	 */
	boolean add(Procedure procedure) throws DaoException;

	/**
	 * Change procedure
	 * @param procedure
	 * @return boolean result of operation
	 * @throws DaoException
	 */
	boolean changeProcedure(Procedure procedure) throws DaoException;

	/**
	 * Activate procedure
	 * @param id
	 * @param isActive
	 * @return boolean result of operation
	 * @throws DaoException
	 */
	boolean setIsActive(long id, boolean isActive) throws DaoException;

	/**
	 * Find duration of procedure
	 * @param procedureId
	 * @return Optional<Procedure>
	 * @throws DaoException
	 */
	Optional<Duration> findDuration(long procedureId) throws DaoException;

	/**
	 * Find all procedures
	 * @return List of procedures
	 * @throws DaoException
	 */
	List<Procedure> findAll() throws DaoException;

	/**
	 * Find all activated procedures
	 * @return List of procedures
	 * @throws DaoException
	 */
	List<Procedure> findAllActive() throws DaoException;

	/**
	 * Find procedure by id
	 * @param id
	 * @return Optional<Procedure>
	 * @throws DaoException
	 */
	Optional<Procedure> findById(long id) throws DaoException;
}
