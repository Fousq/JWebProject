package kz.zhanbolat;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kz.zhanbolat.database.dao.UserDao;
import kz.zhanbolat.database.pool.ConnectionPool;
import kz.zhanbolat.entity.User;

public class UserDaoTest {
	private static Logger logger = LogManager.getLogger(UserDaoTest.class);
	private Connection connection;
	private UserDao userDao;
	
	@Before
	public void init() {
		connection = ConnectionPool.INSTANCE.getConnection();
		userDao = new UserDao();
		userDao.setConnection(connection);
	}
	
	@Test
	public void findAllShoulReturnEveryUser() {
		List<User> users = userDao.findAll();
		users.forEach(user -> logger.debug(user));
		assertTrue(users.size() == 1);
	}
	
	@After
	public void finish() throws SQLException {
		connection.close();
	}
	
}
