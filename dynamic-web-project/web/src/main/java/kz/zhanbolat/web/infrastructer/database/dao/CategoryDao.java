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

import kz.zhanbolat.web.domain.entity.Category;
import kz.zhanbolat.web.infrastructer.exception.DaoException;

public class CategoryDao implements AbstractDao<Integer, Category> {
	private static Logger logger = LogManager.getLogger(CategoryDao.class);
	private static final String SELECT_ALL_CATEGORIES = 
			"SELECT id, name FROM categories;";
	private Connection connection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	
	public CategoryDao() {
		super();
	}
	
	public CategoryDao(Connection connection) {
		this.connection = connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Category> findAll() throws DaoException {
		List<Category> categories;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_ALL_CATEGORIES);
			categories = new ArrayList<>();
			while(resultSet.next()) {
				Category category = new Category(resultSet.getInt(1), 
						resultSet.getString(2));
				categories.add(category);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.getCause());
		} finally {
			closeResultSet(resultSet, logger);
			closeStatement(statement, logger);
			closeConnection(connection, logger);
		}
		
		return categories;
	}

	@Override
	public boolean create(Category entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Category read(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Category entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Category entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

}
