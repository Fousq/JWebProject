package kz.zhanbolat.web;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import kz.zhanbolat.web.application.service.UserService;
import kz.zhanbolat.web.domain.entity.User;
import kz.zhanbolat.web.domain.exception.InvalidValueException;

public class UserServiceTest {
	private static Logger logger = LogManager.getLogger(UserServiceTest.class);
	private UserService service;
	
	@Before
	public void init() {
		service = new UserService();
	}
	
	@Test
	@Ignore
	public void UserShouldBeExisted() {
		boolean isExisted = service.isExisted("login", "password");
		assertTrue(isExisted);
	}
	
	@Test
	@Ignore
	public void newUserShouldBeRegistered() throws InvalidValueException {
		User user = User.newUser().setUsername("test")
				.setPassword("test")
				.build();
		boolean registered = service.registerNewUser(user);
		assertFalse(registered);
	}
	
	@Test
	public void UserShouldNotBeExisted() {
		boolean isExisted = service.isExisted("log", "password");
		assertFalse(isExisted);
	}
}
