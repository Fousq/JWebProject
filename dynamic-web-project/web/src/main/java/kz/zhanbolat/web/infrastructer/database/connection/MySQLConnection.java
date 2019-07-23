package kz.zhanbolat.web.infrastructer.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.infrastructer.config.DBConfiguration;

public class MySQLConnection {
	
	private static Logger logger = LogManager.getLogger(MySQLConnection.class);
	private String url;
	private Properties prop;
	
	public MySQLConnection(String url) {
		this.url = url;
		prop = DBConfiguration.INSTANCE.getPropeties();
	}
	
	public Connection newConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, prop);
		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Error in creating new connection.", e);
		}
		return connection;
	}
	
}
