package com.nt.surya;

import org.apache.commons.lang3.RandomStringUtils;

public class Random_PWD_Test03 {

	public static void main(String[] args) {
		
		 int length = 10;
		    boolean useLetters = true;
		    boolean useNumbers = false;
		    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

		    System.out.println(generatedString);

	}

}
