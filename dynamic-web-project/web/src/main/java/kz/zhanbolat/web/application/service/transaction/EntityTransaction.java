package kz.zhanbolat.web.application.service.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.infrastructer.database.dao.AbstractDao;
import kz.zhanbolat.web.infrastructer.database.pool.ConnectionPool;

public class EntityTransaction {
	private static Logger logger = LogManager.getLogger(EntityTransaction.class);
	private Connection connection;
	
	public void begin(AbstractDao dao, AbstractDao... daos) {
		if (connection == null) {
			connection = ConnectionPool.INSTANCE.getConnection();
		}
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			logger.error("ERROR ON CONNECTION.", e);
		}
		dao.setConnection(connection);
		Stream.of(daos).forEach(daoElement -> daoElement.setConnection(connection));
	}
	
	public void end() {
		if (connection != null) {
			try {
				if (!connection.getAutoCommit()) {
					connection.setAutoCommit(true);
				}
				connection.close();
			} catch (SQLException e) {
				logger.error("ERROR ON CLOSING THE CONNECTION.", e);
			}
		} else {
			logger.warn("GOT NO CONNECTION TO CLOSE");
		}
	}
	
	public void rollback() {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				logger.error("ERROR ON ROLLBACK OF CONNECTION.", e);
			}
		} else {
			logger.warn("GOT NO CONNECTION TO CLOSE");
		}
	}
	
	public void commit() {
		if (connection != null) {
			try {
				connection.commit();
			} catch (SQLException e) {
				logger.error("ERROR ON COMMITING THE CONNECTION.", e);
			}
		} else {
			logger.warn("GOT NO CONNECTION TO CLOSE");
		}
	}
	
}
