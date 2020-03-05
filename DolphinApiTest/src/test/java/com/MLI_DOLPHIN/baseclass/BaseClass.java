package com.MLI_DOLPHIN.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.*;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	public static File file;
	public static FileInputStream fileinput;
	public static Properties properties;
	public static Logger logger;
	public static Response response;
	public static RequestSpecification httpRequest;

	/* This Function is used to set Log for every step: */
	@BeforeClass
	public static void SetUp() {
		logger = Logger.getLogger("Dolphin logger");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);

		/* Setting the Base Url */
		baseURI = readPropertiesFile().getProperty("BASE_URI");
	}

	@AfterClass
	public static void TearDown() {
		reset();
	}
	
	public static void main(String[] args) {
		System.out.println(readPropertiesFile().getProperty("allurelog"));
		
	}

	public static Properties readPropertiesFile() {
		file = new File(System.getProperty("user.dir") + "./src/test/resources/propertiesFile.properties");
		try {
			fileinput = new FileInputStream(file);
			properties = new Properties();
			try {
				properties.load(fileinput);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return properties;
	}

}
