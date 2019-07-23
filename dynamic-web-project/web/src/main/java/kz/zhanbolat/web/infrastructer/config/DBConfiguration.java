package kz.zhanbolat.web.infrastructer.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum DBConfiguration {
	INSTANCE("database.properties");
	
	private Logger logger = LogManager.getLogger(DBConfiguration.class); 
	private Properties prop;
	
	DBConfiguration(String file) {
		prop = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream(file);
		try {
			prop.load(input);
		} catch (IOException e) {
			logger.error("Error on loading the data from " + file, e);
		}
	}
	
	public Properties getPropeties() {
		return prop;
	}
	
}
