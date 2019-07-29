package kz.zhanbolat.web.application.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.entity.User;
import kz.zhanbolat.web.infrastructer.database.dao.AbstractDao;
import kz.zhanbolat.web.infrastructer.database.dao.UserDao;
import kz.zhanbolat.web.infrastructer.database.pool.ConnectionPool;

public class UserService {
	private static Logger logger = LogManager.getLogger(UserService.class);
	private Connection connection;
	private AbstractDao<Long, User> userDao;
	
	public UserService() {
		userDao = new UserDao();
	}
	
	public boolean isExisted(String username, String password) {
		connection = ConnectionPool.INSTANCE.getConnection();
		userDao.setConnection(connection);
		boolean isExisted = false;
		User user = ((UserDao) userDao).findUserByUsernameAndPassword(username, password);
		if (user != null) {
			isExisted = true;
		}
		return isExisted;
	}
	
	public boolean registerNewUser(String username, String password) {
		if (isExisted(username, password)) {
			logger.info("User with such login or password existed.");
			return false;
		}
		User user = User.newUser()
						.setUsername(username)
						.setPassword(password)
						.build();
		connection = ConnectionPool.INSTANCE.getConnection();
		userDao.setConnection(connection);
		userDao.create(user);
		return true;
	}
	
	public boolean editUser() {
		return false;
	}
	
}
