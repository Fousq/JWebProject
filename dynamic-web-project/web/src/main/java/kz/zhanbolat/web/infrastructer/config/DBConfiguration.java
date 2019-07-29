package kz.zhanbolat.web.infrastructer.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.infrastructer.exception.ConfigurationException;

public enum DBConfiguration {
	INSTANCE("database.properties");
	
	private Logger logger = LogManager.getLogger(DBConfiguration.class); 
	private Properties prop;
	private final String URL_PARAM_NAME = "url"; 
	private final String USER_PARAM_NAME = "user";
	private final String PASSWORD_PARAM_NAME = "password";
	private final String CONNECTION_POOL_SIZE_PARAM_NAME = "connection.pool.size";
	
	DBConfiguration(String file) {
		prop = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream(file);
		try {
			prop.load(input);
		} catch (IOException e) {
			logger.error("Error on loading the data from " + file, e);
		}
	}
	/*
	 * GET ALL PROPERTIES
	 */
	public Properties getPropeties() {
		return (Properties) prop.clone();
	}
	
	/*
	 * GET URL OF DATABASE
	 */
	public String getUrl() throws ConfigurationException {
		String url = prop.getProperty(URL_PARAM_NAME);
		if (url == null) {
			throw new ConfigurationException("property 'url' cannot be found.");
		}
		return url;
	}
	
	public String getUser() throws ConfigurationException {
		String user = prop.getProperty(USER_PARAM_NAME);
		if (user == null) {
			throw new ConfigurationException("property 'user' cannot be found.");
		}
		return user;
	}
	
	public String getPassword() throws ConfigurationException {
		String password = prop.getProperty(PASSWORD_PARAM_NAME);
		if (password == null) {
			throw new ConfigurationException("property 'password' cannot be found.");
		}
		return password;
	}
	
	public int getConnectionPoolSize() throws ConfigurationException {
		String value = prop.getProperty(CONNECTION_POOL_SIZE_PARAM_NAME);
		if (value == null) {
			throw new ConfigurationException("property 'password' cannot be found.");
		}
		int size = 0;
		try {
			size = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new ConfigurationException("Value of property 'connection.pool.size' is not an integer.", e);
		}
		if (size < 1) {
			throw new ConfigurationException("Size of the connection pool must be above 0.");
		}
		return size;
	}
	
	/*
	 * GET ALL INFORMATION FOR CREATING THE CONNECTION, 
	 * DON NOT RETURN THE URL OF DATABASE
	 */
	public Properties getInfo() {
		Properties info = new Properties();
		Enumeration<Object> elements = prop.keys();
		while(elements.hasMoreElements()) {
			String key = (String) elements.nextElement();
			if (!key.equals(URL_PARAM_NAME)) {
				info.put(key, prop.get(key));
			}
		}
		return info;
	}
	
}
