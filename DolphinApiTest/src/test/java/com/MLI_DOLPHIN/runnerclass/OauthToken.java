package com.MLI_DOLPHIN.runnerclass;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "src/test/java/com/MLI_DOLPHIN/featurefile",

		tags = "@OauthApI,@IFSC_MICR_API,@MYMONEY_API,@LE_IllustrationGenerator,@LE_AWPService,@EE_ServiceTest,@UW_MedicalReportService", glue = {
				"com.MLI_DOLPHIN.stepDefination" }, plugin = { "pretty",
						"html:target/cucumber-reports" }, dryRun = false, monochrome = true) 

public class OauthToken extends AbstractTestNGCucumberTests {
	@BeforeClass
	public static void SetUp() {
		Logger logger = Logger.getLogger("Dolphin logger");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
		// Setting the Base Url
		baseURI = ReusableFunction.readPropertiesFile().getProperty("BASE_URI");
	}

	@AfterClass
	public static void TearDown() {
		reset();
	}

}
