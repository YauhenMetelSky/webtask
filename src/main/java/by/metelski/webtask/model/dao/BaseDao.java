package by.metelski.webtask.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.model.entity.Entity;
import by.metelski.webtask.model.entity.User;

public interface BaseDao<T extends Entity> {
	List<T> findAll() throws DaoException ;
	
	default void close(Statement statement) {
		try {
			if(statement!=null) {
				statement.close();
			}
		} catch(SQLException e) {
			//TODO Log
		}
	}
	default void close(Connection connection) {
		try {
			if(connection!=null) {
				connection.close();
			}
		} catch(SQLException e) {
			//TODO Log
		}
	}
}
