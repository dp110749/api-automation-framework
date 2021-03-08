package com.MLI_DOLPHIN.stepDefination;
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
		
public class LE_SPS_Service {
	private static final Logger logger = Logger.getLogger(LE_SPS_Service.class);
	private int secondRowData;
	private String endPointUrl;
	private String header;
	private String requestFile;
	private String requestBody;
	private Response responseBody;
	private String actualResponseCode;
	private String msgCode;
	private String responseMsg;
	private String inputTestData;
	private String oparationType;

	@Given("^Set the request test data for SPS$")
	public void set_the_request_test_data_for_SPS(DataTable inputData) throws Throwable {
		List<String> listOfData = inputData.asList(String.class);
		List<List<String>> numberOfRow = inputData.raw();
		secondRowData = listOfData.size() / numberOfRow.size();
		for (int i = secondRowData; i < listOfData.size(); i++) {
			endPointUrl = listOfData.get(i);
			i++;
			header = listOfData.get(i);
			i++;
			requestFile = listOfData.get(i);
		}
		requestBody = ReusableFunction.readJsonFile(requestFile);

	}

	@When("^I want send the request$")
	public void i_want_send_the_request() throws Throwable {
		responseBody = WebservicesMethod.POST_METHOD(endPointUrl, requestBody, ReusableFunction.requestHeaders(header));
		logger.info("Response Body is :: "+responseBody.prettyPrint());
	}

	@SuppressWarnings("deprecation")
	@Then("^I want to validate the response data\"([^\"]*)\"$")
	public void i_want_to_validate_the_response_data(String responseData) throws Throwable {
		responseBody.then().root("payload.premiumAmount")
		.body("sumAssured", Is.is(responseData));
	}

	@Then("^I want to validate the response Msgcode\"([^\"]*)\"$")
	public void i_want_to_validate_the_response_Msgcode(String expResponseCode) throws Throwable {
		Thread.sleep(2000);
		actualResponseCode = String.valueOf(responseBody.getStatusCode());
		Assert.assertEquals(actualResponseCode, expResponseCode);
		logger.info("Validated response code is ==" + actualResponseCode);
	}

	@SuppressWarnings("deprecation")
	@Then("^I want to validate the response message\"([^\"]*)\"$")
	public void i_want_to_validate_the_response_message(String expMsgCode) throws Throwable {
		if (expMsgCode.equals("400")) {
			responseBody.then().root("msgInfo").body("msgCode", Matchers.equalToIgnoringCase(expMsgCode));
			logger.info("verify the error msg due to sending invaild input data: RESULT AS PER EXPECTATION");

		} else if (expMsgCode.equalsIgnoreCase("200")) {
			responseBody.then().root("msgInfo").body("msgCode", Matchers.equalToIgnoringCase(expMsgCode));
			logger.info("Response generated Successfully.");

		} else if (expMsgCode.equals("500")) {
			responseBody.then().root("msgInfo").body("msgCode", Matchers.equalToIgnoringCase(expMsgCode));
		} else if (expMsgCode.equals("")) {
			
			logger.info("Verify bad request ");

		} else {
			logger.info("Unexpected response received from api.");
		}
		logger.info("Response Body : " + responseBody.prettyPrint());
	}

	@Then("^I want to validate the response AppId and time$")
	public void i_want_to_validate_the_response_AppId_and_time() throws Throwable {
		SpecificationFactory.getGenericResponseSpec();
		logger.info("Validation of response appId and response time successfully passed");       
	}
	
	@Given("^I want to set the test data$")
	public void i_want_to_set_the_test_data(DataTable inputData) throws Throwable {
		List<String> listOfInputData =inputData.asList(String.class);
		List<List<String>>numberOfRaw =inputData.raw();
		secondRowData=listOfInputData.size()/numberOfRaw.size();
		for(int i=secondRowData;i<listOfInputData.size();i++){
			
			msgCode=listOfInputData.get(i);
			i++;
			responseMsg=listOfInputData.get(i);
			i++;
			inputTestData=listOfInputData.get(i);
			i++;
			oparationType =listOfInputData.get(i);			
			requestBody=ReusableFunction.getSpecificRequest(requestBody, inputTestData, oparationType);						
		}
		
	}
	@Given("^I want to set the test Data\"([^\"]*)\"$")
	public void i_want_to_set_the_test_Data(String inuptTestData) throws Throwable {
		if(inuptTestData.contains("developer")){
		endPointUrl=inuptTestData;
		}else{
			header=inuptTestData;
		}
		logger.info("Set the test data successfully..");
	}

	@Then("^I want to validate the response Msgcode$")
	public void i_want_to_validate_the_response_Msgcode() throws Throwable {
		logger.info("Response Body is :: "+responseBody.prettyPrint());	
	  responseBody.then().root("msgInfo").body("msgCode", Is.is(msgCode));
	  logger.info("validation of response code successfully pass..");
	}

	@Then("^I want to validate the response message$")
	public void i_want_to_validate_the_response_message() throws Throwable {
		  responseBody.then().root("msgInfo").body("msg", Is.is(responseMsg));
		  logger.info("validation of response message is successful..");	
	}
	@Then("^I want to validate the error message$")
	public void i_want_to_validate_the_error_message() throws Throwable {
		responseBody.then().body("errorMessage", Is.is(responseMsg));
		logger.info("Error message validate successfully..");
	}
	@Given("^I want to set the correlatinKey\"([^\"]*)\" and value\"([^\"]*)\" in the request$")
	public void i_want_to_set_the_correlatinKey_and_value_in_the_request(String inputKey, String inputValue) throws Throwable {
      JSONObject jsonObjectRequest =ReusableFunction.createJSONObject(requestBody);
      jsonObjectRequest =ReusableFunction.replacekeyInJSONObject(jsonObjectRequest, inputKey, inputValue);
      requestBody=jsonObjectRequest.toString();
	}
	@Then("^I want to validate the response error message\"([^\"]*)\"$")
	public void i_want_to_validate_the_response_error_message(String errorMessage) throws Throwable {
		responseBody.then().body("error", Is.is(errorMessage));
		logger.info("Error message validate successfully..");
	}
	
}
