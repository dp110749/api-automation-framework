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
	public String headers;
	public String endPointUrl;
	public Response response;
	public String expStatusCode;
	public String requestBodyPath;
	public String inputTestData;
	public String requestBody;
	public String oparationToperform;
	public String invalidField;

	@Given("^Set the request url and header for AWP Service$")
	public void set_the_request_url_and_header_for_AWP_Service(DataTable inputData) throws Throwable {

		List<String> inputdatalist = inputData.asList(String.class);
		List<List<String>> NumOfRow = inputData.raw();
		int loopStart = inputdatalist.size() / NumOfRow.size();
		int count = 0;
		for (int i = loopStart; i < inputdatalist.size(); i++) {
			requestBodyPath = inputdatalist.get(i + count);
			count++;
			endPointUrl = inputdatalist.get(i + count);
			count++;
			headers = inputdatalist.get(i + count);

			break;
		}
		requestBody=ReusableFunction.readJsonFile(requestBodyPath);
		logger.info("Set up of request url and header");
		
	}

	@When("^Send the post request$")
	public void send_the_request() throws Throwable {
		response = WebservicesMethod.POST_METHOD(endPointUrl, requestBody, ReusableFunction.requestHeaders(headers));

		logger.info("response body is::" + response.getBody().prettyPrint());
	}

	@Then("^I try Validate the response status code \"([^\"]*)\"$")
	public void validate_the_response_status_code(String statusCode) throws Throwable {
		expStatusCode = String.valueOf(response.getStatusCode());
		Assert.assertEquals(expStatusCode, statusCode.toString());
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

	@Given("^I remove the field from payload and send request$")
	public void i_remove_the_payload_field_and_send_request(DataTable inputData) throws Throwable {
		
       List<String> listData =inputData.asList(String.class);	
       List<List<String>> NumOfRow=  inputData.raw();
       int loopStart=listData.size()/NumOfRow.size();
		for(int i=loopStart;i<listData.size();i++){
			inputTestData=listData.get(i);
			i++;
			oparationToperform=listData.get(i);
			break;
		}

		requestBody = ReusableFunction.getSpecificRequest(requestBody, inputTestData,oparationToperform);
		logger.info("Set up of request input data is completed.");
	}

	@Then("^i want to validate response error message \"([^\"]*)\"$")
	public void i_want_to_validate_response_error_message(String responseMessage) throws Throwable {
		response.then().body("error", Matchers.equalTo(responseMessage.trim()));
		logger.info("Validation of error message successfull");

	}
	
	@Given("^i want to set the data in request$")
	public void i_want_to_set_the_data_in_request(DataTable inputData) throws Throwable {
	
		List<String> listData=inputData.asList(String.class);
		List<List<String>> numOfRow=inputData.raw();
		int loopStart=listData.size()/numOfRow.size();
		 for(int i=loopStart;i<listData.size();i++){
			 oparationToperform=listData.get(i);
			 i++;
			 invalidField=listData.get(i);
			 break;
		 }
			requestBody = ReusableFunction.getSpecificRequest(requestBody, invalidField,oparationToperform);
			logger.info("Set up of request input data is completed.");		 
	}

	@Given("^i want to set headerValue \"([^\"]*)\"$")
	public void i_want_to_set_headerValue(String header) throws Throwable {
		headers=header;
	}

	@Given("^I want to set request url \"([^\"]*)\"$")
	public void i_want_to_the_request_url(String url) throws Throwable {
		endPointUrl=url;
	}

	@Given("^I want to set the input data in request$")
	public void i_want_to_set_the_input_data_in_request(DataTable inputData) throws Throwable {
		List<String> listdata=inputData.asList(String.class);
		List<List<String>> NumofRow=inputData.raw();
		int loopStart=listdata.size()/NumofRow.size();
		for (int i=loopStart;i<listdata.size();i++){
			oparationToperform=listdata.get(i);
			i++;
			inputTestData=listdata.get(i);
			i++;
			endPointUrl=listdata.get(i);
		}
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


}
