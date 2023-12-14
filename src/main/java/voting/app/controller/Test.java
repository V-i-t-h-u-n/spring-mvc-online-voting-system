package voting.app.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import voting.app.utilities.EmailSender;
import voting.app.utilities.PasswordEncryption;

public class Test {

	public static void main(String[] args) {
		
		EmailSender emailSender = new EmailSender();
		emailSender.sendEmail("vcvithun24@gmail.com","123");

		byte[] byteArray = null;
		try {
			byteArray = new PasswordEncryption().getSalt();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

//		System.out.println(Arrays.toString(byteArray));
		// Example byte array

		// Convert byte array to string
		String encodedString = byteArrayToString(byteArray);
		System.out.println("Encoded String: " + encodedString);

		// Convert string back to byte array
		byte[] decodedByteArray = stringToByteArray(encodedString);
		System.out.print("Decoded Byte Array: ");
		for (byte b : decodedByteArray) {
			System.out.print(b + " ");
		}

		String hashedPassword = PasswordEncryption.getSecurePassword("1234", decodedByteArray);
		System.out.println("\n" + hashedPassword);
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
 * 
 * This code uses Java's Base64 class to encode the byte array to a string
 * (byteArrayToString) and decode the string back to a byte array
 * (stringToByteArray). The example demonstrates this conversion with a simple
 * byte array containing ASCII values.
 * 
 * Keep in mind that Base64 encoding increases the length of the data by
 * approximately 33% due to its encoding mechanism. If you're dealing with
 * binary data and need a string representation without length expansion, you
 * might consider hexadecimal encoding instead.
 * 
 * 
 */
