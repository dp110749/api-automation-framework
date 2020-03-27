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

import com.MLI_DOLPHIN.utilities.ReusableFunction;

import static io.restassured.RestAssured.*;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	public static File file;
	public static FileInputStream fileinput;
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
		baseURI = ReusableFunction.readPropertiesFile().getProperty("BASE_URI");
	}

	@AfterClass
	public static void TearDown() {
		reset();
	}
	
	
	

}
