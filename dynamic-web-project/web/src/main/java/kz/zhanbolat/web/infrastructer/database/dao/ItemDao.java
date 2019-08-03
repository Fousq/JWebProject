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
		// TODO Auto-generated method stub
		return null;
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
	
}
