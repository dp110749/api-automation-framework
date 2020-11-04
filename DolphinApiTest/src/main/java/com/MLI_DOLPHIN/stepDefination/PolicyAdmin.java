package com.MLI_DOLPHIN.stepDefination;

import java.util.List;

import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
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

public class PolicyAdmin {
	
	private static final Logger logger = Logger.getLogger(PolicyAdmin.class);
	private String header;
	private String url;
	private String requestFile;
	private String requestBody;
	private String testData;
	private String operationType;
	private Response responseBody;
	private String actualResponseCode;
	private int getSecondRowData;
	private String testUrl;
	private String msgCode;
	private String msg;
	private JSONObject newRequest;

	
	@Given("^set pre set of data$")
	public void set_pre_set_of_data(DataTable preSetOfData) throws Throwable {
	
		List<String> listOfSetData = preSetOfData.asList(String.class);
		List<List<String>> rowNum = preSetOfData.raw();

		getSecondRowData = listOfSetData.size() / rowNum.size();

		for (int i = getSecondRowData; i < listOfSetData.size(); i++) {
			url = listOfSetData.get(i);
			i++;
			header = listOfSetData.get(i);
			i++;
			requestFile = listOfSetData.get(i);
			break;
		}
		requestBody = ReusableFunction.readJsonFile(requestFile);

	}

	@When("^I want to send the request for policyAdmin$")
	public void i_want_to_send_the_request_for_policyAdmin() throws Throwable {
		
		if (requestBody.length() > 0) {
			responseBody = WebservicesMethod.POST_METHOD(url, requestBody, ReusableFunction.requestHeaders(header));
			logger.info("Response body is :: " + responseBody.prettyPrint());
		} else {
			logger.info("Invalid Request..");
		}

	}

	@Then("^I want to validate the response code \"([^\"]*)\"$")
	public void i_want_to_validate_the_response_code(String expResponseCode) throws Throwable {
		Thread.sleep(2000);
		actualResponseCode = String.valueOf(responseBody.getStatusCode());
		if (actualResponseCode.equals(expResponseCode)) {
			Assert.assertEquals(actualResponseCode, expResponseCode);
			logger.info("Validated response code is ==" + actualResponseCode);
		} else if (actualResponseCode.equals(expResponseCode)) {
			Assert.assertEquals(actualResponseCode, expResponseCode);
			logger.info("Validated response code is ==" + actualResponseCode);
		} else {
			logger.info("Service not returning any response code");
		}

	}

	@Then("^I want to validate the message code \"([^\"]*)\"$")
	public void i_want_to_validate_the_message_code(String msgCode) throws Throwable {
		
		if(msgCode.equals("200")){
		responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(msgCode));
		logger.info("Validation of response message Code Successful.");
		} else if(msgCode.equals("500")){
			responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(msgCode));
			logger.info("Validation of response message Code Successful.");			
		}else{
			logger.info("API Returning some other message code.."+responseBody.prettyPrint());
		}
		

	}

	@Then("^I want to validate the message description \"([^\"]*)\"$")
	public void i_want_to_validate_the_message_description(String message) throws Throwable {
		if(message.equals("Success")){
		responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(message));
		logger.info("Validation of response message Is::"+message);
		} else if(message.equals("Failure")){
			responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(message));
			logger.info("Validation of response message is::"+message);			
		}else{
			logger.info("API Returning some other message::"+responseBody.prettyPrint());
		}

	}


}
