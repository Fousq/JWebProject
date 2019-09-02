package kz.zhanbolat.web;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import kz.zhanbolat.web.domain.entity.Item;
import kz.zhanbolat.web.infrastructer.database.dao.ItemDao;
import kz.zhanbolat.web.infrastructer.database.pool.ConnectionPool;
import kz.zhanbolat.web.infrastructer.exception.DaoException;

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
	@Ignore
	public void itemShouldBeCreatedInDB() throws DaoException {
		Item item = Item.newBuilder()
							.setName("name")
							.setDescription("description")
							.setPrice(123)
							.setCategoryId(1)
							.build();
		boolean created = dao.create(item);
		assertTrue(created);
	}
	
	@Test
	@Ignore
	public void shouldFindAllUsersItem() throws DaoException {
		List<Item> items = new ArrayList<>();
		long userId = 10;
		items = dao.findAllItemByUserId(userId);
		assertTrue(items.size() == 1);
		items.forEach(item -> logger.debug(item));
	}
	
	@Test
	@Ignore
	public void itemShouldBeDeleted() throws DaoException {
		long itemId = 1;
		Item item = Item.newBuilder().setId(itemId)
				.build();
		boolean isDeleted = dao.delete(item);
		assertTrue(isDeleted);
	}
	
	@After
	public void destroy() throws SQLException {
		connection.close();
	}
	
}
