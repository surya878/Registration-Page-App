package com.nt.surya;

import java.security.SecureRandom;
import java.util.Random;

public class Random_PWD_Tet01 {
	// Use SecureRandom to generate random numbers for password characters
	private static final Random RANDOM = new SecureRandom();
	// Define the character set for the password
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public static void main(String[] args) {

		// Define desired password length
		int passwordLength = 10;

		// Generate Secure Password
		String password = generatePassword(passwordLength);

		// Print out password value
		System.out.println(password);
	}

	// Method to generate a secure password of specified length
		public static String generatePassword(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		// Iterate through password length and append random characters from character
		// set
		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		// Convert StringBuilder to String and return
		return new String(returnValue);

	}
}
