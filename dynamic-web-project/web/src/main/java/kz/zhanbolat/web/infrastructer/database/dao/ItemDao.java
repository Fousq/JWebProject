package kz.zhanbolat.web.infrastructer.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.entity.Item;
import kz.zhanbolat.web.infrastructer.exception.DaoException;

public class ItemDao implements AbstractDao<Long, Item> {
	private static Logger logger = LogManager.getLogger(ItemDao.class);
	private static final String CREATE_NEW_ITEM = 
			"INSERT INTO items(name, description, price, category_id) VALUES (?, ?, ?, ?);";
	private Connection connection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	
	public ItemDao() {
		super();
	}
	
	public ItemDao(Connection connection) {
		this.connection = connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public List<Item> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Item item) throws DaoException {
		try {
			preStatement = connection.prepareStatement(CREATE_NEW_ITEM);
			preStatement.setString(1, item.getName());
			preStatement.setString(2, item.getDescription());
			preStatement.setInt(3, item.getPrice());
			preStatement.setInt(4, item.getCategoryId());
			preStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		
		return true;
	}

	@Override
	public Item read(Long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Item entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Item entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

}
