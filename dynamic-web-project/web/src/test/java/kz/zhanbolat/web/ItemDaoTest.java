package kz.zhanbolat.web;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kz.zhanbolat.web.domain.entity.Item;
import kz.zhanbolat.web.infrastructer.database.dao.ItemDao;
import kz.zhanbolat.web.infrastructer.database.pool.ConnectionPool;

public class ItemDaoTest {
	private static Logger logger = LogManager.getLogger(ItemDaoTest.class);
	private ItemDao dao;
	private Connection connection;
	
	@Before
	public void init() {
		dao = new ItemDao();
		connection = ConnectionPool.INSTANCE.getConnection();
		dao.setConnection(connection);
	}
	
	@Test
	public void itemShouldBeCreatedInDB() {
		Item item = Item.newBuilder()
							.setName("name")
							.setDescription("description")
							.setPrice(123)
							.setCategoryId(1)
							.build();
		boolean created = dao.create(item);
		assertTrue(created);
	}
	
}
