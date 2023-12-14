package voting.app.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncryption {

	private byte[] salt;

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public byte[] getSalt() throws NoSuchAlgorithmException {
		return getByteSalt();
	}

	public static String getHashedPassword(String enteredPassword,String salt) throws NoSuchAlgorithmException {		
		byte[] byteSalt = stringToByteArray(salt);
		return getSecurePassword(enteredPassword, byteSalt);
	}

	public static String getSecurePassword(String password, byte[] salt) {

		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt);
			byte[] bytes = md.digest(password.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	private static byte[] getByteSalt() throws NoSuchAlgorithmException {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}

	// Function to convert byte array to string
	public static String byteArrayToString(byte[] byteArray) {
		return Base64.getEncoder().encodeToString(byteArray);
	}

	// Function to convert string to byte array
	public static byte[] stringToByteArray(String encodedString) {
		return Base64.getDecoder().decode(encodedString);
	}

}

/*
 * https://www.javaguides.net/2020/02/java-sha-256-hash-with-salt-example.html
 * Certainly! This Java code is a part of a process that converts a byte array
 * into a hexadecimal string representation. Let's break it down step by step:
 * 
 * 1. `bytes[i] & 0xff`**: This part is performing a bitwise AND operation
 * between the `i`th element of the `bytes` array and the hexadecimal value
 * `0xff` (which is `255` in decimal). This operation ensures that the resulting
 * value is within the range of 0 to 255, effectively clearing any higher-order
 * bits and keeping only the lower 8 bits of the byte.
 * 
 * 2. `+ 0x100`**: This adds `256` to the value obtained in the previous step.
 * This step is necessary to ensure that the resulting string always has at
 * least two characters.
 * 
 * 3. `Integer.toString(..., 16)`**: This converts the obtained value into its
 * string representation in base 16 (hexadecimal).
 * 
 * 4. `.substring(1)`**: This takes a substring of the resulting hexadecimal
 * string, starting from index `1` and onwards. This is done to remove the extra
 * '1' that was added in step 2.
 * 
 * 5. `sb.append(...)`**: This appends the resulting substring to a
 * `StringBuilder` named `sb`. Presumably, `sb` is used to accumulate the
 * individual hexadecimal representations of the bytes.
 * 
 * In summary, this code processes each byte in the `bytes` array, converts it
 * into a two-character hexadecimal representation, and appends it to a
 * `StringBuilder`, effectively converting the entire byte array into a single
 * hexadecimal string.
 * 
 * 
 * // same salt should be passed byte[] salt = getSalt(); String password1 =
 * getSecurePassword("Password", salt); String password2 =
 * getSecurePassword("Password", salt); System.out.println(" Password 1 -> " +
 * password1); System.out.println(" Password 2 -> " + password2); if
 * (password1.equals(password2)) { System.out.println("passwords are equal"); }
 * 
 * 
 * 
 */
