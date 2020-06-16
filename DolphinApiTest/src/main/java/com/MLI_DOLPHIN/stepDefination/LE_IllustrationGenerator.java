package com.MLI_DOLPHIN.stepDefination;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.apache.log4j.Logger;
import org.codehaus.groovy.transform.EqualsAndHashCodeASTTransformation;
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
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import junit.framework.Assert;

public class LE_IllustrationGenerator {
	
	public String RESPONSEBODY;
	public String STATUSCODE;
	public String ENDPOINTURL;
	public String REQUESTBODY;
	public String FINALREQUEST;
	public String FILE_PATH;
	public String HEADER;
	public Response responseOFbody;
	private final static Logger logger = Logger.getLogger(LE_IllustrationGenerator.class.getName());
	
	@Description("To test when user send the valid request illustration should be generate:")
	@Step("Passing two parameter endurl {0} and test case name {1}")
	@Given("^I wnat to set the request end Point url for \"([^\"]*)\" for test case \"([^\"]*)\"$")
	public void i_wnat_to_set_the_request_end_Point_url_for_for_test_case(String Url, String testCaseName) throws Throwable {
		ENDPOINTURL=Url;
		logger.info(testCaseName+"::Test Execution is started");
	}
	@Step("Set the request header {0}")
	@When("^I set the header \"([^\"]*)\"$")
	public void i_set_the_header(String header) throws Throwable {
		HEADER=header;
	}
		
	@When("^I send the complete request body for \"([^\"]*)\"$")
	@Step("Passing the resuest path to read json request {0} ")
	public  void i_send_the_complete_request_body_for(String requestBodyPath) throws Throwable {
/*		if (requestBodyPath != null && !requestBodyPath.isEmpty()) {
			JSONParser jsonParser = new JSONParser();
			FILE_PATH = System.getProperty("user.dir") + "/ApiRequest/" + requestBodyPath;
			logger.info("Path of requestbody file is :: " + FILE_PATH);
			try (FileReader reader = new FileReader(FILE_PATH)) {
				Object obj = jsonParser.parse(reader);
				REQUESTBODY = obj.toString();
			} catch (FileNotFoundException | ParseException exc) {
				exc.printStackTrace();
			}
			if (REQUESTBODY.length() > 0) {

				responseOFbody = WebservicesMethod.POST_METHOD(ENDPOINTURL, REQUESTBODY,
						ReusableFunction.requestHeaders(HEADER));																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																		
			} else {
				logger.info(" Request Body cannot be null or empty!");
			}
		}
*/		
		responseOFbody=ReusableFunction.getResponse(requestBodyPath, ENDPOINTURL, HEADER);
		STATUSCODE = String.valueOf(responseOFbody.getStatusCode());
		logger.info("Response Body is " + responseOFbody.body().prettyPrint());
        
	}

	@Then("^I verify the response status code \"([^\"]*)\"$")
	public void i_verify_the_response_status_code(String statuscode) throws Throwable {
		Assert.assertEquals(statuscode, STATUSCODE);
		logger.info("Validation of status code is successfully pass:");
	}
	@Then("^I try to validate the generated illustration is not empty$")
	public void i_try_to_validate_the_generated_illustration_is_not_empty() throws Throwable {
		responseOFbody.then().root("payload")
		.body("illustrationPdfBase64", Matchers.notNullValue());
		 logger.info("Illustration is generated successfully");

	}
	
	@Then("^I try to validate the response time response type and response app id for LE FTSP$")
	public void i_try_to_validate_the_response_time_response_type_and_response_app_id_for_LE_FTSP() throws Throwable {
            responseOFbody.then().spec(SpecificationFactory.getGenericResponseSpec());
            logger.info("validation of response time type of response and response app id");
	}

	
	@Then("^I try to validate response  message  discription \"([^\"]*)\" and correlation id \"([^\"]*)\"$")
	public void i_try_to_validate_response_message_discription_and_correlation_id(String message, String correlationId) throws Throwable {
		responseOFbody.then().root("msgInfo").body("msgDescription", Matchers.equalTo(message))
		.and().root("metadata").body("X-Correlation-ID", Matchers.equalTo(correlationId));
		logger.info("Varification of correlation id and successful message generated");
	}

	@When("^I send the complete request body for \"([^\"]*)\" and pass the name of insured as empty key is \"([^\"]*)\" and value \"([^\"]*)\"$")
	public void i_send_the_complete_request_body_for_and_pass_the_name_of_insured_as_empty_key_is_and_value(String requestBodyPath, String insuredNameKey, String insuredNameValue) throws Throwable {

		
		if (requestBodyPath != null && !requestBodyPath.isEmpty()) {
			JSONParser jsonParser = new JSONParser();
			FILE_PATH = System.getProperty("user.dir") + "/ApiRequest/" + requestBodyPath;
			logger.info("Path of requestbody file is :: " + FILE_PATH);
			try (FileReader reader = new FileReader(FILE_PATH)) {
				Object obj = jsonParser.parse(reader);
				REQUESTBODY = obj.toString();
				JSONObject jsonobject = ReusableFunction.createJSONObject(REQUESTBODY);
				JSONObject jsonRequest = ReusableFunction.replacekeyInJSONObject(jsonobject, insuredNameKey, insuredNameValue);
				FINALREQUEST = jsonRequest.toString();
				logger.info("Request Body is :: " + FINALREQUEST);
			} catch (FileNotFoundException | ParseException exc) {
				exc.printStackTrace();
			}
		}
		if (FINALREQUEST.length() > 0) {

			responseOFbody = WebservicesMethod.POST_METHOD(ENDPOINTURL, FINALREQUEST,
					ReusableFunction.requestHeaders(HEADER));
		} else {
			logger.info(" Request Body cannot be null or empty!");
		}
		STATUSCODE = String.valueOf(responseOFbody.getStatusCode());
		logger.info("Response body is ::" + responseOFbody.body().prettyPrint());

	}
	@Then("^I try to validate the generated premium is partA \"([^\"]*)\" and premium partB \"([^\"]*)\" and premium partC \"([^\"]*)\"$")
	public void i_try_to_validate_the_generated_premium_is_partA_and_premium_partB_and_premium_partC(String premiumA, String premiumB, String premiumC) throws Throwable {
	
		responseOFbody.then().rootPath("payload")
				.body("premiumAmount.Part A", Matchers.hasItem(premiumA))
		                      .and().body("Part B", Matchers.hasItem(premiumB))
		                      .and().body("Part C", Matchers.hasItem(premiumC));
		logger.info("Varification of LE Premium Amount successfully pass.");
	}      


	}
