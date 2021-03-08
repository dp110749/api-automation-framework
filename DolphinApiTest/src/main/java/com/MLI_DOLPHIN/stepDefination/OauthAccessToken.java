package com.MLI_DOLPHIN.stepDefination;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.specs.SpecificationFactory;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import junit.framework.Assert;

public class OauthAccessToken {
	private final static Logger logger = Logger.getLogger(OauthAccessToken.class.getName());
	public String apiEndPointUri;
	public String requesHeader;
	public String CONTENT_TYPE;
	public String STATUS_CODE;
	public String FILE_PATH;
	public String REQUESTBODY;
	public String RESPONSEBODY;
	public String FINALREQUEST;
	public Response responseBody;

	public WebservicesMethod sendTheRequest;

	@Given("^I want to set URL as \"([^\"]*)\" for test case \"([^\"]*)\"$")
	public void i_want_to_set_URL_as_for_test_case(String url, String testname) throws Throwable {
		logger.info(testname + ":Test execution Started");

		apiEndPointUri = url;
		logger.info("Passing url to set :" + apiEndPointUri);
	}

	@When("^I set headers client id as \"([^\"]*)\"$")
	public void i_set_headers_client_id_as(String headers) throws Throwable {
		requesHeader = headers;

	}

	@When("^I hit the API with requestbody \"([^\"]*)\"$")
	public void i_hit_the_API_with_requestbody_and_request_method_is(String requestBodyPath) throws Throwable {

		if (requestBodyPath != null && !requestBodyPath.isEmpty()) {
			JSONParser jsonParser = new JSONParser();
			FILE_PATH = System.getProperty("user.dir") + "/ApiRequest/" + requestBodyPath;
			logger.info("Path of requestbody file is :: " + FILE_PATH);
			try (FileReader reader = new FileReader(FILE_PATH)) {
				Object obj = jsonParser.parse(reader);
				REQUESTBODY = obj.toString();
				logger.info("Request Body is :: " + REQUESTBODY);
			} catch (FileNotFoundException | ParseException exc) {
				exc.printStackTrace();
			}
			if (REQUESTBODY.length() > 0) {

				responseBody = WebservicesMethod.OAUTHAPI_POST_METHOD(apiEndPointUri, REQUESTBODY,
						ReusableFunction.requestHeaders(requesHeader));
			} else {
				logger.info(" Request Body cannot be null or empty!");
			}
		}
		STATUS_CODE = String.valueOf(responseBody.getStatusCode());
		logger.info("Response Body is " + responseBody.body().prettyPrint());
	}

	@Then("^I try to verify the status code is \"([^\"]*)\"$")
	public void i_try_to_verify_the_status_code_is(String statusCode) throws Throwable {
		Assert.assertEquals(statusCode, STATUS_CODE);
		logger.info("Varification of response status ");
	}

	@Then("^I try to verify the response value \"([^\"]*)\" is \"([^\"]*)\" and successmessage \"([^\"]*)\"$")
	public void i_try_to_verify_the_response_value_is_and_successmessage(String jsonPayload, String responseValue,
			String responseMessage) throws Throwable {

//		responseBody.then().spec(SpecificationFactory.getGenericResponseSpec()).and().root(jsonPayload)
		responseBody.then().root(jsonPayload).body(responseValue, Matchers.notNullValue()).and().root("msgInfo")
				.body("msgDescription", Matchers.comparesEqualTo(responseMessage.trim()));

	}

	@When("^I hit the API with invalid credential \"([^\"]*)\" key \"([^\"]*)\" and invalidPassword \"([^\"]*)\"$")
	public void i_hit_the_API_with_invalid_credential_key_and_invalidPassword(String requestBodyPath, String jsonkey,
			String value) throws Throwable {

		if (requestBodyPath != null && !requestBodyPath.isEmpty()) {
			JSONParser jsonParser = new JSONParser();
			FILE_PATH = System.getProperty("user.dir") + "/ApiRequest/" + requestBodyPath;
			logger.info("Path of requestbody file is :: " + FILE_PATH);
			try (FileReader reader = new FileReader(FILE_PATH)) {
				Object obj = jsonParser.parse(reader);
				REQUESTBODY = obj.toString();
				JSONObject jsonobject = ReusableFunction.createJSONObject(REQUESTBODY);
				JSONObject jsonRequest = ReusableFunction.replacekeyInJSONObject(jsonobject, jsonkey, value);
				FINALREQUEST = jsonRequest.toString();
				logger.info("Request Body is :: " + FINALREQUEST);
			} catch (FileNotFoundException | ParseException exc) {
				exc.printStackTrace();
			}
		}
		if (FINALREQUEST.length() > 0) {

			responseBody = WebservicesMethod.OAUTHAPI_POST_METHOD(apiEndPointUri, FINALREQUEST,
					ReusableFunction.requestHeaders(requesHeader));
		} else {
			logger.info(" Request Body cannot be null or empty!");
		}
		STATUS_CODE = String.valueOf(responseBody.getStatusCode());
		logger.info("Response body is ::" + responseBody.body().prettyPrint());
	}

	@Then("^I try to verify the response message \"([^\"]*)\"$")
	public void i_try_to_verify_the_response_message(String responseMessage) throws Throwable {

		responseBody.then().root("msgInfo").body("msgDescription", Matchers.comparesEqualTo(responseMessage.trim()));
	}

}
