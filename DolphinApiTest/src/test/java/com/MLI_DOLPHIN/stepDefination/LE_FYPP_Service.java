package com.MLI_DOLPHIN.stepDefination;

import java.util.List;
import org.apache.log4j.Logger;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
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

public class LE_FYPP_Service {

	private final static Logger logger = Logger.getLogger(LE_FYPP_Service.class.getName());
	private String lepremiumUrl;
	private String leillustrationUrl;
	private String header;
	private String requestFile;
	private String requestBody;
	private String testData;
	private String oparationType;
	private Response responseBody;
	private String actualResponseCode;
	private int getSecondRowData;
	private String testUrl;
	private String msgCode;
	private String msgDescription;

	@Given("^set the input prequest test data$")
	public void set_the_input_prequest_test_data(DataTable presetData) throws Throwable {

		List<String> listOfSetData = presetData.asList(String.class);
		List<List<String>> rowNum = presetData.raw();
		getSecondRowData = listOfSetData.size() / rowNum.size();
		for (int i = getSecondRowData; i < listOfSetData.size(); i++) {
			leillustrationUrl = listOfSetData.get(i);
			i++;
			lepremiumUrl = listOfSetData.get(i);
			i++;
			header = listOfSetData.get(i);
			i++;
			requestFile = listOfSetData.get(i);
			break;
		}
		requestBody = ReusableFunction.readJsonFile(requestFile);
	}

	@When("^i want to send the request for illustration generator$")
	public void i_want_to_send_the_request_for_illustration_generator() throws Throwable {
		responseBody = WebservicesMethod.POST_METHOD(leillustrationUrl, requestBody,
				ReusableFunction.requestHeaders(header));
		logger.info("Response Body is ::" + responseBody.prettyPrint());
	}

	@Then("^i want to validate response code \"([^\"]*)\"$")
	public void i_want_to_validate_response_code(String expResponseCode) throws Throwable {
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

	@Then("^i want to validate response app id response time$")
	public void i_want_to_validate_response_app_id_response_time() throws Throwable {
		SpecificationFactory.getGenericResponseSpec();
		logger.info("Validation of response app id and response time successfully pass::");
	}

	@Then("^i want validate the response message \"([^\"]*)\"$")
	public void i_want_validate_the_response_message(String arg1) throws Throwable {

	}

	@Then("^i want check the illustration is generated or not$")
	public void i_want_check_the_illustration_is_generated_or_not() throws Throwable {
		boolean ellustraion = responseBody.then().root("payload").body("illustrationPdfBase64",
				Matchers.notNullValue()) != null;
		if (ellustraion == true) {
			logger.info("Illustration is generated successfully");
		} else {
			logger.info("Illustration is not generated.");
		}
	}

	@Given("^:Set testing data$")
	public void set_test_data(DataTable inputTestData) throws Throwable {
		List<String> listOfTestData = inputTestData.asList(String.class);
		List<List<String>> numOfRow = inputTestData.raw();
		getSecondRowData = listOfTestData.size() / numOfRow.size();
		for (int i = getSecondRowData; i < listOfTestData.size(); i++) {
			testData = listOfTestData.get(i);
			i++;
			oparationType = listOfTestData.get(i);
			i++;
			testUrl = listOfTestData.get(i);
			i++;
			msgCode = listOfTestData.get(i);
			i++;
			msgDescription = listOfTestData.get(i);
			requestBody = ReusableFunction.getSpecificRequest(requestBody, testData, oparationType);
			if (requestBody.length() > 0) {
				responseBody = WebservicesMethod.POST_METHOD(testUrl, requestBody,
						ReusableFunction.requestHeaders(header));
			} else {
				logger.info("send request is invaild ");
			}
			
			if (msgCode.equals("500")) {
				responseBody.then().root("msgInfo").body("msgCode", Matchers.equalToIgnoringCase(msgCode)).and()
						.body("msg", Matchers.equalTo(msgDescription));
				logger.info("verify error msg for send invaild input data.");
			} else if (msgCode.equals("")) {
				responseBody.then().body("error", Matchers.equalToIgnoringCase(msgDescription));
				logger.info("Verify bad request ");
			} else {
				logger.info("No  response received from api.");
			}
		}		
	} 
	
	@Given("^:Set the header value \"([^\"]*)\"$")
	public void set_the_header_value(String inputHeader) throws Throwable {
          header =inputHeader;
	}
	
	@Given("^: Set the x-correlation id data in request \"([^\"]*)\" and \"([^\"]*)\"$")
	public void set_the_input_test_data_in_request(String apiKey, String apiValue) throws Throwable {
     JSONObject requestInjsonObject= ReusableFunction.createJSONObject(requestBody);
     JSONObject updatedRequest=ReusableFunction.replacekeyInJSONObject(requestInjsonObject, apiKey, apiValue);
     requestBody=  updatedRequest.toString();
 	
	}
	
	@When("^i want to send the request for premium generator$")
	public void i_want_to_send_the_request_for_premium_generator() throws Throwable {
		responseBody = WebservicesMethod.POST_METHOD(lepremiumUrl, requestBody,
				ReusableFunction.requestHeaders(header));
		logger.info("Response Body for premium::" + responseBody.prettyPrint());

	}	

	@SuppressWarnings("deprecation")
	@Then("^i want check premium amount \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_want_check_premium_amount_and(String premiumPartA, String premiumPartB) throws Throwable {
//       responseBody.then().root("payload.premiumAmount").body(premiumPartA.trim(), Matchers.notNullValue())
//       .and().body(premiumPartB.trim(), Matchers.notNullValue(String.class)); 
//		responseBody.then().rootPath("payload").body("premiumAmount.Part A", Matchers.notNullValue());

		responseBody.then().rootPath("payload")
		.body("premiumAmount.Part A", Matchers.hasItem(premiumPartA));
//                      .and().body("Part B", Matchers.hasItem(premiumB))
//                      .and().body("Part C", Matchers.hasItem(premiumC));
//
	
	}



}
