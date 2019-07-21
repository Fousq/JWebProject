package kz.zhanbolat.web.infrastructer.database.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.infrastructer.database.connection.MySQLConnection;

public enum ConnectionPool {
	INSTANCE();
	
	private Logger logger = LogManager.getLogger(ConnectionPool.class);
	private BlockingQueue<Connection> freeConnections;
	private BlockingQueue<Connection> usedConnections;
	
	private final int DEFAULT_POOL_SIZE = 24;
	
	private ConnectionPool() {
		MySQLConnection connection =
				new MySQLConnection("jdbc:mysql://localhost:3306/testschema",
									"root",
									"123456fifa");
		freeConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
		usedConnections = new LinkedBlockingQueue<>();
		for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
			ProxyConnection proxyConnection = 
					new ProxyConnection(connection.newConnection());
			freeConnections.add(proxyConnection);
		}
	}
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			if (!freeConnections.isEmpty()) {
				connection = freeConnections.take();
				usedConnections.add(connection);
			}
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
		freeConnections.add(connection);
	}
	
	public void destroyPool() {
		for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
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
