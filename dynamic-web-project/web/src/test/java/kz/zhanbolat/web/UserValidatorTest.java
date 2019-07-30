package kz.zhanbolat.web;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import kz.zhanbolat.web.domain.validator.UserValidator;

public class UserValidatorTest {
	private static Logger logger = LogManager.getLogger(UserValidatorTest.class);
	
	@Test
	public void validePasswordShouldReturnTrue() {
		String password = "seeg412QWFS";
		boolean valid = UserValidator.validatePassword(password);
		assertTrue(valid);
	}
	
	@Test
	public void validePasswordShouldReturnFalse() {
		String[] passwords = new String[] {
				"4135235414", "ferehrthgae", "FSEEVNONG",
				"1231fgsegf", "fgrgrgGSGSG", "3124GGGHG",
				"1wE"
		};
		Stream.of(passwords).forEach(password -> 
		assertFalse(UserValidator.validatePassword(password)));
	}
	
	@Test
	public void validateTelephoneNumberShouldReturnTrue() {
		String telephoneNumber = "123-123-12-12";
		assertTrue(UserValidator.validateTelephoneNumber(telephoneNumber));
	}
	
	@Test
	public void validateTelephoneNumberShouldReturnFalse() {
		String[] telephoneNumbers = new String[] {
				"702", "123-", "13", "123-1", "123-12", "1", "123-123",
				"123-123-1", "123-123-", "123-123-12", "123-123-123",
				"123-123-12-", "123-123-12-1", "123-123-12-123"
		};
		Stream.of(telephoneNumbers).forEach(telephoneNumber -> 
		assertFalse(UserValidator.validateTelephoneNumber(telephoneNumber)));
	}
	
}
