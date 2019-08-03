package kz.zhanbolat.web;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kz.zhanbolat.web.infrastructer.database.dao.RecordDao;
import kz.zhanbolat.web.infrastructer.database.pool.ConnectionPool;
import kz.zhanbolat.web.infrastructer.exception.DaoException;

public class RecordDaoTest {
	private static Logger logger = LogManager.getLogger(RecordDaoTest.class);
	private RecordDao dao;
	private Connection connection;
	
	@Before
	public void init() {
		dao = new RecordDao();
		connection = ConnectionPool.INSTANCE.getConnection();
		dao.setConnection(connection);
	}
	
	@Test
	public void recordShouldBeDeletedByItemId() throws DaoException {
		long itemId = 2;
		boolean isDeleted = dao.deleteByItemId(itemId);
		assertTrue(isDeleted);
	}
	
	@After
	public void destroy() throws SQLException {
		connection.close();
	}
	
}
