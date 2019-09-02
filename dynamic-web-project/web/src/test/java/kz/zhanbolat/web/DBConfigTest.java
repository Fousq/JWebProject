package kz.zhanbolat.web;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kz.zhanbolat.web.infrastructer.config.DBConfiguration;
import kz.zhanbolat.web.infrastructer.exception.ConfigurationException;

public class DBConfigTest {
	private static Logger logger = LogManager.getLogger(DBConfigTest.class);
	private Properties prop;
	
	@Before
	public void begin() {
		prop = DBConfiguration.INSTANCE.getPropeties();
	}
	
	@Test
	public void propertiesShouldBeTheSameAsInTheFile() {
		prop = DBConfiguration.INSTANCE.getPropeties();
		Set<Object> keys = prop.keySet();
		keys.forEach(key -> logger.debug(key));
		assertTrue(prop.getProperty("url") != null);
		assertTrue(prop.getProperty("user") != null);
		assertTrue(prop.getProperty("password") != null);
		assertTrue(prop.getProperty("serverTimezone") != null);
		assertTrue(prop.getProperty("charset") != null);
		assertTrue(prop.getProperty("useLegacyDatetimeCode") != null);
		assertTrue(prop.getProperty("useJDBCCompliantTimezoneShift") != null);
	}
	
	@Test
	public void urlShouldBeGot() throws ConfigurationException {
		String url = DBConfiguration.INSTANCE.getUrl();
		logger.debug(url);
		assertTrue(url != null);
	}
	
	@Test
	public void userShouldBeGot() throws ConfigurationException {
		String user = DBConfiguration.INSTANCE.getUser();
		logger.debug(user);
		assertTrue(user != null);
	}
	
	@Test
	public void passwordShouldBeGot() throws ConfigurationException {
		String password = DBConfiguration.INSTANCE.getPassword();
		logger.debug(password);
		assertTrue(password != null);
	}
	
	@Test
	public void infoShouldBeGot() {
		prop = DBConfiguration.INSTANCE.getInfo();
		logger.debug(prop.isEmpty());
		assertFalse(prop.isEmpty());
		assertTrue(prop.getProperty("url") == null);
	}
	
	@After
	public void end() {
		prop.clear();
	}
	
}
