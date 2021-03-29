package by.metelski.webtask.model.dao;

import java.util.List;
import java.util.Optional;

import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.model.entity.User;

public interface BaseDao {
	List<User>FindAllUsers() throws DaoException;
	List<User>FindUsersByName(String userName) throws DaoException;
	Optional<String>findPasswordByLogin(String login) throws DaoException;
	Optional<User>findUserByLogin(String login) throws DaoException;
}
