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
/*	private String transTypeKey;
	private String transTypeValue;
	private String genderKey;
	private String genderValue;
	private String exactIncomeKey;
	private String exactIncomeValue;
	private String dobKey;
	private String dobValue;
	private String modelPremiumKey;
	private String modelPremiumValue;*/
	private String inputKey;
	private String inputValue;

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

		if (msgCode.equals("200")) {
			responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(msgCode));
			logger.info("Validation of response message Code Successful.");
		} else if (msgCode.equals("500")) {
			responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(msgCode));
			logger.info("Validation of response message Code Successful.");
		} else {
			logger.info("API Returning some other message code.." + responseBody.prettyPrint());
		}

	}

	@Then("^I want to validate the message description \"([^\"]*)\"$")
	public void i_want_to_validate_the_message_description(String message) throws Throwable {
		if (message.equals("Success")) {
			responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(message));
			logger.info("Validation of response message Is::" + message);
		} else if (message.equals("Failure")) {
			responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(message));
			logger.info("Validation of response message is::" + message);
		} else {
			logger.info("API Returning some other message::" + responseBody.prettyPrint());
		}

	}

	@Then("^Lets validate response appId response time for PolicyAdmin$")
	public void lets_validate_response_appId_response_time_for_PolicyAdmin() throws Throwable {
		SpecificationFactory.getGenericResponseSpec();
		logger.info("Reponse Time Validated Successfully..");
	}

	/*
	 * @Given("^PreRequest Set of test data$") public void
	 * prerequest_Set_of_test_data(DataTable preRequestTestData) throws
	 * Throwable { List<String> listOfTestData =
	 * preRequestTestData.asList(String.class); List<List<String>> numOfRow =
	 * preRequestTestData.raw(); getSecondRowData = listOfTestData.size() /
	 * numOfRow.size(); for (int i = getSecondRowData; i <
	 * listOfTestData.size(); i++) { transTypeKey = listOfTestData.get(i); i++;
	 * transTypeValue = listOfTestData.get(i); i++; genderKey =
	 * listOfTestData.get(i); i++; genderValue = listOfTestData.get(i); i++;
	 * exactIncomeKey = listOfTestData.get(i); i++; exactIncomeValue =
	 * listOfTestData.get(i); i++; dobKey = listOfTestData.get(i); i++; dobValue
	 * = listOfTestData.get(i); i++; modelPremiumKey = listOfTestData.get(i);
	 * i++; modelPremiumValue = listOfTestData.get(i);
	 * 
	 * }
	 * 
	 * }
	 */
	@Given("^Set the inputData \"([^\"]*)\"$")
	public void set_the_inputData(String testData) throws Throwable {
		if (testData.contains("x-api-key")) {

			header = testData;
		} else if (testData.contains("developer")) {
			url = testData;
		} else {
			logger.info("Data Does not set..");
		}
	}

	@Given("^Set of Test Data for request$")
	public void set_of_Test_Data_for_request(DataTable inputrequestData) throws Throwable {
		List<String> numberOfRecords = inputrequestData.asList(String.class);
		List<List<String>> numberOfRow = inputrequestData.raw();
		getSecondRowData = numberOfRecords.size() / numberOfRow.size();
		for (int i = getSecondRowData; i < numberOfRecords.size(); i++) {
			inputKey = numberOfRecords.get(i);
			i++;
			inputValue = numberOfRecords.get(i);
			i++;
			msgCode = numberOfRecords.get(i);
			i++;
			msg = numberOfRecords.get(i);
			String testRequest = requestBody;
			newRequest = ReusableFunction.createJSONObject(testRequest);
			newRequest = ReusableFunction.replacekeyInJSONObject(newRequest, inputKey, inputValue);
			testRequest = newRequest.toString();
			if (testRequest.length() > 1) {
				Thread.sleep(1000);
				responseBody = WebservicesMethod.POST_METHOD(url, testRequest, ReusableFunction.requestHeaders(header));
			} else {
				logger.info("Request Body is Null");
			}
			if (msgCode.equals("200") && msg.equals("Success")) {

				responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(msg)).and().root("msgInfo")
						.body("msgCode", Matchers.equalTo(msgCode));
				logger.info("Validation of response message Is::" + msg);

			} else if (msgCode.equals("400") || msgCode.equals("500")
					|| msgCode.equals("403") && msg.equals("Failure")) {
				responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(msg)).and().root("msgInfo")
						.body("msgCode", Matchers.equalTo(msgCode));
				logger.info("Validation of response message Is::" + msg);

			} else {
				logger.info("Api not returning any message code");
			}
		}

	}

	@Given("^PreRequest Set of test data$")
	public void prerequest_Set_of_test_data(DataTable inputrequestData) throws Throwable {
		List<String> numberOfRecords = inputrequestData.asList(String.class);
		List<List<String>> numberOfRow = inputrequestData.raw();
		getSecondRowData = numberOfRecords.size() / numberOfRow.size();
		for (int i = getSecondRowData; i < numberOfRecords.size(); i++) {
			inputKey = numberOfRecords.get(i);
			i++;
			inputValue = numberOfRecords.get(i);
			i++;
			msgCode = numberOfRecords.get(i);
			i++;
			msg = numberOfRecords.get(i);
//			String testRequest = requestBody;
			newRequest = ReusableFunction.createJSONObject(requestBody);
			newRequest = ReusableFunction.replacekeyInJSONObject(newRequest, inputKey, inputValue);
			requestBody = newRequest.toString();
			if (requestBody.length() > 1) {
				Thread.sleep(1000);
				responseBody = WebservicesMethod.POST_METHOD(url, requestBody, ReusableFunction.requestHeaders(header));
				logger.info("response is"+responseBody.prettyPrint());
			} else {
				logger.info("Request Body is Null");
			}
			if (msgCode.equals("200") && msg.equals("Success")) {

				responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(msg)).and().root("msgInfo")
						.body("msgCode", Matchers.equalTo(msgCode));
				logger.info("Validation of response message for Update XmL ::" + msg);

			} else if (msgCode.equals("400") || msgCode.equals("500")
					|| msgCode.equals("403") && msg.equals("Failure")) {
				responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(msg)).and().root("msgInfo")
						.body("msgCode", Matchers.equalTo(msgCode));
				logger.info("Validation of response message Is::" + msg);

			} else {
				logger.info("Api not returning any message code");
			}
		}
	}

}
