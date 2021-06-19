package by.metelski.webtask.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.exception.ServiceException;

public interface ProcedureService {
	boolean add(Map<String, String> procedureData) throws ServiceException;
	boolean setIsActive(long id, boolean isActive) throws ServiceException;
	boolean changeProcedure(Map<String,String> procedureData) throws ServiceException;
	List<Procedure> findAll() throws ServiceException;
	List<Procedure> findAllActive() throws ServiceException;
	Optional<Procedure> findById(long id) throws ServiceException;
}
