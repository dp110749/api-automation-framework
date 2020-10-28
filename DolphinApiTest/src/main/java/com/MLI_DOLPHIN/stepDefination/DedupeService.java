package com.MLI_DOLPHIN.stepDefination;

import java.util.List;

import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.Every;
import org.json.simple.JSONObject;
import org.testng.Assert;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.specs.SpecificationFactory;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import com.google.gson.JsonObject;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class DedupeService {

	private static final Logger logger = Logger.getLogger(DedupeService.class);
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

	@Given("^Set the request test data$")
	public void set_the_request_test_data(DataTable preSetOfData) throws Throwable {

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

	@When("^i want to send the request for Dedupe Servcie$")
	public void i_want_to_send_the_request_for_Dedupe_Servcie() throws Throwable {

		if (requestBody.length() > 0) {
			responseBody = WebservicesMethod.POST_METHOD(url, requestBody, ReusableFunction.requestHeaders(header));
			logger.info("Response body is :: " + responseBody.prettyPrint());
		} else {
			logger.info("Invalid Request..");
		}
	}

	@Then("^Lets validate response code  of DedupeService \"([^\"]*)\"$")
	public void lets_validate_response_code(String expResponseCode) throws Throwable {

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

	@Then("^Lets validate response appId response time  of DedupeService$")
	public void lets_validate_response_appId_response_time() throws Throwable {
		SpecificationFactory.getGenericResponseSpec();
		logger.info("Validation of response appId and response time successfully passed");
	}

	@Then("^Lets validate the response message  of DedupeService \"([^\"]*)\"$")
	public void lets_validate_the_response_message_of_DedupeService(String expResponseMessage) throws Throwable {

		if (expResponseMessage.equalsIgnoreCase("Success")) {

			responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(expResponseMessage));
			// responseBody.then().root("payload").body("dedupe",
			// Matchers.hasItem("dedupeFlag"));
		} else if (expResponseMessage.equalsIgnoreCase("Failure")) {
			responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(expResponseMessage));
			// logger.info("Api Getting Fail :: " + responseBody.prettyPrint());
		} else {
			logger.info("Api Getting Fail and not returning any response..");
		}
	}

	@Given("^i want to set the test data$")
	public void set_testdata_(DataTable inputTestData) throws Throwable {
		List<String> listOfTestData = inputTestData.asList(String.class);
		List<List<String>> numOfRow = inputTestData.raw();
		getSecondRowData = listOfTestData.size() / numOfRow.size();
		for (int i = getSecondRowData; i < listOfTestData.size(); i++) {
			testData = listOfTestData.get(i);
			i++;
			operationType = listOfTestData.get(i);
			i++;
			testUrl = listOfTestData.get(i);
			i++;
			msgCode = listOfTestData.get(i);
			i++;
			msg = listOfTestData.get(i);
			requestBody = ReusableFunction.getSpecificRequest(requestBody, testData, operationType);
			if (requestBody.length() > 0) {
				responseBody = WebservicesMethod.POST_METHOD(testUrl, requestBody,
						ReusableFunction.requestHeaders(header));
			} else {
				logger.info("sent request is invaild ");

			}

			if (msgCode.equals("400")) {
				responseBody.then().root("msgInfo").body("msgCode", Matchers.equalToIgnoringCase(msgCode)).and()
						.body("msg", Matchers.equalTo(msg));
				logger.info("verify the error msg due to sending invaild input data: RESULT AS PER EXPECTATION");

			} else if (msgCode.equalsIgnoreCase("200")) {
				responseBody.then().root("msgInfo").body("msgCode", Matchers.equalToIgnoringCase(msgCode)).and()
						.body("msg", Matchers.equalTo(msg));

				logger.info("Response generated Successfully.");

			} else if (msgCode.equals("500")) {
				responseBody.then().root("msgInfo").body("msgCode", Matchers.equalToIgnoringCase(msgCode)).and()
						.body("msg", Matchers.equalTo(msg));
			} else if (msgCode.equals("")) {
				responseBody.then().body("error", Matchers.equalToIgnoringCase(msg));
				logger.info("Verify bad request ");

			} else {
				logger.info("Unexpected response received from api.");
			}
			logger.info("Response Body : " + responseBody.prettyPrint());
		}
	}

	@Given("^I want to Set the test data in request \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_want_to_Set_the_test_data_in_request_and(String correlationIDkey, String correlationValue)
			throws Throwable {

		newRequest = ReusableFunction.createJSONObject(requestBody);
		newRequest = ReusableFunction.replacekeyInJSONObject(newRequest, correlationIDkey, correlationValue);
		requestBody = newRequest.toString();
	}
}
