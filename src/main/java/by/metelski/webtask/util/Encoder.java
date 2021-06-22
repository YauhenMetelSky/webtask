package by.metelski.webtask.util;

import java.math.BigInteger;
import java.util.Base64;

/**
 * Class Encoder.
 * 
 * Encode password
 * @author Yauhen Metelski
 *
 */
public final class Encoder {
	private Encoder() {
	}

	/**
	 * @param password
	 * @return String hash value of password
	 */
	public static String encodePassword(String password) {
		Base64.Encoder encoder = Base64.getEncoder();
		byte[] bytesEncoded = encoder.encode(password.getBytes());
		BigInteger bigInt = new BigInteger(1, bytesEncoded);
		String resultHex = bigInt.toString(16);
		return resultHex;
	}
}
