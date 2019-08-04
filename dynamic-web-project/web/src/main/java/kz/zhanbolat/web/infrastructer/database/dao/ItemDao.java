package kz.zhanbolat.web.infrastructer.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.entity.Item;
import kz.zhanbolat.web.infrastructer.exception.DaoException;

public class ItemDao implements AbstractDao<Long, Item> {
	private static Logger logger = LogManager.getLogger(ItemDao.class);
	private static final String SELECT_ALL_ITEMS = 
			"SELECT id, name, description, price, category_id FROM items;";
	private static final String SELECT_ALL_ITEMS_WITH_LIMIT = 
			"SELECT id, name, description, price, category_id FROM items "
			+ "LIMIT ?;";
	private static final String SELECT_ALL_ITEMS_WITH_LIMIT_BEGIN_OFFSET = 
			"SELECT id, name, description, price, category_id FROM items "
			+ "LIMIT ? OFFSET ?;";
	private static final String CREATE_NEW_ITEM = 
			"INSERT INTO items(name, description, price, category_id) VALUES (?, ?, ?, ?);";
	private static final String SELECT_ITEM_BY_ID = 
			"SELECT name, description, price, category_id FROM items "
			+ "WHERE id = ?;";
	private static final String SELECT_ITEM_BY_USER_ID = 
			"SELECT items.id, items.name, items.description, items.price, "
			+ "items.category_id FROM items "
			+ "INNER JOIN records ON items.id = records.item_id "
			+ "AND records.user_id = ?;";
	private static final String DELETE_ITEM_BY_ID = 
			"DELETE FROM items WHERE id = ?;";
	private static final String SELECT_ALL_ITEMS_BY_CATEGORY_ID = 
			"SELECT id, name, description, price FROM items "
			+ "WHERE category_id = ?;";
	private static final String SELECT_ALL_ITEMS_BY_CATEGORY_ID_WITH_LIMIT =
			"SELECT id, name, description, price FROM items "
			+ "WHERE category_id = ? LIMIT ?;";
	private static final String SELECT_ALL_ITEMS_BY_CATEGORY_ID_WITH_LIMIT_BEGIN_OFFSET =
			"SELECT id, name, description, price FROM items "
			+ "WHERE category_id = ? LIMIT ? OFFSET ?;";
	private Connection connection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	private long generatedId;
	
	public ItemDao() {
		super();
	}
	
	public ItemDao(Connection connection) {
		this.connection = connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public long getGeneratedId() {
		return generatedId;
	}
	
	@Override
	public List<Item> findAll() throws DaoException {
		List<Item> items = new ArrayList<>();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_ALL_ITEMS);
			while(resultSet.next()) {
				Item item = Item.newBuilder().setId(resultSet.getLong(1))
						.setName(resultSet.getString(2))
						.setDescription(resultSet.getString(3))
						.setPrice(resultSet.getInt(4))
						.setCategoryId(resultSet.getInt(5))
						.build();
				items.add(item);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(statement, logger);
			closeConnection(connection, logger);
		}
		
		return items;
	}
	
	public List<Item> findAll(int limit) throws DaoException {
		List<Item> items = new ArrayList<>(limit);
		try {
			preStatement = connection.prepareStatement(SELECT_ALL_ITEMS_WITH_LIMIT);
			preStatement.setInt(1, limit);
			resultSet = preStatement.executeQuery();
			while(resultSet.next()) {
				Item item = Item.newBuilder().setId(resultSet.getLong(1))
						.setName(resultSet.getString(2))
						.setDescription(resultSet.getString(3))
						.setPrice(resultSet.getInt(4))
						.setCategoryId(resultSet.getInt(5))
						.build();
				items.add(item);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		
		return items;
	}
	
	public List<Item> findAll(int limit, long offset) throws DaoException {
		List<Item> items = new ArrayList<>(limit);
		try {
			preStatement = 
					connection.prepareStatement(SELECT_ALL_ITEMS_WITH_LIMIT_BEGIN_OFFSET);
			preStatement.setInt(1, limit);
			preStatement.setLong(2, offset);
			resultSet = preStatement.executeQuery();
			while(resultSet.next()) {
				Item item = Item.newBuilder().setId(resultSet.getLong(1))
						.setName(resultSet.getString(2))
						.setDescription(resultSet.getString(3))
						.setPrice(resultSet.getInt(4))
						.setCategoryId(resultSet.getInt(5))
						.build();
				items.add(item);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		
		return items;
	}
	
	@Override
	public boolean create(Item item) throws DaoException {
		try {
			preStatement = connection.prepareStatement(CREATE_NEW_ITEM, 
					Statement.RETURN_GENERATED_KEYS);
			preStatement.setString(1, item.getName());
			preStatement.setString(2, item.getDescription());
			preStatement.setInt(3, item.getPrice());
			preStatement.setInt(4, item.getCategoryId());
			preStatement.executeUpdate();
			resultSet = preStatement.getGeneratedKeys();
			if (resultSet.next()) {
				generatedId = resultSet.getLong(1);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		
		return true;
	}

	@Override
	public Item read(Long id) throws DaoException {
		Item item = null;
		try {
			preStatement = connection.prepareStatement(SELECT_ITEM_BY_ID);
			preStatement.setLong(1, id);
			resultSet = preStatement.executeQuery();
			if (resultSet.next()) {
				item = Item.newBuilder().setId(id)
						.setName(resultSet.getString(1))
						.setDescription(resultSet.getString(2))
						.setPrice(3)
						.setCategoryId(4)
						.build();
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		return item;
	}

	@Override
	public boolean update(Item entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Item item) throws DaoException {
		boolean isDeleted = false;
		try {
			preStatement = connection.prepareStatement(DELETE_ITEM_BY_ID);
			preStatement.setLong(1, item.getId());
			isDeleted = preStatement.executeUpdate() == 1;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		
		return isDeleted;
	}
	
	public List<Item> findAllItemByUserId(long userId) throws DaoException {
		List<Item> items = null;
		try {
			preStatement = connection.prepareStatement(SELECT_ITEM_BY_USER_ID);
			preStatement.setLong(1, userId);
			resultSet = preStatement.executeQuery();
			items = new ArrayList<>();
			while(resultSet.next()) {
				Item item = Item.newBuilder().setId(resultSet.getLong(1))
						.setName(resultSet.getString(2))
						.setDescription(resultSet.getString(3))
						.setPrice(resultSet.getInt(4))
						.setCategoryId(resultSet.getInt(5))
						.build();
				items.add(item);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		
		return items;
	}
	
	public List<Item> findAllByCategoryId(int categoryId) throws DaoException {
		List<Item> items = new ArrayList<>();
		try {
			preStatement = connection.prepareStatement(SELECT_ALL_ITEMS_BY_CATEGORY_ID);
			preStatement.setInt(1, categoryId);
			resultSet = preStatement.executeQuery();
			while(resultSet.next()) {
				Item item = Item.newBuilder().setId(resultSet.getLong(1))
						.setName(resultSet.getString(2))
						.setDescription(resultSet.getString(3))
						.setPrice(resultSet.getInt(4))
						.setCategoryId(categoryId)
						.build();
				items.add(item);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		
		return items;
	}
	
	public List<Item> findAllByCategoryId(int categoryId, int limit) throws DaoException {
		List<Item> items = new ArrayList<>(limit);
		try {
			preStatement = connection.prepareStatement(SELECT_ALL_ITEMS_BY_CATEGORY_ID_WITH_LIMIT);
			preStatement.setInt(1, categoryId);
			preStatement.setInt(2, limit);
			resultSet = preStatement.executeQuery();
			while (resultSet.next()) {
				Item item = Item.newBuilder().setId(resultSet.getLong(1))
						.setName(resultSet.getString(2))
						.setDescription(resultSet.getString(3))
						.setPrice(resultSet.getInt(4))
						.setCategoryId(categoryId)
						.build();
				items.add(item);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		
		return items;
	}
	
	public List<Item> findAllByCategoryId(int categoryId, int limit, long offset) throws DaoException {
		List<Item> items = new ArrayList<>(limit);
		try {
			preStatement = connection.prepareStatement(SELECT_ALL_ITEMS_BY_CATEGORY_ID_WITH_LIMIT_BEGIN_OFFSET);
			preStatement.setInt(1, categoryId);
			preStatement.setInt(2, limit);
			preStatement.setLong(3, offset);
			resultSet = preStatement.executeQuery();
			while (resultSet.next()) {
				Item item = Item.newBuilder().setId(resultSet.getLong(1))
						.setName(resultSet.getString(2))
						.setDescription(resultSet.getString(3))
						.setPrice(resultSet.getInt(4))
						.setCategoryId(categoryId)
						.build();
				items.add(item);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		return items;
	}
	
}
