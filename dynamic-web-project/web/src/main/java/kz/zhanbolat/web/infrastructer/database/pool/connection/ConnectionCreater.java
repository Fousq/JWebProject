package kz.zhanbolat.web.infrastructer.database.pool.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.infrastructer.config.DBConfiguration;
import kz.zhanbolat.web.infrastructer.exception.ConfigurationException;

public enum ConnectionCreater {
	INSTANCE;
	
	private Logger logger = LogManager.getLogger(ConnectionCreater.class);
	
	public Connection createNewMySQLConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = DBConfiguration.INSTANCE.getUrl();
			Properties info = DBConfiguration.INSTANCE.getInfo();
			connection = DriverManager.getConnection(url, info);
		} catch (ClassNotFoundException | SQLException | ConfigurationException e) {
			logger.error("Error in creating new MySQL connection.", e);
		}
		return connection;
	}
	
}
