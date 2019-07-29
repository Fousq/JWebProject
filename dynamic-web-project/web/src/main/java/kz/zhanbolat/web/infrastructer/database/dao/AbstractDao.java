package kz.zhanbolat.web.infrastructer.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.entity.Entity;

public interface AbstractDao<K, T extends Entity> {
	void setConnection(Connection connection);
	List<T> findAll();
	boolean create(T entity);
	T read(K id);
	boolean update(T entity);
	boolean delete(T entity);
	
	/*
	 * FUNCTIONS BELOW MUST BE USED ONLY IN SUBCLASSES OF THIS INTERFACE.
	 */
	default void closeConnection(Connection connection, Logger logger) {
		try {
			connection.close();
		} catch (SQLException e) {
			logger.error("ERROR on closing the connection.", e);
		}
	}
	
	default void closeStatement(Statement statement, Logger logger) {
		try {
			statement.close();
		} catch (SQLException e) {
			logger.error("ERROR on closing the statement.", e);
		}
	}
	
	default void closeResultSet(ResultSet resultSet, Logger logger) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			logger.error("ERROR on closing the result set.", e);
		}
	}
	
}
