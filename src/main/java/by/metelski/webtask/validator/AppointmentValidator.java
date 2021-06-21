package by.metelski.webtask.validator;

public class AppointmentValidator {
	private static final String PRICE_REGEX = "\\d{1,8}(\\.\\d{2})";
	private final static String NAME_REGEX ="([\\p{Alpha}А-Яа-я]{1,15}[\\s-]?){0,9}";
	private final static String IMAGE_NAME_REGEX ="[\\p{Alpha}А-Яа-я-_]{1,15}\\.jpg";
	private final static String NUMBERS_REGEX ="\\d{1,10}";
	
	public static boolean isOnlyNumbers(String text) {
		if(text==null||text.isBlank()) {
		return false;
		}
		return text.matches(NUMBERS_REGEX);
	}
	public static boolean isValidName(String text) {
		if(text==null||text.isBlank()) {
			return false;
			}
			return text.matches(NAME_REGEX);
	}
	public static boolean isValidImageName(String text) {
		if(text==null||text.isBlank()) {
			return false;
			}
			return text.matches(IMAGE_NAME_REGEX);
	}
	public static boolean isValidPrice(String text) {
		if(text==null||text.isBlank()) {
			return false;
			}
			return text.matches(PRICE_REGEX);
	}
}
