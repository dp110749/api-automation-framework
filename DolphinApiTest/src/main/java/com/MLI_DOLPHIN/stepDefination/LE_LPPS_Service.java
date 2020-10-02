package com.MLI_DOLPHIN.stepDefination;

import java.util.List;

import org.apache.log4j.Logger;
import org.hamcrest.Matcher;
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

public class LE_LPPS_Service {

	private final static Logger logger = Logger.getLogger(LE_LPPS_Service.class.getName());
	public String illustrationUrl;
	public String premiumUrl;
	public String header;
	public String filePath;
	public String requestBody;
	public Response responseBody;
	public String responseStatusCode;
	public String inputTestData;
	public String oparationType;

	@Given("^: set the request url header and request body$")
	public void set_the_request_url_header_and_request_body(DataTable dataSetup) throws Throwable {

		List<String> listData = dataSetup.asList(String.class);
		List<List<String>> numRow = dataSetup.raw();
		int secondRowData = listData.size() / numRow.size();
		for (int i = secondRowData; i < listData.size(); i++) {
			illustrationUrl = listData.get(i);
			i++;
			premiumUrl = listData.get(i);
			i++;
			header = listData.get(i);
			i++;
			filePath = listData.get(i);
			break;
		}
		requestBody = ReusableFunction.readJsonFile(filePath);
	}

	@When("^:I want to send the request$")
	public void i_want_to_send_the_request() throws Throwable {
		responseBody = WebservicesMethod.POST_METHOD(illustrationUrl, requestBody,
				ReusableFunction.requestHeaders(header));
		logger.info("Response Body is ::" + responseBody.prettyPrint());
	}

	@Then("^:I want to validate the response code \"([^\"]*)\"$")
	public void i_want_to_validate_the_response_code(String statusCode) throws Throwable {
		responseStatusCode = String.valueOf(responseBody.getStatusCode());
		Assert.assertEquals(statusCode, responseStatusCode);
		logger.info("Varification of status code is successfully pass:");

	}

	@Then("^:I want to validate the response payload data$")
	public void i_want_to_validate_the_response_payload_data() throws Throwable {
		responseBody.then().root("payload").body("illustrationPdfBase64", Matchers.notNullValue());
		logger.info("Illustration is generated successfully");

	}

	@Then("^:I want to validate the response message \"([^\"]*)\"$")
	public void i_want_to_validate_the_response_message(String responseMsg) throws Throwable {

		responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(responseMsg));
		logger.info("Illustration is generated successfully");

	}

	@Then("^:I want to validate the repsonse appid and response time$")
	public void i_want_to_validate_the_repsonse_appid_and_response_time() throws Throwable {
		responseBody.then().spec(SpecificationFactory.getGenericResponseSpec());
		logger.info("validation of response time type of response and response app id");

	}

	@Given("^: I want to set header value null \"([^\"]*)\"$")
	public void i_want_to_set_header_value_null(String inputheader) throws Throwable {
		header = inputheader;

	}

	@Given("^: I want to set invalid url \"([^\"]*)\" and url type \"([^\"]*)\"$")
	public void i_want_to_set_invalid_url(String inputUrl, String urlType) throws Throwable {

		if (urlType.equalsIgnoreCase("illustrationurl")) {
			illustrationUrl = inputUrl;
			logger.info("illustation set url for testing is");
		} else if (urlType.equalsIgnoreCase("premiumurl")) {
			premiumUrl = inputUrl;
			logger.info("premium url set for testing is");
		} else {
			logger.info("no url set..");
		}
	}

	@Given("^: I want to set inputdata in request$")
	public void i_want_to_set_inputdata_in_request(DataTable inputTestdata) throws Throwable {
		List<String> listData = inputTestdata.asList(String.class);
		List<List<String>> numOfRow = inputTestdata.raw();
		int statrtLoop = listData.size() / numOfRow.size();
		// Loop will start from second row
		for (int i = statrtLoop; i < listData.size(); i++) {
			inputTestData = listData.get(i);
			i++;
			oparationType = listData.get(i);
			break;
		}
		requestBody = ReusableFunction.getSpecificRequest(requestBody, inputTestData, oparationType);
		logger.info("Data set successfully for testing " + requestBody);
	}

	@Then("^:I want to validate the response error message \"([^\"]*)\"$")
	public void i_want_to_validate_the_response_error_message(String responseMessage) throws Throwable {
		responseBody.then().body("error", Matchers.equalTo(responseMessage));
		logger.info("validation of response message successfully.");
	}

	@When("^:I want to send the request for premium$")
	public void i_want_to_send_the_request_for_premium() throws Throwable {
		responseBody = WebservicesMethod.POST_METHOD(premiumUrl, requestBody, ReusableFunction.requestHeaders(header));
		logger.info("Response Body is ::" + responseBody.prettyPrint());

	}

	@Then("^:I want to validate the respone premiunm field$")
	public void i_want_to_validate_the_respone_premiunm_field() throws Throwable {
		responseBody.then().root("payload")
				.body("premiumAmount.biInstallmentPremiumTotalWithoutGST", Matchers.notNullValue()).and()
				.body("premiumAmount.biInstallmentPremiumBasePlanWithGST", Matchers.notNullValue());
	}

	@Given("^: i want set the data for testing$")
	public void i_want_set_the_data_for_testing(DataTable inputData) throws Throwable {

		List<String> listData = inputData.asList(String.class);
		List<List<String>> numOfRow = inputData.raw();
		int secondRowIndex = listData.size() / numOfRow.size();
		for (int i = secondRowIndex; i < listData.size(); i++) {
             inputTestData=listData.get(i);
             i++;
            oparationType= listData.get(i);
            break;
             
		}

		requestBody=ReusableFunction.getSpecificRequest(requestBody, inputTestData, oparationType);
	}
}
