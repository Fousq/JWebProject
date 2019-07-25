package kz.zhanbolat.web.infrastructer.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.entity.Category;

public class CategoryDao extends AbstractDao<Integer, Category> {
	private static Logger logger = LogManager.getLogger(CategoryDao.class);
	private static final String SELECT_ALL_CATEGORIES = 
			"SELECT id, name FROM categories;";
	
	@Override
	public List<Category> findAll() {
		List<Category> categories;
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(SELECT_ALL_CATEGORIES);
			categories = new ArrayList<>();
			while(result.next()) {
				Category category = new Category(result.getInt(1), 
												 result.getString(2));
				categories.add(category);
			}
		} catch (SQLException e) {
			logger.error("ERROR on selection all categories.", e);
			categories = null;
		}
		
		return categories;
	}

	@Override
	public boolean create(Category entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Category read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Category entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Category entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
