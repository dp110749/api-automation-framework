package com.MLI_DOLPHIN.stepDefination;

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
import io.restassured.response.Response;
import junit.framework.Assert;

public class LE_PWPService extends WebservicesMethod {
	private final static Logger logger = Logger.getLogger(LE_PWPService.class.getName());
	
	public String illustrationUrl;
	public String premiumUrl;	
	public String testData;
	public String oparationToperform;

	@Given("^Set the url header and request$")
	public void set_the_url_header_and_request(DataTable initialData) throws Throwable {

		List<String>  listOfdata= initialData.asList(String.class);
		List<List<String>>  numOfRow= initialData.raw();
		
		getSecondRowData =listOfdata.size()/numOfRow.size();
		for(int i=getSecondRowData;i<listOfdata.size();i++){
			illustrationUrl=listOfdata.get(i);
			i++;
			premiumUrl=listOfdata.get(i);
			i++;
			header=listOfdata.get(i);
			i++;
			requestFile=listOfdata.get(i);
			i++;
			method_Type=listOfdata.get(i);
			break;
		}
		requestBody=ReusableFunction.readJsonFile(requestFile);
	
	}

	@Then("^: Send the request for illustation$")
	public void send_the_request() throws Throwable {
		responseBody = WebservicesMethod.Select_API_METHOD(method_Type,illustrationUrl, requestBody,
				ReusableFunction.requestHeaders(header));
		logger.info("Response Body is ::" + responseBody.prettyPrint());
	}

	@Then("^: I want to validate the response \"([^\"]*)\"$")
	public void i_want_to_validate_the_response(String statusCode) throws Throwable {
		responseCode = String.valueOf(responseBody.getStatusCode());
		Assert.assertEquals(statusCode, responseCode);
		logger.info("Varification of status code is successfully pass:");

	}

	@Then("^: I want to validate the response app id and response time$")
	public void i_want_to_validate_the_response_app_id_and_response_time() throws Throwable {
		SpecificationFactory.getGenericResponseSpec();
		logger.info("Validation of app id and response time successfully pass.");
	}

	@Then("^: I want to validate the response message \"([^\"]*)\"$")
	public void i_want_to_validate_the_response_message(String responseMessage) throws Throwable {
		if(responseMessage.equalsIgnoreCase("Success") || responseMessage.contains("Fail")){
			responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(responseMessage));
			logger.info("varification of response message successfully");

		} else if (responseMessage.equalsIgnoreCase("Bad Request")){
			responseBody.then().body("error", Matchers.equalToIgnoringCase(responseMessage));
			logger.info("User Send the  bad request.");
		}else{
			logger.info("response message does not match");
		}	
		
	}

	@Then("^: I want to validate illustration genrate or not$")
	public void i_want_to_validate_illustration_genrate_or_not() throws Throwable {
		responseBody.then().root("payload").body("illustrationPdfBase64", Matchers.notNullValue());
		logger.info("Illustration is generated successfully");

	}

	@Given("^: Set the input test data$")
	public void set_the_input_test_data(DataTable inputTestData) throws Throwable {
		List<String> listTestData=inputTestData.asList(String.class);
		List<List<String>> numOfRow=inputTestData.raw();
		int firstRow=listTestData.size()/numOfRow.size();
		for(int i=firstRow;i<listTestData.size();i++){
			testData=listTestData.get(i);
			i++;
			oparationToperform=listTestData.get(i);
			break;
		}
		requestBody=ReusableFunction.getSpecificRequest(requestBody, testData, oparationToperform);		
	}
	
	@Then("^: Send the request for premium$")
	public void send_the_request_for_premium() throws Throwable {
		responseBody = WebservicesMethod.Select_API_METHOD(method_Type,premiumUrl, requestBody,
				ReusableFunction.requestHeaders(header));
		logger.info("Response Body is ::" + responseBody.prettyPrint());
	
	}
	@Then("^: I want to validate the premium amount should not be null$")
	public void premium_amount_should_not_be_null() throws Throwable {
		responseBody.then().root("payload").body("premiumAmount.sumAssured", Matchers.notNullValue())
		.and()
		.body("premiumAmount.modalPremium", Matchers.notNullValue())
//		.and()
//		.body("premiumAmount.biInstallmentPremium", Matchers.notNullValue())
		;
		logger.info("Premium is generated successfully");

	}
	@Given("^: Set the request url \"([^\"]*)\"$")
	public void set_the_request_url(String inputUrl) throws Throwable {
	     premiumUrl=inputUrl;
	}
	@Given("^: Set the input test data in request \"([^\"]*)\" and \"([^\"]*)\"$")
	public void set_the_input_test_data_in_request(String apiKey, String apiValue) throws Throwable {
     JSONObject requestInjsonObject= ReusableFunction.createJSONObject(requestBody);
     JSONObject updatedRequest=ReusableFunction.replacekeyInJSONObject(requestInjsonObject, apiKey, apiValue);
     requestBody=  updatedRequest.toString();
 	
	}
	
}
