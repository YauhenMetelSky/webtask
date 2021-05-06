package by.metelski.webtask.model.dao.impl;

import static by.metelski.webtask.model.dao.ColumnName.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.entity.User.Role;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.model.connection.ConnectionPool;
import by.metelski.webtask.model.dao.ProcedureDao;

public class ProcedureDaoImpl implements ProcedureDao {
	private static final Logger logger = LogManager.getLogger();
	private static final String SQL_ADD_PROCEDURE = "INSERT INTO procedures (name,image_name,price,description,duration) values(?,?,?,?,?)";
	private static final String SQL_FIND_ALL_PROCEDURES = "SELECT procedure_id,name,image_name,is_active,price,description,duration FROM procedures";
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	@Override
	public boolean add(Procedure procedure) throws DaoException {
		logger.log(Level.INFO, "try to add procedure: " + procedure);
		boolean isAdded = false;
		try (Connection connection = connectionPool.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_ADD_PROCEDURE);
			statement.setString(1, procedure.getName());
			statement.setString(2, procedure.getImageName());
			statement.setBigDecimal(3, procedure.getPrice());
			statement.setString(4, procedure.getDescription());
			statement.setLong(5, procedure.getDuration().toMinutes());
			int rowCount = statement.executeUpdate();
			if (rowCount != 0) {
				isAdded = true;
				logger.log(Level.INFO, "procedure:" + procedure +" added");
			} else {
				logger.log(Level.ERROR, "procedure was not added");
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException("dao exception in method when we try to add procedure:" + procedure, e);
		}
		return isAdded;
	}

	@Override
	public boolean activate(long id) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deActivate(long id) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Procedure> findAll() throws DaoException {
		List<Procedure> procedures = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_PROCEDURES)) {
			while (resultSet.next()) {
				long procedureId = (long)resultSet.getInt(PROCEDURE_ID);
				String name = resultSet.getString(NAME);
				String imageName = resultSet.getString(IMAGE_NAME);
				boolean isActive = resultSet.getBoolean(IS_ACTIVE);
				BigDecimal price = resultSet.getBigDecimal(PRICE);
				String description = resultSet.getString(DESCRIPTION);
				Duration duration = Duration.ofMinutes(resultSet.getLong(DURATION));
				logger.log(Level.DEBUG, "procedure id:" + procedureId + " procedure name:" + name + " image name:" + imageName);
				procedures.add(new Procedure(procedureId,name,imageName,price,isActive,description,duration));//TODO Use builder???
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in findAll: " + e.getMessage() + " : " + e.getErrorCode());
			throw new DaoException("Dao exception", e);
		}
		return procedures;
	}

	@Override
	public Optional<Procedure> findByName() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Procedure> findById() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
