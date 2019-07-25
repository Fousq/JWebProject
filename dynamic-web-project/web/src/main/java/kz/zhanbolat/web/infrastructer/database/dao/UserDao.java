package kz.zhanbolat.web.infrastructer.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.entity.User;

public class UserDao extends AbstractDao<Long, User> {
	private static Logger logger = LogManager.getLogger(UserDao.class);
	private static final String SELECT_ALL_USER = 
			"SELECT id, username, password FROM users";
	private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD =
			"SELECT id, username, password FROM users WHERE USERNAME = ?"
			+ " AND PASSWORD = ?";
	private static final String CREATE_NEW_USER = 
			"INSERT INTO users(username, password) VALUES (?, ?)";
	private static final String UPDATE_USER = 
			"UPDATE users SET telephone = ?, country = ?, birthday = ?"
			+ " WHERE username = ?;";
	
	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		try {
			Statement state = connection.createStatement();
			ResultSet result = state.executeQuery(SELECT_ALL_USER);
			while(result.next()) {
				User user = User.newUser()
							.setId(result.getLong(1))
							.setUsername(result.getString(2))
							.setPassword(result.getString(3))
							.build();
				users.add(user);
			}
		} catch (SQLException e) {
			logger.error("Error on finding the users.", e);
		}
		return users;
	}

	@Override
	public boolean create(User user) {
		int i = 0;
		try {
			PreparedStatement preState = connection.prepareStatement(CREATE_NEW_USER);
			preState.setString(1, user.getUsername());
			preState.setString(2, user.getPassword());
			i = preState.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error on creation new user.", e);
		}
		return i == 1;
	}

	@Override
	public User read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(User user) {
		try {
			PreparedStatement preState = connection.prepareStatement(UPDATE_USER);
			preState.setString(1, user.getTelephoneNumber());
			preState.setString(2, user.getCountry());
			preState.setDate(3, user.getBirthday());
			preState.setString(4, user.getUsername());
			preState.executeUpdate();
		} catch (SQLException e) {
			logger.error("ERROR on updating the user.", e);
			return false;
		}
		
		return true;
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
			user = (result.next()) ? User.newUser().setId(result.getLong(1))
									 .setUsername(result.getString(2))
									 .setPassword(result.getString(3))
									 .build()
									: null;
		} catch (SQLException e) {
			logger.error("Error on finding the user by username and password.",
						 e);
		}
		return user;
	}
}
