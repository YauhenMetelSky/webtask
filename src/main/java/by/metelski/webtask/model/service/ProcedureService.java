package by.metelski.webtask.model.service;

import java.util.List;
import java.util.Optional;

import by.metelski.webtask.entity.Procedure;

public interface ProcedureService {
	boolean add(Procedure procedure);

	boolean activate(long id);

	boolean deActivate(long id);

	List<Procedure> findAll();

	Optional<Procedure> findByName();

	Optional<Procedure> findById();
}
