package com.MLI_DOLPHIN.stepDefination;

import static org.testng.Assert.assertEquals;

import java.util.List;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.json.simple.JSONObject;
import org.testng.Assert;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.specs.SpecificationFactory;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
public class LE_WLS_Service {
	private static final Logger logger = Logger.getLogger(LE_WLS_Service.class);
	private int secondRow;
	private String endPointurl;
	private String header;
	private String requestFile;
	private String requestBoday;
	private Response responseBody;
	private String statusCode;
	private String inputTestData;
	private String responseMessage;
	private String oparationtype;
	
	@Given("^Set the pre test for request$")
	public void set_the_pre_test_for_request(DataTable inputPreTestData) throws Throwable {
		List<String> listOfData =inputPreTestData.asList(String.class);
		List<List<String>>numOfRow =inputPreTestData.raw();
		secondRow=listOfData.size()/numOfRow.size();
		for(int i=secondRow;i<listOfData.size();i++){
			endPointurl=listOfData.get(i);
			i++;
			header=listOfData.get(i);
			i++;
			requestFile=listOfData.get(i);
		}
		requestBoday =ReusableFunction.readJsonFile(requestFile);
	}

	@When("^I want to send the request$")
	public void i_want_to_send_the_request() throws Throwable {
		responseBody= WebservicesMethod.POST_METHOD(endPointurl, requestBoday, ReusableFunction.requestHeaders(header));
	    logger.info("Response received from API:"+responseBody.body().prettyPrint());
	}

	@Then("^I want to validate the response code\"([^\"]*)\"$")
	public void i_want_to_validate_the_response_code(String responseCode) throws Throwable {
	  statusCode=String.valueOf(responseBody.getStatusCode());
	  Assert.assertEquals(statusCode, responseCode);
	  logger.info("Varification of response status code successfull.");
	}

	@Then("^I want to validate the response messageCode for WLS\"([^\"]*)\"$")
	public void i_want_to_validate_the_response_messageCode(String msgCode) throws Throwable {
	    responseBody.then().root("msgInfo").body("msgCode", Is.is(msgCode));
	    logger.info("Validation of response message code successfull.");
	}

	@Then("^I want to validate the response message for WLS\"([^\"]*)\"$")
	public void i_want_to_validate_the_response_message(String responseMessage) throws Throwable {
		if(responseMessage.contains("Success")){
	    responseBody.then().root("msgInfo").body("msg", Is.is(responseMessage));
	    logger.info("Validation of response message successfull.");
		}else if(responseMessage.contains("Bad Request")){
		responseBody.then().body("error", Is.is(responseMessage));
		logger.info("Validation of response message successfull.");
		}else{
			logger.info("no message found in the request...");
		}
	}

	@Then("^I want to validate the response data for WLS\"([^\"]*)\"$")
	public void i_want_to_validate_the_response_data(String outputTestData) throws Throwable {
	    responseBody.then().root("payload.premiumAmount").body("sumAssusred", Is.is(outputTestData));
	    logger.info("Validation of response message successfull."); 
	}

	@Then("^I want to validate the response appId and time$")
	public void i_want_to_validate_the_response_appId_and_time() throws Throwable {
	  SpecificationFactory.getGenericResponseSpec();
	  logger.info("Validation of response AppId and time successfull.");
	}
	@Given("^Set the test data for WLS\"([^\"]*)\"$")
	public void setInputTestdata(String inputData) {
       if(inputData.contains("x-api-key")){
    	   header=inputData;
    	   logger.info("Input data set successfully is :"+header);
       }else if(inputData.contains("developer")){
    	   endPointurl=inputData;
    	   logger.info("Input data set successfully is :"+endPointurl);
       }else{
    	   logger.info("No Input data set");
       }
	}
	
	@Then("^i want to validate the response data$")
	public void i_want_to_validate_the_response_data() throws Throwable {	
		responseBody.then().root("payload").body("illustrationPdfBase64", Matchers.notNullValue());
		logger.info("Validation of illustration out successfull..");
	}
	@Given("^Set the input request data for WLS$")
	public void set_the_input_request_data_for_WLS(DataTable inputRequestData) throws Throwable {
	
		List<String> listOfrequestData =inputRequestData.asList(String.class);
		List<List<String>> numOfRow =inputRequestData.raw();
		int getSecondRow=listOfrequestData.size()/numOfRow.size();
		for(int i=getSecondRow;i<listOfrequestData.size();i++){
			inputTestData =listOfrequestData.get(i);
			i++;
			oparationtype =listOfrequestData.get(i);
			i++;
			statusCode=listOfrequestData.get(i);
			i++;
			responseMessage=listOfrequestData.get(i);			
		}
		requestBoday =ReusableFunction.getSpecificRequest(requestBoday, inputTestData, oparationtype);
	}
	@Then("^I want to validate the response code and message$")
	public void i_want_to_validate_the_response_code_and_message() throws Throwable {		
		if(responseMessage.contains("Bad Request")){
		assertEquals(String.valueOf(responseBody.getStatusCode()),statusCode);
		logger.info("Validation of status code is successfull");
		responseBody.then().body("error", Matchers.equalTo(responseMessage));
		logger.info("Validation of response mesaage is successfull..");
		} else if(responseMessage.contains("Unknow Product")){
			assertEquals(String.valueOf(responseBody.getStatusCode()),statusCode);
			logger.info("Validation of status code is successfull");
			responseBody.then().body("errorMessage", Matchers.equalTo(responseMessage));
			logger.info("Validation of response mesaage is successfull..");

		}else{
			logger.info("Not message found in response..");
		}
	}
	@Given("^To Set the correlatinKey\"([^\"]*)\" and value\"([^\"]*)\" in the request for WLS$")
	public void SetCorrelationID(String inputKey, String inputValue) throws Throwable {
      JSONObject jsonObjectRequest =ReusableFunction.createJSONObject(requestBoday);
      jsonObjectRequest =ReusableFunction.replacekeyInJSONObject(jsonObjectRequest, inputKey, inputValue);
      requestBoday=jsonObjectRequest.toString();
	}

}
