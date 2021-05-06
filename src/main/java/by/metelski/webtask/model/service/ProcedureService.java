package by.metelski.webtask.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.exception.ServiceException;

public interface ProcedureService {
	boolean add(Map<String, String> procedureData) throws ServiceException;

	boolean activate(long id) throws ServiceException;

	boolean deActivate(long id) throws ServiceException;

	List<Procedure> findAll() throws ServiceException;

	Optional<Procedure> findByName() throws ServiceException;

	Optional<Procedure> findById() throws ServiceException;
}
