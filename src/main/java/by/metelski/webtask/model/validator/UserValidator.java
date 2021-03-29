package by.metelski.webtask.model.validator;

public class UserValidator {
	
	public static boolean isValidName(String name) {
		boolean isValid = true;
		if(name.isBlank()) {
			isValid = false;
		}
		return isValid;
	}
}
