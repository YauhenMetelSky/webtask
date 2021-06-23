package by.metelski.webtask.validator;

/**
 * Class procedure data validator
 * @author Yauhen Metelski
 *
 */
public class ProcedureValidator {
	private static final String PRICE_REGEX = "\\d{1,8}(\\.\\d{2,8})";
	private final static String NAME_REGEX ="([\\p{Alpha}А-Яа-я]{1,15}[\\s-]?){0,9}";
	private final static String IMAGE_NAME_REGEX ="[\\p{Alpha}А-Яа-я-_]{1,15}\\.jpg";
	private final static String NUMBERS_REGEX ="\\d{1,10}";
	private final static String SPECIAL_CHAR_REGEX ="[.[^<>]]{1,1000}";
	
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
	/**
	 * @param text
	 * @return boolean result of checking
	 */
	public static boolean isValidName(String text) {
		if(text==null||text.isBlank()) {
			return false;
			}
			return text.matches(NAME_REGEX);
	}
	/**
	 * @param text
	 * @return boolean result of checking
	 */
	public static boolean isValidImageName(String text) {
		if(text==null||text.isBlank()) {
			return false;
			}
			return text.matches(IMAGE_NAME_REGEX);
	}
	/**
	 * @param text
	 * @return boolean result of checking
	 */
	public static boolean isValidPrice(String text) {
		if(text==null||text.isBlank()) {
			return false;
			}
			return text.matches(PRICE_REGEX);
	}
	/**
	 * @param text
	 * @return boolean result of checking
	 */
	public static boolean isValidDescription(String text) {
		if(text==null||text.isBlank()) {
			return false;
			}
			return text.matches(SPECIAL_CHAR_REGEX);
	}
}
