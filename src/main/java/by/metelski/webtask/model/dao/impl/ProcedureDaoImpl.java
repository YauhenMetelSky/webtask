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
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.metelski.webtask.entity.Procedure;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.model.connection.ConnectionPool;
import by.metelski.webtask.model.dao.ProcedureDao;

/**
 * Class procedure dao
 * @author Yauhen Metelski
 *
 */
public class ProcedureDaoImpl implements ProcedureDao {
	private static final Logger logger = LogManager.getLogger();
	private static final String SQL_ADD_PROCEDURE = "INSERT INTO procedures (name,image_name,price,description,duration) values(?,?,?,?,?)";
	private static final String SQL_CHANGE_PROCEDURE = "UPDATE procedures SET name=?,image_name=?,price=?,description=?,duration=? WHERE procedure_id=?";
	private static final String SQL_FIND_ALL_PROCEDURES = "SELECT procedure_id,name,image_name,is_active,price,description,duration FROM procedures";
	private static final String SQL_FIND_ALL_ACTIVE_PROCEDURES = "SELECT procedure_id,name,image_name,is_active,price,description,duration FROM procedures WHERE is_active=true";
	private static final String SQL_FIND_PROCEDURE_BY_ID = "SELECT procedure_id,name,image_name,is_active,price,description,duration FROM procedures WHERE procedure_id=?";
	private static final String SQL_FIND_DURATION = "SELECT duration FROM procedures WHERE procedure_id=?";
	private static final String SQL_UPDATE_IS_ACTIVE = "UPDATE procedures set is_active=? WHERE procedure_id=?";
	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public boolean add(Procedure procedure) throws DaoException {
		logger.log(Level.INFO, "try to add procedure: " + procedure);
		boolean isAdded = false;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_ADD_PROCEDURE)) {
			statement.setString(1, procedure.getName());
			statement.setString(2, procedure.getImageName());
			statement.setBigDecimal(3, procedure.getPrice());
			statement.setString(4, procedure.getDescription());
			statement.setLong(5, procedure.getDuration().toMinutes());
			int rowCount = statement.executeUpdate();
			if (rowCount != 0) {
				isAdded = true;
				logger.log(Level.INFO, "procedure:" + procedure + " added");
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
	public List<Procedure> findAll() throws DaoException {
		List<Procedure> procedures = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_PROCEDURES)) {
			while (resultSet.next()) {
				Procedure procedure = createProcedure(resultSet);
				procedures.add(procedure);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in findAll: " + e.getMessage() + " : " + e.getErrorCode());
			throw new DaoException("Dao exception", e);
		}
		return procedures;
	}

	@Override
	public List<Procedure> findAllActive() throws DaoException {
		List<Procedure> procedures = new ArrayList<>();
		try (Connection connection = connectionPool.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_ACTIVE_PROCEDURES)) {
			while (resultSet.next()) {
				Procedure procedure = createProcedure(resultSet);
				procedures.add(procedure);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in findAll: " + e.getMessage() + " : " + e.getErrorCode());
			throw new DaoException("Dao exception", e);
		}
		return procedures;
	}

	@Override
	public Optional<Duration> findDuration(long procedureId) throws DaoException {
		Optional<Duration> duration;
		logger.log(Level.INFO, "Find duration by procedure, ID: " + procedureId);
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_DURATION)) {
			statement.setLong(1, procedureId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Duration procedureDuration = Duration.ofMinutes(resultSet.getLong(DURATION));
				duration = Optional.of(procedureDuration);
			} else {
				logger.log(Level.INFO, "didn't find duration, procedure id:" + procedureId);
				duration = Optional.empty();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException("Dao exception in method finadDuration, procedure id:" + procedureId
					+ " error code: " + e.getErrorCode(), e);
		}
		return duration;
	}

	@Override
	public Optional<Procedure> findById(long id) throws DaoException {
		Optional<Procedure> optional;
		logger.log(Level.INFO, "Find  procedure by, ID: " + id);
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_PROCEDURE_BY_ID)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Procedure procedure = createProcedure(resultSet);
				optional = Optional.of(procedure);
			} else {
				logger.log(Level.INFO, "didn't find procedure, procedure id:" + id);
				optional = Optional.empty();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException(
					"SQL exception in method findById, procedure id:" + id + " error code: " + e.getErrorCode(), e);
		}
		return optional;
	}

	@Override
	public boolean setIsActive(long id, boolean isActive) throws DaoException {
		logger.log(Level.INFO, "set procedure isActive, procedureId:" + id + ", isActive:" + isActive);
		boolean isChanged = false;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_IS_ACTIVE)) {
			statement.setBoolean(1, isActive);
			statement.setLong(2, id);
			int rowCount = statement.executeUpdate();
			if (rowCount != 0) {
				isChanged = true;
				logger.log(Level.INFO, "procedureid:" + id + " isActive:" + isActive);
			} else {
				logger.log(Level.ERROR, "procedure isActive was not changed");
			}

		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException("exception in method when we try to setIsActive procedureId:" + id, e);
		}
		return isChanged;
	}

	@Override
	public boolean changeProcedure(Procedure procedure) throws DaoException {
		logger.log(Level.INFO, "Try to change procedure in db" + procedure);
		boolean isChanged = false;
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_PROCEDURE)) {
			statement.setString(1, procedure.getName());
			statement.setString(2, procedure.getImageName());
			statement.setBigDecimal(3, procedure.getPrice());
			statement.setString(4, procedure.getDescription());
			statement.setLong(5, procedure.getDuration().toMinutes());
			statement.setLong(6, procedure.getProcedureId());
			int rowCount = statement.executeUpdate();
			if (rowCount != 0) {
				isChanged = true;
				logger.log(Level.INFO, "procedure changed:" + procedure);
			} else {
				logger.log(Level.ERROR, "procedure was not changed");
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DaoException(
					"SQL exception in method changeProcedure, when we try to change procedure:" + procedure, e);
		}
		return isChanged;
	}

	/**
	 * @param resultSet
	 * @return new Procedure
	 * @throws SQLException
	 */
	private Procedure createProcedure(ResultSet resultSet) throws SQLException {
		long procedureId = (long) resultSet.getInt(PROCEDURE_ID);
		String name = resultSet.getString(NAME);
		String imageName = resultSet.getString(IMAGE_NAME);
		boolean isActive = resultSet.getBoolean(IS_ACTIVE);
		BigDecimal price = resultSet.getBigDecimal(PRICE);
		String description = resultSet.getString(DESCRIPTION);
		Duration duration = Duration.ofMinutes(resultSet.getLong(DURATION));
		logger.log(Level.DEBUG, "procedure id:" + procedureId + " procedure name:" + name + " image name:" + imageName);
		Procedure procedure = new Procedure.Builder()
				.setProcedureId(procedureId)
				.setName(name)
				.setImageName(imageName)
				.setPrice(price)
				.setIsActive(isActive)
				.setDescription(description)
				.setDuration(duration)
				.build();
		return procedure;
	}
}
