package by.metelski.webtask.model.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

	public static boolean isValidName(String name) {
		boolean isValid = true;
		if (name.isBlank()) {
			isValid = false;
		}
		return isValid;
	}

	public static boolean isValidEmail(String email) {
		boolean isValid = true;
		if (!email.isBlank()) {
			String patternEmail = "\\w+@\\p{Alpha}+\\.\\p{Alpha}{2,6}";
			Pattern pattern = Pattern.compile(patternEmail);
			Matcher matcher = pattern.matcher(email);
			isValid = matcher.matches();
		} else {
			isValid = false;
		}

		return isValid;
	}
}
