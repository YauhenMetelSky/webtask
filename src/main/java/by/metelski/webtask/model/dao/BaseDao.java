package by.metelski.webtask.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.model.entity.Entity;

public interface BaseDao<T extends Entity> {
	public static final Logger logger = LogManager.getLogger();
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
