package kz.zhanbolat.web.application.service;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.entity.Item;
import kz.zhanbolat.web.infrastructer.database.dao.AbstractDao;
import kz.zhanbolat.web.infrastructer.database.dao.ItemDao;
import kz.zhanbolat.web.infrastructer.database.pool.ConnectionPool;
import kz.zhanbolat.web.infrastructer.exception.DaoException;

public class ItemService {
	private static Logger logger = LogManager.getLogger(ItemService.class);
	private AbstractDao<Long, Item> dao;
	private Connection connection;
	
	public ItemService() {
		dao = new ItemDao();
	}
	
	public boolean createNewItem(String name, String description, int price, 
							  int categoryId) {
		Item item = Item.newBuilder()
						 .setName(name)
						 .setDescription(description)
						 .setPrice(price)
						 .setCategoryId(categoryId)
						 .build();
		connection = ConnectionPool.INSTANCE.getConnection();
		dao.setConnection(connection);
		boolean created;
		try {
			created = dao.create(item);
		} catch (DaoException e) {
			logger.error("Error on creation new item.", e);
			created = false;
		}
		return created;
	}
	
}
