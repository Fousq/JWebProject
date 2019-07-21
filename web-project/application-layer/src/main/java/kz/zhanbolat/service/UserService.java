package kz.zhanbolat.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.database.dao.AbstractDao;
import kz.zhanbolat.database.dao.UserDao;
import kz.zhanbolat.database.pool.ConnectionPool;
import kz.zhanbolat.entity.User;

public class UserService {
	private static Logger logger = LogManager.getLogger(UserService.class);
	private Connection connection;
	@SuppressWarnings("rawtypes")
	private AbstractDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}
	
	public boolean isExisted(String username, String password) {
		connection = ConnectionPool.INSTANCE.getConnection();
		userDao.setConnection(connection);
		boolean isExisted = false;
		try {
			User user = ((UserDao) userDao).findUserByUsernameAndPassword(username, password);
			if (user != null) {
				isExisted = true;
			}
			return isExisted;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("Error in closing the connection.", e);
			}
		}
	}
	
}
