package kz.zhanbolat.web.application.service;

import java.sql.Connection;

import kz.zhanbolat.web.domain.entity.Item;
import kz.zhanbolat.web.infrastructer.database.dao.AbstractDao;
import kz.zhanbolat.web.infrastructer.database.dao.ItemDao;
import kz.zhanbolat.web.infrastructer.database.pool.ConnectionPool;

public class ItemService {
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
		boolean created = dao.create(item);
		return created;
	}
	
}
