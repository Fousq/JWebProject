package kz.zhanbolat.web;

import static org.junit.Assert.assertTrue;

import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import kz.zhanbolat.web.infrastructer.config.DBConfiguration;

public class DBConfigTest {
	private static Logger logger = LogManager.getLogger(DBConfigTest.class);
	
	@Test
	public void propertiesShouldBeTheSameAsInTheFile() {
		Properties prop = DBConfiguration.INSTANCE.getPropeties();
		Set<Object> keys = prop.keySet();
		keys.forEach(key -> logger.debug(key));
		assertTrue(keys.size() == 6);
		assertTrue(prop.getProperty("user") != null);
		assertTrue(prop.getProperty("password") != null);
		assertTrue(prop.getProperty("serverTimezone") != null);
		assertTrue(prop.getProperty("charset") != null);
		assertTrue(prop.getProperty("useLegacyDatetimeCode") != null);
		assertTrue(prop.getProperty("useJDBCCompliantTimezoneShift") != null);
	}
	
}
