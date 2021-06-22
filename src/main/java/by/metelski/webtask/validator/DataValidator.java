package by.metelski.webtask.validator;

/**
 * Class data validator
 * Check date, time format and numbers value
 * Allowed date format: yyyy-mm-dd
 * Allowed time format: hh-mm-ss
 * @author Yauhen Metelski
 *
 */
public class DataValidator {
	private static final String DATE_REGEX = "\\d\\d\\d\\d-\\d\\d-\\d\\d";
	private final static String TIME_REGEX ="\\d\\d:\\d\\d:\\d\\d";
	private final static String NUMBERS_REGEX ="\\d{1,10}";
	
	/**
	 * @param text
	 * @return boolean result of checking
	 */
	public static boolean isDateFormatValid(String text) {
		if(text==null||text.isBlank()) {
		return false;
		}
		return text.matches(DATE_REGEX);
	}
	
	/**
	 * @param text
	 * @return boolean result of checking
	 */
	public static boolean isTimeFormatValid(String text) {
		if(text==null||text.isBlank()) {
		return false;
		}
		return text.matches(TIME_REGEX);
	}
	/**
	 * @param text
	 * @return boolean result of checking
	 */
	public static boolean isOnlyNumbers(String text) {
		if(text==null||text.isBlank()) {
		return false;
		}
		return text.matches(NUMBERS_REGEX);
	}
}
