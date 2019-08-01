package kz.zhanbolat.web.infrastructer.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.entity.Entity;
import kz.zhanbolat.web.infrastructer.exception.DaoException;

public interface AbstractDao<K, T extends Entity> {
	void setConnection(Connection connection);
	List<T> findAll() throws DaoException;
	boolean create(T entity) throws DaoException;
	T read(K id) throws DaoException;
	boolean update(T entity) throws DaoException;
	boolean delete(T entity) throws DaoException;
	
	/*
	 * FUNCTIONS BELOW MUST BE USED ONLY IN SUBCLASSES OF THIS INTERFACE.
	 */
	default void closeConnection(Connection connection, Logger logger) {
		try {
			if (connection != null && connection.getAutoCommit()) {
				connection.close();
			}
		} catch (SQLException e) {
			logger.error("ERROR on closing the connection.", e);
		}
	}
	
	default void closeStatement(Statement statement, Logger logger) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			logger.error("ERROR on closing the statement.", e);
		}
	}
	
	default void closeResultSet(ResultSet resultSet, Logger logger) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			logger.error("ERROR on closing the result set.", e);
		}
	}
	
}
