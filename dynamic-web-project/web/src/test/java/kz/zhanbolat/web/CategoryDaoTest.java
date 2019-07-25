package kz.zhanbolat.web;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kz.zhanbolat.web.domain.entity.Category;
import kz.zhanbolat.web.infrastructer.database.dao.CategoryDao;
import kz.zhanbolat.web.infrastructer.database.pool.ConnectionPool;

public class CategoryDaoTest {
	private static Logger logger = LogManager.getLogger(CategoryDaoTest.class);
	private CategoryDao dao;
	private Connection connection;
	
	@Before
	public void init() {
		dao = new CategoryDao();
		connection = ConnectionPool.INSTANCE.getConnection();
		dao.setConnection(connection);
	}
	
	@Test
	public void daoShouldFindAll() {
		List<Category> categories = dao.findAll();
		assertTrue(categories.size() == 5);
		categories.forEach(category -> logger.debug(category));
	}
	
	@After
	public void finish() throws SQLException {
		connection.close();
	}
	
}
