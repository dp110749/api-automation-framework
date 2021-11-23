package com.MLI_DOLPHIN.stepDefination;

import static org.testng.Assert.assertEquals;

import java.util.List;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;

import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.specs.SpecificationFactory;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import com.google.gson.JsonObject;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class DolphinPush extends WebservicesMethod{

	private static final Logger logger = Logger.getLogger(DedupeService.class);
	private String actResponseCode;
	private String testData;
	private String oparationType;
	private String messageDesc;
	private  JSONObject jsonObj;
	private JSONObject jsonRequest;

	@Given("^set the request data$")
	public void set_the_request_data(DataTable preSetData) throws Throwable {

		List<String> listOfSetData = preSetData.asList(String.class);
		List<List<String>> numOfRow = preSetData.raw();
		getSecondRowData = listOfSetData.size() / numOfRow.size();
		for (int i = getSecondRowData; i < listOfSetData.size(); i++) {

			endPointUrl = listOfSetData.get(i);
			i++;
			header = listOfSetData.get(i);
			i++;
			requestFile = listOfSetData.get(i);
			i++;
			method_Type=listOfSetData.get(i);
			break;
		}
		requestBody = ReusableFunction.readJsonFile(requestFile);
	}

	@When("^i want to send the request for DolphinPush Servcie$")
	public void i_want_to_send_the_request_for_DolphinPush_Servcie() throws Throwable {

		if (requestBody.length() > 1) {
			responseBody = WebservicesMethod.Select_API_METHOD(method_Type,endPointUrl, requestBody,
					ReusableFunction.requestHeaders(header));
			logger.info("Response Body ::"+responseBody.prettyPrint());
		} else {
			logger.info("request Formate is wrong..");
		}
		
	}

	@Then("^Lets validate response code  of DolphinPush \"([^\"]*)\"$")
	public void lets_validate_response_code_of_DolphinPush(String expResponseCode) throws Throwable {
		actResponseCode = String.valueOf(responseBody.getStatusCode());
		if (actResponseCode.equals(expResponseCode)) {
			assertEquals(actResponseCode, expResponseCode);
			logger.info("Api Hit Successfully..");
		} else {
			logger.info("Api did't Hit..");
		}
	}

	@Then("^Lets validate response appId response time  of DolphinPush$")
	public void lets_validate_response_appId_response_time_of_DolphinPush() throws Throwable {
		SpecificationFactory.getGenericResponseSpec();
	}

	@Then("^Lets validate the response message  of DolphinPush \"([^\"]*)\"$")
	public void lets_validate_the_response_message_of_DolphinPush(String responseMessage) throws Throwable {
		if (responseMessage.equalsIgnoreCase("Success")) {
			responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(responseMessage));
			logger.info("Api getting Pass..");
		} else if (responseMessage.equalsIgnoreCase("Failure")) {
			responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(responseMessage));
			logger.info("API Geeting Fail..");
		}else{
			logger.info("Api not responding..");
		}
	}
	
	@Given("^Set of test data$")
	public void set_of_test_data(DataTable preSetOfData) throws Throwable {
		List<String> listOfData = preSetOfData.asList(String.class);
		List<List<String>> numOfRow = preSetOfData.raw();
		getSecondRowData = listOfData.size() / numOfRow.size();
		for (int i = getSecondRowData; i < listOfData.size(); i++) {
        testData=listOfData.get(i);
        i++;
        oparationType= listOfData.get(i);
        i++;
        messageDesc=listOfData.get(i);
        System.out.println("actula request ============="+requestBody);
       jsonObj= ReusableFunction.createJSONObject(requestBody);
       jsonObj= ReusableFunction.replacekeyInJSONObject(jsonObj, testData, oparationType);
//       requestBody= ReusableFunction.getSpecificRequest(requestBody, testData, oparationType);
      requestBody= jsonObj.toString();
        System.out.println("------"+requestBody);
		}

	}


}
