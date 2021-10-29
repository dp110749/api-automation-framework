package com.MLI_DOLPHIN.utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.random.RandomDataGenerator;

import com.github.javafaker.Faker;

public class RendomDataGenerator {

	public static Faker fakerapi;

	public static String userName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return ("jhon" + generatedString);
	}

	/* This is function is used to generate 16 number */
	public static String getPinCode() {
		String generatedString = RandomStringUtils.randomNumeric(6);
		return (generatedString);
	}

	/* This is function is used to generate 9 number */
	public static String get9DigitNumber() {
		String generatedString = RandomStringUtils.randomNumeric(9);
		return (generatedString);
	}

	public static String get2DigitNumber() {
		String generatedString = RandomStringUtils.randomNumeric(2);
		return (generatedString);
	}

	public static String getFirstname() {

		fakerapi = new Faker();
		return fakerapi.name().firstName();
	}

	public static String getMiddlename() {

		fakerapi = new Faker();
		return fakerapi.name().nameWithMiddle();
	}

	public static String getLatsname() {
		fakerapi = new Faker();
		return fakerapi.name().lastName();
	}

	public static String getMobNumber() {
		fakerapi = new Faker();
		return fakerapi.phoneNumber().cellPhone();
	}

	public static String getAddress() {
		fakerapi = new Faker();
		return fakerapi.address().city();

	}

}
