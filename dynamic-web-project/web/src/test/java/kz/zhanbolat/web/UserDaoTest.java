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
import org.junit.Ignore;

import kz.zhanbolat.web.domain.entity.User;
import kz.zhanbolat.web.domain.exception.InvalidValueException;
import kz.zhanbolat.web.infrastructer.database.dao.UserDao;
import kz.zhanbolat.web.infrastructer.database.pool.ConnectionPool;
import kz.zhanbolat.web.infrastructer.exception.DaoException;

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
	@Ignore
	public void findAllShoulReturnEveryUser() throws DaoException {
		List<User> users = userDao.findAll();
		users.forEach(user -> logger.debug(user));
		assertTrue(users.size() != 0);
	}
	
	@Test
	@Ignore
	public void createNewUser() throws InvalidValueException, DaoException {
		User user = User.newUser()
						.setUsername("test")
						.setPassword("test")
						.build();
		boolean created = userDao.create(user);
		assertTrue(created);
	}
	
}
