package kz.zhanbolat.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.entity.User;

public class UserDao extends AbstractDao<Long, User> {
	private static Logger logger = LogManager.getLogger(UserDao.class);
	private static final String SELECT_ALL_USER = 
			"SELECT id, username, password FROM users";
	private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD =
			"SELECT id, username, password FROM users WHERE USERNAME = ?"
			+ " AND PASSWORD = ?";
	
	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		try {
			Statement state = connection.createStatement();
			ResultSet result = state.executeQuery(SELECT_ALL_USER);
			while(result.next()) {
				User user = new User();
				user.setId(result.getLong(1));
				user.setUsername(result.getString(2));
				user.setPassword(result.getString(3));
				users.add(user);
			}
		} catch (SQLException e) {
			logger.error("Error in excuting the query.", e);
		}
		return users;
	}

	@Override
	public boolean create(User entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(User entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User entity) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public User findUserByUsernameAndPassword(String username, String password) {
		User user = null;
		try {
			PreparedStatement preState 
			= connection.prepareStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD);
			preState.setString(1, username);
			preState.setString(2, password);
			ResultSet result = preState.executeQuery();
			result.next();
			user = new User(result.getLong(1),
							result.getString(2),
							result.getString(3));
		} catch (SQLException e) {
			logger.error("Error in excuting the query.", e);
		}
		return user;
	}
	
}
