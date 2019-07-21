package kz.zhanbolat.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {
	private static Logger logger = LogManager.getLogger(UserServiceTest.class);
	private UserService service;
	
	@Before
	public void init() {
		service = new UserService();
	}
	
	@Test
	public void UserShouldBeExisted() {
		boolean isExisted = service.isExisted("login", "password");
		assertTrue(isExisted);
	}
	
	@Test
	public void UserShouldNotBeExisted() {
		boolean isExisted = service.isExisted("log", "password");
		assertFalse(isExisted);
	}
	
}
