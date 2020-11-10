package com.MLI_DOLPHIN.stepDefination;

import java.util.List;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.Assert;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.specs.SpecificationFactory;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class MyAgent {

	Logger logger = Logger.getLogger(MyAgent.class.getName());
	private int secondRow;
	private String endPoint;
	private String requestHeader;
	private String requestFile;
	private String requestBody;
	private Response responseBody;
	private String actualResponseCode;
	private String testData;
	private String actValue;
	private String msgDescription;
	private String msgCode;
	private String oparationType;

	@Given("^: Set the preRequest set of data$")
	public void set_the_preRequest_set_of_data(DataTable inputData) throws Throwable {
		List<String> listOfRecords = inputData.asList(String.class);
		List<List<String>> numOfRow = inputData.raw();
		secondRow = listOfRecords.size() / numOfRow.size();

		for (int i = secondRow; i < listOfRecords.size(); i++) {
			endPoint = listOfRecords.get(i);
			i++;
			requestHeader = listOfRecords.get(i);
			i++;
			requestFile = listOfRecords.get(i);
		}
		requestBody = ReusableFunction.readJsonFile(requestFile);

	}

	@When("^I want to send the request for MyAgent$")
	public void i_want_to_send_the_request_for_MyAgent() throws Throwable {
		responseBody = WebservicesMethod.POST_METHOD(endPoint, requestBody,
				ReusableFunction.requestHeaders(requestHeader));
		logger.info("Response Body:::----" + responseBody.prettyPrint());
	}

	@Then("^Lets validate response appId response time for MyAgent$")
	public void lets_validate_response_appId_response_time_for_MyAgent() throws Throwable {
		SpecificationFactory.getGenericResponseSpec();
		logger.info("Validation of response time and");
	}

	@Then("^I want to validate the response code for myAgent\"([^\"]*)\"$")
	public void i_want_to_validate_the_response_code_for_myAgent(String expResponseCode) throws Throwable {
		actualResponseCode = String.valueOf(responseBody.getStatusCode());
		if (actualResponseCode.equals(expResponseCode)) {
			Assert.assertEquals(actualResponseCode, expResponseCode);
			logger.info("Validated response code is ==" + actualResponseCode);
		} else {
			logger.info("Response Code is not matching with the expected Code : FAILED");
			Assert.assertEquals(actualResponseCode, expResponseCode);
		}
	}

	@Then("^I want to validate the message code for myAgent \"([^\"]*)\"$")
	public void i_want_to_validate_the_message_code_for_myAgent(String expMsgCode) throws Throwable {

		if (expMsgCode.equals("200")) {
			responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(expMsgCode));
			logger.info("For MyAgent API Successfully Response Msgcode is::" + expMsgCode);
		} else if (expMsgCode.equals("401") || expMsgCode.equals("500") || expMsgCode.equals("403")) {
			responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(expMsgCode));
			logger.info("For MyAgent API Successfully Response Msgcode is::" + expMsgCode);
		} else {
			logger.info("MyAgent APi not returning any response code");
		}
	}

	@Then("^I want to validate the message description for myAgent \"([^\"]*)\"$")
	public void i_want_to_validate_the_message_description_for_myAgent(String expMsgDesc) throws Throwable {
		if (expMsgDesc.equals("Success")) {
			responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(expMsgDesc));
			logger.info("For MyAgent API Successfully Response Msgcode is::" + expMsgDesc);
		} else if (expMsgDesc.equals("Failure")) {
			responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(expMsgDesc));
			logger.info("For MyAgent API Successfully Response Msgcode is::" + expMsgDesc);
		} else {
			logger.info("MyAgent APi not returning any response code");
		}

	}

	@Given("^Set the Test data for MyAgent Service$")
	public void set_the_Test_data_for_MyAgent_Service(DataTable inputtestData) throws Throwable {

		List<String> listOfrecord = inputtestData.asList(String.class);
		List<List<String>> numberOfrow = inputtestData.raw();
		int secondOfRow = listOfrecord.size() / numberOfrow.size();
		for (int i = secondOfRow; i < listOfrecord.size(); i++) {
			testData = listOfrecord.get(i);
			i++;
			oparationType = listOfrecord.get(i);
			i++;
			actValue = listOfrecord.get(i);
			i++;
			msgDescription = listOfrecord.get(i);
			i++;
			msgCode = listOfrecord.get(i);

			String testRequest=requestBody;
			testRequest = ReusableFunction.getSpecificRequest(testRequest, testData, oparationType);
			responseBody = WebservicesMethod.POST_METHOD(endPoint, testRequest,
					ReusableFunction.requestHeaders(requestHeader));
			logger.info("Response Body is ====" + responseBody.prettyPrint());
			if (msgCode.equals("200") && msgDescription.contains("Agent Valid")) {
				responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(msgCode)).and()
						.body("msgDescription", Matchers.equalTo(msgDescription));

				responseBody.then().root("payload").body("agentName", Matchers.equalTo(actValue));
                logger.info("Validation of response data is::"+actValue);
				
			}else if(msgCode.equals("200")&&msgDescription.contains("Agent Not Valid")){
				
				responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(msgCode)).and()
				.body("msgDescription", Matchers.equalTo(msgDescription));

		       responseBody.then().root("payload").body("agentName", Matchers.equalTo(null));
               logger.info("Validation of response data is"+msgDescription);

			}else if(msgCode.equals("500") ||msgCode.equals("400")){
				
				responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(msgCode)).and()
				.body("msg", Matchers.equalTo(msgDescription));
				logger.info("Sended request is invalid..");
//
//			}else if (msgDescription.contains("Bad Request")){
//				responseBody.then().body("error", Matchers.equalTo(msgDescription));
			}else{
				logger.info("API not responding !!!!!");
			}
				
		}

	}
	@Given("^I want to set test data\"([^\"]*)\"$")
	public void i_want_to_set_test_data(String pretestData) throws Throwable {
		if(pretestData.contains("x-api-key:")){
		requestHeader =pretestData;
		logger.info("Test data set successfully !!"+requestHeader);
		
		}else if(pretestData.contains("/developer")){
		  endPoint=pretestData;
		  logger.info("Test data set successfully !!"+endPoint);
		}else{
			logger.info("No preRequist test data set !!");
		}
		
	}


}
