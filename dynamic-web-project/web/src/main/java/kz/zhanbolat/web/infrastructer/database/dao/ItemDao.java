package kz.zhanbolat.web.infrastructer.database.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.entity.Item;

public class ItemDao extends AbstractDao<Long, Item> {
	private static Logger logger = LogManager.getLogger(ItemDao.class);
	private static final String CREATE_NEW_ITEM = 
			"INSERT INTO items(name, description, price, category_id) VALUES (?, ?, ?, ?);";
	
	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Item item) {
		try {
			PreparedStatement preState = connection.prepareStatement(CREATE_NEW_ITEM);
			preState.setString(1, item.getName());
			preState.setString(2, item.getDescription());
			preState.setInt(3, item.getPrice());
			preState.setInt(4, item.getCategoryId());
			preState.executeUpdate();
		} catch (SQLException e) {
			logger.error("ERROR on creating new item.", e);
			return false;
		}
		
		return true;
	}

	@Override
	public Item read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Item entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Item entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
