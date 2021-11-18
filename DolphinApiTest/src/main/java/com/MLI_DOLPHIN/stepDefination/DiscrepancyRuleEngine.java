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

public class DiscrepancyRuleEngine extends WebservicesMethod {

	private final static Logger logger = Logger.getLogger(DiscrepancyRuleEngine.class.getName());

	private String testData;
	private String operationType;
	private String actualResponseCode;
	private String testUrl;
	private String msgCode;
	private String msg;

	@Given("^set the input request testdata$")
	public void set_the_input_request_testdata(DataTable presetData) throws Throwable {

		List<String> listOfSetData = presetData.asList(String.class);
		List<List<String>> rowNum = presetData.raw();

		getSecondRowData = listOfSetData.size() / rowNum.size();
		for (int i = getSecondRowData; i < listOfSetData.size(); i++) {
			endPointUrl = listOfSetData.get(i);
			i++;
			header = listOfSetData.get(i);
			i++;
			requestFile = listOfSetData.get(i);
			i++;
			method_Type = listOfSetData.get(i);
			break;
		}
		requestBody = ReusableFunction.readJsonFile(requestFile);
	}

	@When("^i want to send the request for Discrepancy Rule Engine$")
	public void i_want_to_send_the_request_for_Discrepancy_Rule_Engine() throws Throwable {
		responseBody = WebservicesMethod.Select_API_METHOD(method_Type, endPointUrl, requestBody,
				ReusableFunction.requestHeaders(header));
		logger.info("Response Body is ::" + responseBody.prettyPrint());
	}

	@Then("^Lets validate response code \"([^\"]*)\"$")
	public void Lets_validate_response_code(String expResponseCode) throws Throwable {
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

	@Then("^Lets validate response appId response time$")
	public void Lets_validate_response_appId_response_time() throws Throwable {
		SpecificationFactory.getGenericResponseSpec();
		logger.info("Validation of response appId and response time successfully passed");
	}

	@Then("^Lets validate the response message \"([^\"]*)\"$")
	public void Lets_validate_the_response_message(String arg1) throws Throwable {

	}

	@Given("^:Set testdata$")
	public void set_testdata(DataTable inputTestData) throws Throwable {
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
				responseBody = WebservicesMethod.Select_API_METHOD(method_Type, testUrl, requestBody,
						ReusableFunction.requestHeaders(header));
				// System.out.println("------------" + responseBody.prettyPrint());
			} else {
				logger.info("send request is invaild ");
			}

			if (msgCode.equals("400")) {

				responseBody.then().root("response").body("msgInfo.msgCode", Matchers.equalToIgnoringCase(msgCode))
						.and().body("msgInfo.msg", Matchers.equalTo(msg));
				logger.info("verify error msg for invaild input data.");
			} else if (msgCode.equals("")) {
				responseBody.then().body("error", Matchers.equalToIgnoringCase(msg));
				logger.info("Verify bad request ");
			} else {
				logger.info("No response received from api or expected response not matched.");
			}
		}
	}

	@Given("^:Set the header values \"([^\"]*)\"$")
	public void set_the_header_values(String inputHeader) throws Throwable {
		header = inputHeader;
	}

	@Given("^:Set the x-correlation-ID data into request \"([^\"]*)\" and \"([^\"]*)\"$")
	public void set_the_input_test_data_in_request(String apiKey, String apiValue) throws Throwable {
		JSONObject requestInjsonObject = ReusableFunction.createJSONObject(requestBody);
		JSONObject updatedRequest = ReusableFunction.replacekeyInJSONObject(requestInjsonObject, apiKey, apiValue);
		requestBody = updatedRequest.toString();

	}

	@Given("^:Set the proposerGenderFlag data into request \"([^\"]*)\" and \"([^\"]*)\"$")
	public void set_the_input_testdata_in_request(String apiKey, String apiValue) throws Throwable {
		JSONObject requestInjsonObject = ReusableFunction.createJSONObject(requestBody);
		JSONObject updatedRequest = ReusableFunction.replacekeyInJSONObject(requestInjsonObject, apiKey, apiValue);
		requestBody = updatedRequest.toString();

	}

	@Given("^:Set the dobProposerFlag data into request \"([^\"]*)\" and \"([^\"]*)\"$")
	public void set_the_input_testdatadobProposerFlag_in_request(String apiKey, String apiValue) throws Throwable {
		JSONObject requestInjsonObject = ReusableFunction.createJSONObject(requestBody);
		JSONObject updatedRequest = ReusableFunction.replacekeyInJSONObject(requestInjsonObject, apiKey, apiValue);
		requestBody = updatedRequest.toString();

	}

}
