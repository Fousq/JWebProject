package kz.zhanbolat.web.application.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.entity.User;
import kz.zhanbolat.web.infrastructer.database.dao.AbstractDao;
import kz.zhanbolat.web.infrastructer.database.dao.UserDao;
import kz.zhanbolat.web.infrastructer.database.pool.ConnectionPool;
import kz.zhanbolat.web.infrastructer.exception.DaoException;

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
		User user;
		try {
			user = ((UserDao) userDao).findUserByUsernameAndPassword(username, password);
		} catch (DaoException e) {
			logger.error("Error on finding the user.", e);
			user = null;
		}
		if (user != null) {
			isExisted = true;
		}
		return isExisted;
	}
	
	public boolean registerNewUser(User user) {
		if (isExisted(user.getUsername(), user.getPassword())) {
			logger.info("User with such login or password existed.");
			return false;
		}
		connection = ConnectionPool.INSTANCE.getConnection();
		userDao.setConnection(connection);
		try {
			userDao.create(user);
		} catch (DaoException e) {
			logger.error("Error on creatin the new user.", e);
			return false;
		}
		return true;
	}
	
	public User obtainUserByUsername(String username) {
		User user = null;
		connection = ConnectionPool.INSTANCE.getConnection();
		userDao.setConnection(connection);
		try {
			user = ((UserDao) userDao).findUserByUsername(username);
		} catch (DaoException e) {
			logger.error("Error in obtaining the user.", e);
		}
		return user;
	}
	
	public boolean editUser(User user) {
		connection = ConnectionPool.INSTANCE.getConnection();
		userDao.setConnection(connection);
		boolean isEdited = false;
		try {
			isEdited = userDao.update(user);
		} catch (DaoException e) {
			logger.error("ERROR on updating the user information.", e);
		}
		return isEdited;
	}
	
}
