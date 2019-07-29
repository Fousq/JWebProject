package kz.zhanbolat.web.infrastructer.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.entity.User;

public class UserDao implements AbstractDao<Long, User> {
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
	private Connection connection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	
	public UserDao() {
		super();
	}
	
	public UserDao(Connection connection) {
		super();
		this.connection = connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_ALL_USER);
			while(resultSet.next()) {
				User user = User.newUser()
							.setId(resultSet.getLong(1))
							.setUsername(resultSet.getString(2))
							.setPassword(resultSet.getString(3))
							.build();
				users.add(user);
			}
		} catch (SQLException e) {
			logger.error("Error on finding the users.", e);
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(statement, logger);
			closeConnection(connection, logger);
		}
		return users;
	}

	@Override
	public boolean create(User user) {
		int i = 0;
		try {
			preStatement = connection.prepareStatement(CREATE_NEW_USER);
			preStatement.setString(1, user.getUsername());
			preStatement.setString(2, user.getPassword());
			i = preStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error on creation new user.", e);
		} finally {
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
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
			preStatement = connection.prepareStatement(UPDATE_USER);
			preStatement.setString(1, user.getTelephoneNumber());
			preStatement.setString(2, user.getCountry());
			preStatement.setDate(3, user.getBirthday());
			preStatement.setString(4, user.getUsername());
			preStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error("ERROR on updating the user.", e);
			return false;
		} finally {
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
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
			preStatement 
			= connection.prepareStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD);
			preStatement.setString(1, username);
			preStatement.setString(2, password);
			resultSet = preStatement.executeQuery();
			user = (resultSet.next()) ? User.newUser().setId(resultSet.getLong(1))
									 .setUsername(resultSet.getString(2))
									 .setPassword(resultSet.getString(3))
									 .build()
									: null;
		} catch (SQLException e) {
			logger.error("Error on finding the user by username and password.",
						 e);
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		return user;
	}
}
