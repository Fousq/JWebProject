package kz.zhanbolat.web.application.service;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.entity.Category;
import kz.zhanbolat.web.infrastructer.database.dao.AbstractDao;
import kz.zhanbolat.web.infrastructer.database.dao.CategoryDao;
import kz.zhanbolat.web.infrastructer.database.pool.ConnectionPool;
import kz.zhanbolat.web.infrastructer.exception.DaoException;

public class CategoryService {
	private static Logger logger = LogManager.getLogger(CategoryService.class);
	private AbstractDao<Integer, Category> categoryDao;
	private Connection connection;
	
	public CategoryService() {
		categoryDao = new CategoryDao();
	}
	
	public List<Category> obtainAllCategories() {
		connection = ConnectionPool.INSTANCE.getConnection();
		categoryDao.setConnection(connection);
		try {
			return categoryDao.findAll();
		} catch (DaoException e) {
			logger.error("Error on finding all categories.", e);
			return null;
		}
	}
	
}
