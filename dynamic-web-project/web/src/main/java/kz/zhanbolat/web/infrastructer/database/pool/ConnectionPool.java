package kz.zhanbolat.web.infrastructer.database.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.infrastructer.config.DBConfiguration;
import kz.zhanbolat.web.infrastructer.database.pool.connection.ConnectionCreater;
import kz.zhanbolat.web.infrastructer.exception.ConfigurationException;
import kz.zhanbolat.web.infrastructer.exception.ConnectionPoolException;

public enum ConnectionPool {
	INSTANCE();
	
	private Logger logger = LogManager.getLogger(ConnectionPool.class);
	private BlockingQueue<Connection> freeConnections;
	private BlockingQueue<Connection> usedConnections;
	private int poolSize;
	
	private ConnectionPool() {
		try {
			poolSize = DBConfiguration.INSTANCE.getConnectionPoolSize();
			freeConnections = new LinkedBlockingQueue<>(poolSize);
			usedConnections = new LinkedBlockingQueue<>();
			for (int i = 0; i < poolSize; i++) {
				ProxyConnection proxyConnection = 
						new ProxyConnection(ConnectionCreater.INSTANCE.createNewMySQLConnection());
				freeConnections.add(proxyConnection);
			}
			if (freeConnections.size() != poolSize) {
				if (freeConnections.size() == 0) {
					throw new ExceptionInInitializerError("Pool is not created.");
				} else {
					logger.warn("Only " + freeConnections.size() + " was created.");
				}
			}
		} catch (ConfigurationException e) {
			logger.fatal("Pool size is not setted.", e);
			throw new ExceptionInInitializerError("Pool is not created.");
		}
	}
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = freeConnections.take();
			usedConnections.offer(connection);
		} catch (InterruptedException e) {
			logger.error("Error on getting the free connection.", e);
		}
		logger.debug(connection);
		return connection;
	}
	
	public void releaseConnection(Connection connection) {
		if (!(connection instanceof ProxyConnection)) {
			logger.warn("Connection is not from the pool, cannot be realeased.");
		}
		usedConnections.remove(connection);
		freeConnections.offer(connection);
	}
	
	public void destroyPool() {
		for (int i = 0; i < poolSize; i++) {
			try {
				((ProxyConnection) freeConnections.take()).closeConnection();
			} catch (SQLException | InterruptedException e) {
				logger.error("Error in destroying the pool.", e);
			}
		}
	}
	
	public void deregisterDrivers() {
		DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
			try {
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
				logger.error("Error on deregister the drivers of pool.", e);
			}
		});
	}
}
