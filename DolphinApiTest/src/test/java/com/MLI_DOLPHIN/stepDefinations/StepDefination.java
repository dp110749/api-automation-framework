package com.MLI_DOLPHIN.stepDefinations;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.MLI_DOLPHIN.baseclass.BaseClass;
import com.MLI_DOLPHIN.dolphinTest.TC_OauthApi_TokenGenerator;
import com.MLI_DOLPHIN.excelreader.ExcelFileReader;
import com.MLI_DOLPHIN.requestClass.RequestFactory;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefination extends BaseClass{

	RequestFactory requestFactory;
	public Response responseBody;
	public RequestSpecification spec;
	

	TC_OauthApi_TokenGenerator TC_OT = new TC_OauthApi_TokenGenerator();

	@DataProvider(name = "TestData")
	public String[][] getTestData() {
		//Pass the Sheet name to get the data 
		String[][] testRecords = ExcelFileReader.getExcelData("LE_FTSP");
		return testRecords;
	}
	
	@Given("^I Set The Request Body and Headers$")
	public void i_Set_The_Request_Body_and_Headers(String tcName, String runmode, String url, String requestBody, String headers,
			String inputData, String statusCode, String jsonPayload, String responseValue, String actualMeaageCode,
			String responseMessage,String pathofMesgcode,String expErrorMessage) throws Throwable {
		System.out.println("==============Test case Started========================");
		//TC_OT.getTestData();
		TC_OT.tc001_(tcName, runmode, url, requestBody, headers, inputData, statusCode, jsonPayload, responseValue, actualMeaageCode, responseMessage, pathofMesgcode, expErrorMessage);
	   
	   
	}

	@When("^Send the Request$")
	public void send_the_Request() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   
	}

	@Then("^validate the response code$")
	public void validate_the_response_code() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Then("^validate the repose data$")
	public void validate_the_repose_data() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	



}
