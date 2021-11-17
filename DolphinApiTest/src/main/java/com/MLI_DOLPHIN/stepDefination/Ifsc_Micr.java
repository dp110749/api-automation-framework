package com.MLI_DOLPHIN.stepDefination;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.specs.SpecificationFactory;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.qameta.allure.Description;
import junit.framework.Assert;

public class Ifsc_Micr extends WebservicesMethod{

	private final static Logger logger = Logger.getLogger(Ifsc_Micr.class.getName());
	public String STATUS_CODE;
	public String TESTREQUEST;
	public String FINALREQUEST;
	
	@Description("To test the functionality with valid input parameter ")
	@Given("^I want to set the initial End Point URL as \"([^\"]*)\" for test case \"([^\"]*)\"$")
	public void i_want_to_set_the_initial_End_Point_URL_as_for_test_case(String url, String testname) throws Throwable {
		logger.info(testname + ":Test execution Started");
		endPointUrl = url;
		logger.info("Passing url to set :" + endPointUrl);

	}

	@When("^I set header as \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_set_header_as(String header,String methodType) throws Throwable {
		this.header = header;
        method_Type=methodType;
	}

	@When("^I hit the API with requestbody and Post method \"([^\"]*)\"$")
	public void i_hit_the_API_with_requestbody_and_Post_method(String requestBodyPath) throws Throwable {

		if (requestBodyPath != null && !requestBodyPath.isEmpty()) {
			JSONParser jsonParser = new JSONParser();
			requestFile = System.getProperty("user.dir") + "/ApiRequest/" + requestBodyPath;
			logger.info("Path of requestbody file is :: " + requestFile);
			try (FileReader reader = new FileReader(requestFile)) {
				Object obj = jsonParser.parse(reader);
				requestBody = obj.toString();
				logger.info("Request Body is :: " + requestBody);
			} catch (FileNotFoundException | ParseException exc) {
				exc.printStackTrace();
			}
			if (requestBody.length() > 0) {

				responseBody = WebservicesMethod.Select_API_METHOD(method_Type,endPointUrl, requestBody	,
						ReusableFunction.requestHeaders(header));
			} else {
				logger.info(" Request Body cannot be null or empty!");
			}
		}
		STATUS_CODE = String.valueOf(responseBody.getStatusCode());
		logger.info("Response Body is " + responseBody.body().prettyPrint());

	}

	@Then("^I try to verify response status code is \"([^\"]*)\"$")
	public void i_try_to_verify_response_status_code_is(String statusCode) throws Throwable {
		Assert.assertEquals(statusCode, STATUS_CODE);
		logger.info("Varification of response status ");
	}
	@Then("^I try to validate the response time response type and response app id$")
	public void i_try_to_validate_the_response_time_response_type_and_response_app_id() throws Throwable {
		responseBody.then().spec(SpecificationFactory.getGenericResponseSpec());
	}
	@Then("^I want to validate if response body contains specific string \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_want_to_validate_if_response_body_contains_specific_string_and(String bankIfscCode, String bankMicrCode) throws Throwable {
		String bodyStringValue =responseBody.asString();
		Assert.assertTrue(bodyStringValue.contains(bankIfscCode));
		Assert.assertTrue(bodyStringValue.contains(bankMicrCode));
	}
	
	@When("^I hit the API with requestbody and Post method \"([^\"]*)\" jsonkey is \"([^\"]*)\" and micr Code \"([^\"]*)\"$")
	public void i_hit_the_API_with_requestbody_and_Post_method_jsonkey_is_and_micr_Code(String requestBodyPath, String jsonkey, String value) throws Throwable {
		
		if (requestBodyPath != null && !requestBodyPath.isEmpty()) {
			JSONParser jsonParser = new JSONParser();
			requestFile = System.getProperty("user.dir") + "/ApiRequest/" + requestBodyPath;
			logger.info("Path of requestbody file is :: " + requestFile);
			try (FileReader reader = new FileReader(requestFile)) {
				Object obj = jsonParser.parse(reader);
				requestBody = obj.toString();
				JSONObject jsonobject = ReusableFunction.createJSONObject(requestBody);
				JSONObject jsonRequest = ReusableFunction.replacekeyInJSONObject(jsonobject, jsonkey, value);
				FINALREQUEST = jsonRequest.toString();
				logger.info("Request Body is :: " + FINALREQUEST);
			} catch (FileNotFoundException | ParseException exc) {
				exc.printStackTrace();
			}
		}
		if (FINALREQUEST.length() > 0) {

			responseBody = WebservicesMethod.Select_API_METHOD(method_Type,endPointUrl, FINALREQUEST,
					ReusableFunction.requestHeaders(header));
		} else {
			logger.info(" Request Body cannot be null or empty!");
		}
		STATUS_CODE = String.valueOf(responseBody.getStatusCode());
		logger.info("Response body is ::" + responseBody.body().prettyPrint());
	}
	
	@When("^I hit the API with requestbody \"([^\"]*)\" with passing the micrcode \"([^\"]*)\" value \"([^\"]*)\" and replacing the value of ifsc \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_hit_the_API_with_requestbody_with_passing_the_micrcode_value_and_replacing_the_value_of_ifsc_and(String requestBodyPath, String ifsckey, String ifscvalue, String micrKey, String micrValue) throws Throwable {
       		
		if (requestBodyPath != null && !requestBodyPath.isEmpty()) {
			JSONParser jsonParser = new JSONParser();
			requestFile = System.getProperty("user.dir") + "/ApiRequest/" + requestBodyPath;
			logger.info("Path of requestbody file is :: " + requestFile);
			try (FileReader reader = new FileReader(requestFile)) {
				Object obj = jsonParser.parse(reader);
				requestBody = obj.toString();
				JSONObject jsonobject = ReusableFunction.createJSONObject(requestBody);
				JSONObject jsonRequest = ReusableFunction.replacekeyInJSONObject(jsonobject, ifsckey, ifscvalue);
				
				TESTREQUEST = jsonRequest.toString();
				
				JSONObject jsonobjectToreplaceMicrkeyValue = ReusableFunction.createJSONObject(TESTREQUEST);
				JSONObject finalJsonRequest = ReusableFunction.replacekeyInJSONObject(jsonobjectToreplaceMicrkeyValue, micrKey, micrValue); 
				
				FINALREQUEST=finalJsonRequest.toJSONString();
				
			} catch (FileNotFoundException | ParseException exc) {
				exc.printStackTrace();
			}
		}
		if (FINALREQUEST.length() > 0) {

			responseBody = WebservicesMethod.Select_API_METHOD(method_Type,endPointUrl, FINALREQUEST,
					ReusableFunction.requestHeaders(header));
		} else {
			logger.info(" Request Body cannot be null or empty!");
		}
		STATUS_CODE = String.valueOf(responseBody.getStatusCode());
		logger.info("Response body is ::" + responseBody.body().prettyPrint());

	}
	@Then("^I want to validate if response body contains specific string \"([^\"]*)\"$")
	public void i_want_to_validate_if_response_body_contains_specific_string(String errorMesg) throws Throwable {
		String bodyStringValue =responseBody.asString();
		Assert.assertTrue(bodyStringValue.contains(errorMesg));

	}






}
