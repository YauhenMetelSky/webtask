package by.metelski.webtask.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UserValidator {
	public static final Logger logger = LogManager.getLogger();
	private final static String EMAIL_REGEX = "\\w+@\\p{Alpha}+\\.\\p{Alpha}{2,}";
    private final static String NAME_REGEX ="[\\p{Alpha}А-Яа-я\\s-]{1,15}";
    private final static String PASSWORD_REGEX ="[a-zA-Z\\d]{1,15}";
	 
	public static boolean isValidName(String name) {
		logger.log(Level.DEBUG, "name: " + name);
		if(name==null||name.isBlank()) {
			return false;
		}
		return name.matches(NAME_REGEX);
	}

	public static boolean isValidEmail(String email) {
		logger.log(Level.DEBUG, "email: " + email);
		boolean isValid = true;
		if (!email.isBlank()) {
			Pattern pattern = Pattern.compile(EMAIL_REGEX);
			Matcher matcher = pattern.matcher(email);
			isValid = matcher.matches();
		} else {
			isValid = false;
		}
		return isValid;
	}
	public static boolean isValidPassword(String password) {
		logger.log(Level.DEBUG, "password: " + password);//TODO don't show password
		if(password==null||password.isBlank()) {
			return false;
		}
		return password.matches(PASSWORD_REGEX);
	}
}
