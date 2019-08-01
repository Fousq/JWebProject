package kz.zhanbolat.web.infrastructer.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.entity.User;
import kz.zhanbolat.web.domain.exception.InvalidValueException;
import kz.zhanbolat.web.infrastructer.exception.DaoException;

public class UserDao implements AbstractDao<Long, User> {
	private static Logger logger = LogManager.getLogger(UserDao.class);
	private static final String SELECT_ALL_USER = 
			"SELECT id, username, password FROM users";
	private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD =
			"SELECT id, username, password FROM users WHERE username = ?"
			+ " AND password = ?";
	private static final String CREATE_NEW_USER = 
			"INSERT INTO users(username, password, telephone, country, birthday)"
			+ " VALUES (?, ?, ?, ?, ?);";
	private static final String UPDATE_USER_BY_ID = 
			"UPDATE users SET telephone = ?, country = ?, birthday = ?"
			+ " WHERE id = ?;";
	private static final String SELECT_USER_BY_USERNAME = 
			"SELECT id, password, telephone, country, birthday FROM users WHERE"
			+ " username = ?;";
	private static final String SELECT_USER_BY_ID = 
			"SELECT username, password, telephone, country, birthday "
			+ "FROM users WHERE id = ?;";
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
	public List<User> findAll() throws DaoException {
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
		} catch (SQLException | InvalidValueException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(statement, logger);
			closeConnection(connection, logger);
		}
		return users;
	}
	
	/*
	 * (non-Javadoc)
	 * @see kz.zhanbolat.web.infrastructer.database.dao.AbstractDao#create(kz.zhanbolat.web.domain.entity.Entity)
	 * 	In inserting the birthday to database, 
	 * 	the birthday's value store with 1 day subtracted.
	 */
	@Override
	public boolean create(User user) throws DaoException {
		try {
			preStatement = connection.prepareStatement(CREATE_NEW_USER);
			preStatement.setString(1, user.getUsername());
			preStatement.setString(2, user.getPassword());
			preStatement.setString(3, user.getTelephoneNumber());
			preStatement.setString(4, user.getCountry());
			preStatement.setObject(5, user.getBirthday().plusDays(1));
			preStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		return true;
	}

	@Override
	public User read(Long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(User user) throws DaoException {
		try {
			preStatement = connection.prepareStatement(UPDATE_USER_BY_ID);
			preStatement.setString(1, user.getTelephoneNumber());
			preStatement.setString(2, user.getCountry());
			preStatement.setObject(3, user.getBirthday());
			preStatement.setLong(4, user.getId());;
			preStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
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
	
	public User findUserByUsernameAndPassword(String username, String password) throws DaoException {
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
		} catch (SQLException | InvalidValueException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(preStatement, logger);
			closeConnection(connection, logger);
		}
		return user;
	}
	
	public User findUserByUsername(String username) throws DaoException {
		User user;
		try {
			preStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME);
			preStatement.setString(1, username);
			resultSet = preStatement.executeQuery();
			if (resultSet.next()) {
				user = User.newUser().setId(resultSet.getLong(1))
						.setPassword(resultSet.getString(2))
						.setTelephoneNumber(resultSet.getString(3))
						.setCountry(resultSet.getString(4))
						.setBirthday(resultSet.getObject(5, LocalDate.class))
						.build();
			} else {
				user = null;
			}
		} catch (SQLException | InvalidValueException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(statement, logger);
			closeConnection(connection, logger);
		}
		
		return user;
	}
	
	public User findUserById(long id) throws DaoException {
		User user;
		try {
			preStatement = connection.prepareStatement(SELECT_USER_BY_ID);
			preStatement.setLong(1, id);
			resultSet = preStatement.executeQuery();
			if (resultSet.next()) {
				user = User.newUser().setUsername(resultSet.getString(1))
						.setPassword(resultSet.getString(2))
						.setTelephoneNumber(resultSet.getString(3))
						.setCountry(resultSet.getString(4))
						.setBirthday(resultSet.getObject(5, LocalDate.class))
						.build();
			} else {
				user = null;
			}
		} catch (SQLException | InvalidValueException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(statement, logger);
			closeConnection(connection, logger);
		}
		
		return user;
	}
	
}
