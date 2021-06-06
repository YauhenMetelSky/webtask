package by.metelski.webtask.model.dao;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.exception.DaoException;

public interface ProcedureDao {
	
	boolean add(Procedure procedure) throws DaoException;
	boolean changeProcedure(Procedure procedure) throws DaoException;
	boolean setIsActive(long id, boolean isActive) throws DaoException;
	Optional<Duration> findDuration(long procedureId) throws DaoException;
	List<Procedure> findAll() throws DaoException;
	List<Procedure> findAllActive() throws DaoException;
	Optional<Procedure> findByName() throws DaoException;
	Optional<Procedure> findById(long id) throws DaoException;
}
