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
import io.qameta.allure.Story;
import io.restassured.response.Response;
import junit.framework.Assert;

public class Ifsc_Micr {

	private final static Logger logger = Logger.getLogger(Ifsc_Micr.class.getName());
	public String endPointUri;
	public String apiHeader;	
	public String STATUS_CODE;
	public String FILE_PATH;
	public String APIREQUEST;
	public String RESPONSEBODY;
	public String TESTREQUEST;
	public String FINALREQUEST;
	public Response responseOFbody;

	@Description("To test the functionality with valid input parameter ")
	@Given("^I want to set the initial End Point URL as \"([^\"]*)\" for test case \"([^\"]*)\"$")
	public void i_want_to_set_the_initial_End_Point_URL_as_for_test_case(String url, String testname) throws Throwable {
		logger.info(testname + ":Test execution Started");
		endPointUri = url;
		logger.info("Passing url to set :" + endPointUri);

	}

	@When("^I set header as \"([^\"]*)\"$")
	public void i_set_header_as(String header) throws Throwable {
		apiHeader = header;

	}

	@When("^I hit the API with requestbody and Post method \"([^\"]*)\"$")
	public void i_hit_the_API_with_requestbody_and_Post_method(String requestBodyPath) throws Throwable {

		if (requestBodyPath != null && !requestBodyPath.isEmpty()) {
			JSONParser jsonParser = new JSONParser();
			FILE_PATH = System.getProperty("user.dir") + "/ApiRequest/" + requestBodyPath;
			logger.info("Path of requestbody file is :: " + FILE_PATH);
			try (FileReader reader = new FileReader(FILE_PATH)) {
				Object obj = jsonParser.parse(reader);
				APIREQUEST = obj.toString();
				logger.info("Request Body is :: " + APIREQUEST);
			} catch (FileNotFoundException | ParseException exc) {
				exc.printStackTrace();
			}
			if (APIREQUEST.length() > 0) {

				responseOFbody = WebservicesMethod.POST_METHOD(endPointUri, APIREQUEST	,
						ReusableFunction.requestHeaders(apiHeader));
			} else {
				logger.info(" Request Body cannot be null or empty!");
			}
		}
		STATUS_CODE = String.valueOf(responseOFbody.getStatusCode());
		logger.info("Response Body is " + responseOFbody.body().prettyPrint());

	}

	@Then("^I try to verify response status code is \"([^\"]*)\"$")
	public void i_try_to_verify_response_status_code_is(String statusCode) throws Throwable {
		Assert.assertEquals(statusCode, STATUS_CODE);
		logger.info("Varification of response status ");
	}
	@Then("^I try to validate the response time response type and response app id$")
	public void i_try_to_validate_the_response_time_response_type_and_response_app_id() throws Throwable {
		responseOFbody.then().spec(SpecificationFactory.getGenericResponseSpec());
	}
	@Then("^I want to validate if response body contains specific string \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_want_to_validate_if_response_body_contains_specific_string_and(String bankIfscCode, String bankMicrCode) throws Throwable {
		String bodyStringValue =responseOFbody.asString();
		Assert.assertTrue(bodyStringValue.contains(bankIfscCode));
		Assert.assertTrue(bodyStringValue.contains(bankMicrCode));
	}
	
	@When("^I hit the API with requestbody and Post method \"([^\"]*)\" jsonkey is \"([^\"]*)\" and micr Code \"([^\"]*)\"$")
	public void i_hit_the_API_with_requestbody_and_Post_method_jsonkey_is_and_micr_Code(String requestBodyPath, String jsonkey, String value) throws Throwable {
		
		if (requestBodyPath != null && !requestBodyPath.isEmpty()) {
			JSONParser jsonParser = new JSONParser();
			FILE_PATH = System.getProperty("user.dir") + "/ApiRequest/" + requestBodyPath;
			logger.info("Path of requestbody file is :: " + FILE_PATH);
			try (FileReader reader = new FileReader(FILE_PATH)) {
				Object obj = jsonParser.parse(reader);
				APIREQUEST = obj.toString();
				JSONObject jsonobject = ReusableFunction.createJSONObject(APIREQUEST);
				JSONObject jsonRequest = ReusableFunction.replacekeyInJSONObject(jsonobject, jsonkey, value);
				FINALREQUEST = jsonRequest.toString();
				logger.info("Request Body is :: " + FINALREQUEST);
			} catch (FileNotFoundException | ParseException exc) {
				exc.printStackTrace();
			}
		}
		if (FINALREQUEST.length() > 0) {

			responseOFbody = WebservicesMethod.POST_METHOD(endPointUri, FINALREQUEST,
					ReusableFunction.requestHeaders(apiHeader));
		} else {
			logger.info(" Request Body cannot be null or empty!");
		}
		STATUS_CODE = String.valueOf(responseOFbody.getStatusCode());
		logger.info("Response body is ::" + responseOFbody.body().prettyPrint());
	}
	
	@When("^I hit the API with requestbody \"([^\"]*)\" with passing the micrcode \"([^\"]*)\" value \"([^\"]*)\" and replacing the value of ifsc \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_hit_the_API_with_requestbody_with_passing_the_micrcode_value_and_replacing_the_value_of_ifsc_and(String requestBodyPath, String ifsckey, String ifscvalue, String micrKey, String micrValue) throws Throwable {
       		
		if (requestBodyPath != null && !requestBodyPath.isEmpty()) {
			JSONParser jsonParser = new JSONParser();
			FILE_PATH = System.getProperty("user.dir") + "/ApiRequest/" + requestBodyPath;
			logger.info("Path of requestbody file is :: " + FILE_PATH);
			try (FileReader reader = new FileReader(FILE_PATH)) {
				Object obj = jsonParser.parse(reader);
				APIREQUEST = obj.toString();
				JSONObject jsonobject = ReusableFunction.createJSONObject(APIREQUEST);
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

			responseOFbody = WebservicesMethod.POST_METHOD(endPointUri, FINALREQUEST,
					ReusableFunction.requestHeaders(apiHeader));
		} else {
			logger.info(" Request Body cannot be null or empty!");
		}
		STATUS_CODE = String.valueOf(responseOFbody.getStatusCode());
		logger.info("Response body is ::" + responseOFbody.body().prettyPrint());

	}
	@Then("^I want to validate if response body contains specific string \"([^\"]*)\"$")
	public void i_want_to_validate_if_response_body_contains_specific_string(String errorMesg) throws Throwable {
		String bodyStringValue =responseOFbody.asString();
		Assert.assertTrue(bodyStringValue.contains(errorMesg));

	}






}
