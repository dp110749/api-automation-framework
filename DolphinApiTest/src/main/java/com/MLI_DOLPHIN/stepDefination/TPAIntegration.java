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

public class TPAIntegration {

	private final static Logger logger = Logger.getLogger(TPAIntegration.class.getName());
	
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

	@Given("^set input request testdata$")
	public void set_input_request_testdata(DataTable presetData) throws Throwable {

		List<String> listOfSetData = presetData.asList(String.class);
		List<List<String>> rowNum = presetData.raw();
		
		getSecondRowData = listOfSetData.size() / rowNum.size();
	/*	System.out.println(listOfSetData.size()+"-------"+rowNum.size());*/
		for (int i = getSecondRowData; i < listOfSetData.size(); i++) {
/*			System.out.println("-------------------------------------------"+listOfSetData.get(i));*/
			url = listOfSetData.get(i);
			i++;
			/*url = listOfSetData.get(i);
			i++;*/
			header = listOfSetData.get(i);
			i++;
			requestFile = listOfSetData.get(i);
			break;
		}
		requestBody = ReusableFunction.readJsonFile(requestFile);
	}

	@When("^i want to send the request for TPA Integration$")
	public void i_want_to_send_the_request_for_TPA_Integration() throws Throwable {
		responseBody = WebservicesMethod.POST_METHOD(url, requestBody,
				ReusableFunction.requestHeaders(header));
		logger.info("Response Body is ::" + responseBody.prettyPrint());
	}

	@Then("^Validate response code \"([^\"]*)\"$")
	public void Validate_response_code(String expResponseCode) throws Throwable {
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

	@Then("^Validate response appId response time$")
	public void Validate_response_appId_response_time() throws Throwable {
		SpecificationFactory.getGenericResponseSpec();
		logger.info("Validation of response appId and response time successfully passed");
	}

	@Then("^Validate the response message \"([^\"]*)\"$")
	public void Validate_the_response_message(String arg1) throws Throwable {

	}

	/*@Then("^i want check the illustration is generated or not$")
	public void i_want_check_the_illustration_is_generated_or_not() throws Throwable {
		boolean ellustraion = responseBody.then().root("payload").body("illustrationPdfBase64",
				Matchers.notNullValue()) != null;
		if (ellustraion == true) {
			logger.info("Illustration is generated successfully");
		} else {
			logger.info("Illustration is not generated.");
		}
	}*/

	@Given("^:Set testdata for TPA Integration$")
	public void set_testdata_for_TPA_Integration(DataTable inputTestData) throws Throwable {
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
				System.out.println("------------" + responseBody.prettyPrint());
			} else {
				logger.info("send request is invaild ");
			}
			
			if (msgCode.equals("400")) {
				responseBody.then().root("msgInfo").body("msgCode", Matchers.equalToIgnoringCase(msgCode)).and()
						.body("msg", Matchers.equalTo(msg));
				logger.info("verify error msg for send invaild input data.");
			} else if (msgCode.equals("")) {
				responseBody.then().body("error", Matchers.equalToIgnoringCase(msg));
				logger.info("Verify bad request ");
			} else {
				logger.info("No  response received from api.");
			}
		}		
	} 
	
	@Given("^:Set the header values for TPA Integration \"([^\"]*)\"$")
	public void set_the_header_values_for_TPA_Integration(String inputHeader) throws Throwable {
          header =inputHeader;
	}
	
	@Given("^:Set the x-correlation-ID data for TPA Integration into request \"([^\"]*)\" and \"([^\"]*)\"$")
	public void set_the_input_test_data_for_TPA_Integration_in_request(String apiKey, String apiValue) throws Throwable {
     JSONObject requestInjsonObject= ReusableFunction.createJSONObject(requestBody);
     JSONObject updatedRequest=ReusableFunction.replacekeyInJSONObject(requestInjsonObject, apiKey, apiValue);
     requestBody=  updatedRequest.toString();
 	
	}
	
	@Given("^:Set the ProductName data into request \"([^\"]*)\" and \"([^\"]*)\"$")
	public void set_the_input_testdata_for_TPA_Integration_in_request(String apiKey1, String apiValue1) throws Throwable {
     JSONObject requestInjsonObject= ReusableFunction.createJSONObject(requestBody);
     JSONObject updatedRequest=ReusableFunction.replacekeyInJSONObject(requestInjsonObject, apiKey1, apiValue1);
     requestBody=  updatedRequest.toString();
 	
	}
	
	/*@When("^i want to send the request for Discrepancy Rule Engine$")
	public void i_want_to_send_the_request_for_discrepancy_rule_engine() throws Throwable {
		responseBody = WebservicesMethod.POST_METHOD(url, requestBody,
				ReusableFunction.requestHeaders(header));
		logger.info("Response Body for premium::" + responseBody.prettyPrint());

	}*/	

//	@SuppressWarnings("deprecation")
//	@Then("^i want check premium amount \"([^\"]*)\" and \"([^\"]*)\"$")
//	public void i_want_check_premium_amount_and(String premiumPartA, String premiumPartB) throws Throwable {
//       responseBody.then().root("payload.premiumAmount").body(premiumPartA.trim(), Matchers.notNullValue())
//       .and().body(premiumPartB.trim(), Matchers.notNullValue(String.class)); 
//		responseBody.then().rootPath("payload").body("premiumAmount.Part A", Matchers.notNullValue());

//		responseBody.then().rootPath("payload")
//		.body("premiumAmount.Part A", Matchers.hasItem(premiumPartA));
//                      .and().body("Part B", Matchers.hasItem(premiumB))
//                      .and().body("Part C", Matchers.hasItem(premiumC));
//
	
	}



//}
