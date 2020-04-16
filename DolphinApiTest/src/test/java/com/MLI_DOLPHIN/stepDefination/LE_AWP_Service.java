package com.MLI_DOLPHIN.stepDefination;

import java.util.List;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.specs.SpecificationFactory;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import junit.framework.Assert;

public class LE_AWP_Service {

	private final static Logger logger = Logger.getLogger(LE_AWP_Service.class.getName());
	public String HEADER;
	public String ENDPOINT_URL;
	public Response response;
	public String STATUS_CODE;
	public String requestBodyPath;
	public String inputTestData;
	public String requestBody;
	public String oparationToperform;

	@Given("^Set the request url  and header for AWP Service$")
	public void set_the_request_url_and_header_for_AWP_Service(DataTable inputData) throws Throwable {

		List<String> inputdatalist = inputData.asList(String.class);

		for (int i = 0; i < inputdatalist.size(); i++) {

			ENDPOINT_URL = inputdatalist.get(i);
			HEADER = inputdatalist.get(i + 1);
			requestBodyPath = inputdatalist.get(i + 2);
			inputTestData = inputdatalist.get(i + 3);

			break;
		}
		logger.info("Set up of request url and header");
	}

	@When("^Send the valid request$")
	public void send_the_valid_request() throws Throwable {
		response = ReusableFunction.getResponse(requestBodyPath, ENDPOINT_URL, HEADER);

		logger.info("response body is::" + response.getBody().prettyPrint());
	}

	@Then("^I try Validate the response status code \"([^\"]*)\"$")
	public void validate_the_response_status_code(String statusCode) throws Throwable {
		STATUS_CODE = String.valueOf(response.getStatusCode());
		Assert.assertEquals(STATUS_CODE, statusCode.toString());
		logger.info("validation of response status code.");
	}

	@Then("^I want to validate the response time and request type response app id$")
	public void i_want_to_validate_the_response_time_and_request_type_response_app_id() throws Throwable {
		response.then().spec(SpecificationFactory.getGenericResponseSpec());
		logger.info("Varificarion of response time request type and appId.");
	}

	@Then("^I want to validate the illustration generated or not for valid  request$")
	public void i_want_to_validate_the_illustration_generated_or_not_for_valid_request() throws Throwable {
		response.then().root("payload").body("illustrationPdfBase64", Matchers.notNullValue());
		logger.info("Illustration is generated successfully");

	}

	@Then("^i want to validate success message \"([^\"]*)\"$")
	public void i_want_to_validate_success_message(String message) throws Throwable {
		response.then().root("msgInfo").body("msg", Matchers.equalTo(message.trim()));
		logger.info("varification of message response message");
	}

	@When("^I remove the payload field and send request \"([^\"]*)\"$")
	public void i_remove_the_payload_field_and_send_request(String oparationToperform) throws Throwable {
		// requestBody=ReusableFunction.readJsonFile(requestBodyPath);
		requestBody = ReusableFunction.getSpecificRequest(ReusableFunction.readJsonFile(requestBodyPath), inputTestData,
				oparationToperform);
		logger.info("Sending request is ----------" + requestBody.toString());
		response = WebservicesMethod.POST_METHOD(ENDPOINT_URL, requestBody, ReusableFunction.requestHeaders(HEADER));
		logger.info("response body is :" + response.getBody().prettyPrint());
	}

	@Then("^i want to validate response error message \"([^\"]*)\"$")
	public void i_want_to_validate_response_error_message(String responseMessage) throws Throwable {
		response.then().body("error", Matchers.equalTo(responseMessage.trim()));
		logger.info("Validation of error message successfull");

	}

	@When("^I send request with invalid data$")
	public void i_send_request_with_invalid_data(DataTable inputData) throws Throwable {
		List<String> inputdatalist = inputData.asList(String.class);

		oparationToperform = null;
		inputTestData = null;
		for (int i = 0; i < inputdatalist.size(); i++) {
			oparationToperform = inputdatalist.get(i);
			inputTestData = inputdatalist.get(i + 1);
			break;
		}
		requestBody = ReusableFunction.getSpecificRequest(ReusableFunction.readJsonFile(requestBodyPath), inputTestData,
				oparationToperform);
		logger.info("Sending request is :" + requestBody.toString());
		response = WebservicesMethod.POST_METHOD(ENDPOINT_URL, requestBody, ReusableFunction.requestHeaders(HEADER));
		logger.info("response body is :" + response.getBody().prettyPrint());
	}

	@When("^I send the request to generate premium \"([^\"]*)\"$")
	public void i_send_the_request_to_generate_premium(String url) throws Throwable {
		response = ReusableFunction.getResponse(requestBodyPath, url, HEADER);

		logger.info("response body is::" + response.getBody().prettyPrint());
	}

	@Then("^I want to validate the premium Amount$")
	public void i_want_to_validate_the_premium_Amount() throws Throwable {
		response.then().root("payload").body("premiumAmount", Matchers.notNullValue());
		logger.info("Premium is generated successfully");

	}
	@Then("^i want to validate error message \"([^\"]*)\"$")
	public void i_want_to_validate_error_message(String errorMessage) throws Throwable {
		response.then().body("error", Matchers.equalToIgnoringCase(errorMessage));
		logger.info("Varification of error message when send invalid url");

	}
	@When("^I send request with invalid data for Awp premium generator$")
	public void i_send_request_with_invalid_data_for_Awp_premium_generator(DataTable inputData) throws Throwable {
	
		List<String> inputdatalist = inputData.asList(String.class);

		for (int i = 0; i < inputdatalist.size(); i++) {

			oparationToperform = inputdatalist.get(i);
			inputTestData = inputdatalist.get(i + 1);
			ENDPOINT_URL = inputdatalist.get(i + 2);	
			break;
		}
		requestBody = ReusableFunction.getSpecificRequest(ReusableFunction.readJsonFile(requestBodyPath), inputTestData,
				oparationToperform);
		logger.info("Sending request is :" + requestBody.toString());
		response = WebservicesMethod.POST_METHOD(ENDPOINT_URL, requestBody, ReusableFunction.requestHeaders(HEADER));
		logger.info("response body is :" + response.getBody().prettyPrint());

	}


}
