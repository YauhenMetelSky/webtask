package by.metelski.webtask.model.dao;

import java.util.List;
import java.util.Optional;

import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.exception.DaoException;

public interface ProcedureDao {
	
	boolean add(Procedure procedure) throws DaoException;

	boolean activate(long id) throws DaoException;

	boolean deActivate(long id) throws DaoException;
	
	int findDuration(int procedureId) throws DaoException;

	List<Procedure> findAll() throws DaoException;

	Optional<Procedure> findByName() throws DaoException;

	Optional<Procedure> findById() throws DaoException;
}
