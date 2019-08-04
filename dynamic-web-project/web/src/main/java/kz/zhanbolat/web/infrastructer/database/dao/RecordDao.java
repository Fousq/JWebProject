package kz.zhanbolat.web.infrastructer.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.entity.Item;
import kz.zhanbolat.web.domain.entity.Record;
import kz.zhanbolat.web.infrastructer.exception.DaoException;

public class RecordDao implements AbstractDao<Long, Record> {
	private static Logger logger = LogManager.getLogger(RecordDao.class);
	private static final String SELECT_ALL_RECORDS_WITH_LIMIT_AND_OFFSET = 
			"SELECT id, active, cretedAt, item_id, user_id FROM records "
			+ "LIMIT ? OFFSET ?;";
	private static final String SELECT_RECORDS_BY_USER_ID = 
			"SELECT id, active, createdAt, item_id FROM records "
			+ "WHERE user_id = ?;";
	private static final String CREATE_NEW_RECORD = 
			"INSERT INTO records(active, createdAt, user_id, item_id) "
			+ "VALUES (?, ?, ?, ?);";
	private static final String DELETE_RECORD_BY_ITEM_ID = 
			"DELETE FROM records WHERE item_id = ?;";
	private static final String SELECT_ALL_RECORDS_BY_CATEGORY_ID =
			"SELECT records.id, records.active, records.createdAt, records.user_id, "
			+ "records.item_id FROM records "
			+ "INNER JOIN items ON records.item_id == items.id "
			+ "AND items.category_id == ?;";
	private static final String SELECT_ALL_RECORDS_BY_CATEGORY_ID_WITH_LIMIT =
			"SELECT records.id, records.active, records.createdAt, records.user_id, "
			+ "records.item_id FROM records "
			+ "INNER JOIN items ON records.item_id == items.id "
			+ "AND items.category_id == ?; LIMIT ?";
	private static final String SELECT_ALL_RECORDS_BY_CATEGORY_ID_WITH_LIMIT_AND_OFFSET =
			"SELECT records.id, records.active, records.createdAt, records.user_id, "
			+ "records.item_id FROM records "
			+ "INNER JOIN items ON records.item_id = items.id "
			+ "AND items.category_id = ? LIMIT ? OFFSET ?;";
	private Connection connection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	
	public RecordDao() {
		
	}
	
	public RecordDao(Connection connection) {
		this.connection = connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public List<Record> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Record> findAll(int limit, long offset) throws DaoException {
		List<Record> records = new ArrayList<>();
		try {
			preStatement = connection.prepareStatement(SELECT_ALL_RECORDS_WITH_LIMIT_AND_OFFSET);
			preStatement.setInt(1, limit);
			preStatement.setLong(2, offset);
			resultSet = preStatement.executeQuery();
			while(resultSet.next()) {
				Record record = Record.newBuilder().setId(resultSet.getLong(1))
						.setActive(resultSet.getBoolean(2))
						.setCreateAt(resultSet.getObject(3, LocalDate.class))
						.setItemId(resultSet.getLong(4))
						.setUserId(resultSet.getLong(5))
						.build();
				records.add(record);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		}
		
		return records;
	}
	
	@Override
	public boolean create(Record record) throws DaoException {
		boolean isCreated = false;
		try {
			preStatement = connection.prepareStatement(CREATE_NEW_RECORD);
			preStatement.setBoolean(1, record.isActive());
			preStatement.setObject(2, record.getCreatedAt().plusDays(1));
			preStatement.setLong(3, record.getUserId());
			preStatement.setLong(4, record.getItemId());
			isCreated = preStatement.executeUpdate() == 1;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		
		return isCreated;
	}

	@Override
	public Record read(Long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Record entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Record entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Record> findAllByUserId(long userId) throws DaoException {
		List<Record> records = new ArrayList<>();
		try {
			preStatement = connection.prepareStatement(SELECT_RECORDS_BY_USER_ID);
			preStatement.setLong(1, userId);
			resultSet = preStatement.executeQuery();
			while(resultSet.next()) {
				Record record = Record.newBuilder().setId(resultSet.getLong(1))
						.setActive(resultSet.getBoolean(2))
						.setCreateAt(resultSet.getObject(3, LocalDate.class))
						.setUserId(userId)
						.setItemId(resultSet.getLong(4))
						.build();
				records.add(record);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		
		return records;
	}
	
	public boolean deleteByItemId(long itemId) throws DaoException {
		boolean isDeleted = false;
		try {
			preStatement = connection.prepareStatement(DELETE_RECORD_BY_ITEM_ID);
			preStatement.setLong(1, itemId);
			isDeleted = preStatement.executeUpdate() == 1;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		
		return isDeleted;
	}
	
	public List<Record> findAllRecordsByCategoryId(int categoryId) 
			throws DaoException {
		List<Record> records = new ArrayList<>();
		try {
			preStatement = connection.prepareStatement(SELECT_ALL_RECORDS_BY_CATEGORY_ID);
			preStatement.setInt(1, categoryId);
			resultSet = preStatement.executeQuery();
			while(resultSet.next()) {
				Record record = Record.newBuilder().setId(resultSet.getLong(1))
						.setActive(resultSet.getBoolean(2))
						.setCreateAt(resultSet.getObject(3, LocalDate.class))
						.setUserId(resultSet.getLong(4))
						.setItemId(resultSet.getLong(5))
						.build();
				records.add(record);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		
		return records;
	}
	
	public List<Record> findAllRecordsByCategoryId(int categoryId, int limit) 
			throws DaoException {
		List<Record> records = new ArrayList<>(limit);
		try {
			preStatement = connection.prepareStatement(SELECT_ALL_RECORDS_BY_CATEGORY_ID_WITH_LIMIT);
			preStatement.setInt(1, categoryId);
			preStatement.setInt(2, limit);
			resultSet = preStatement.executeQuery();
			while(resultSet.next()) {
				Record record = Record.newBuilder().setId(resultSet.getLong(1))
						.setActive(resultSet.getBoolean(2))
						.setCreateAt(resultSet.getObject(3, LocalDate.class))
						.setUserId(resultSet.getLong(4))
						.setItemId(resultSet.getLong(5))
						.build();
				records.add(record);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		
		return records;
	}
	
	public List<Record> findAllRecordsByCategoryId(int categoryId, int limit, long offset) 
			throws DaoException {
		List<Record> records = new ArrayList<>(limit);
		try {
			preStatement = connection.prepareStatement(SELECT_ALL_RECORDS_BY_CATEGORY_ID_WITH_LIMIT_AND_OFFSET);
			preStatement.setInt(1, categoryId);
			preStatement.setInt(2, limit);
			preStatement.setLong(3, offset);
			resultSet = preStatement.executeQuery();
			while(resultSet.next()) {
				Record record = Record.newBuilder().setId(resultSet.getLong(1))
						.setActive(resultSet.getBoolean(2))
						.setCreateAt(resultSet.getObject(3, LocalDate.class))
						.setUserId(resultSet.getLong(4))
						.setItemId(resultSet.getLong(5))
						.build();
				records.add(record);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		
		return records;
	}
	
}
