package kz.zhanbolat.web.domain.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
	private static Pattern pattern;
	private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,16}$";
	private static final String TELEPHONE_NUMBER_REGEX = "^[0-9]{3,4}\\-[0-9]{3,3}(\\-\\d{2,2}){2,2}$";
	
	public static boolean validatePassword(String password) {
		pattern = Pattern.compile(PASSWORD_REGEX);
		Matcher matchedPassword = pattern.matcher(password);
		return matchedPassword.find();
	}
	
	public static boolean validateTelephoneNumber(String telephoneNumber) {
		pattern = Pattern.compile(TELEPHONE_NUMBER_REGEX);
		Matcher matchedTelephoneNumber = pattern.matcher(telephoneNumber);
		return matchedTelephoneNumber.find();
	}
	
}
