package com.MLI_DOLPHIN.stepDefination;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.specs.SpecificationFactory;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import junit.framework.Assert;

public class MyMoney extends WebservicesMethod{

	private final static Logger logger = Logger.getLogger(MyMoney.class.getName());
	private String policyNumber;
	private String premiumAmount;
	public String FILE_PATH;
	public String APIREQUEST;
	public String TESTREQUEST;
	public String FINALREQUEST;
	private List<String> listOfSetData;
	private List<List<String>> numOfRow;
	
	@Given("^Set the prerequest data for my-MoneyService$")
	public void set_the_prerequest_data_for_my_MoneyService(DataTable SetOfPreRequestData) throws Throwable {
		listOfSetData = SetOfPreRequestData.asList(String.class);
		numOfRow = SetOfPreRequestData.raw();
		getSecondRowData = listOfSetData.size() / numOfRow.size();
		for (int i = getSecondRowData; i < listOfSetData.size(); i++) {
			endPointUrl = listOfSetData.get(i);
			i++;
			header = listOfSetData.get(i);
			i++;
			requestFile = listOfSetData.get(i);
			i++;
			policyNumber = listOfSetData.get(i);
			i++;
			premiumAmount = listOfSetData.get(i);
			i++;
			method_Type=listOfSetData.get(i);
			break;
		}
		requestBody = ReusableFunction.readJsonFile(requestFile);
	}

	public String getRequestBody() {

		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "policyNo" + "}}"), policyNumber);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "premiumAmount" + "}}"), premiumAmount);

		return requestBody;
	}

	@Given("^I want to set the policy number for mymoneyApi\"([^\"]*)\"$")
	public void i_want_to_set_the_policy_number_for_mymoneyApi(String policyNumber) throws Throwable {
		this.policyNumber = policyNumber;
		logger.info("Set the policy Number is " + policyNumber);
	}

	@Given("^I want to set the premiumAmount for MymoneyApi\"([^\"]*)\"$")
	public void i_want_to_set_the_premiumAmount_for_MymoneyApi(String premiumAmount) throws Throwable {

		this.premiumAmount = premiumAmount;
		logger.info("Set the premiumAmount is " + premiumAmount);

	}

	// @Given("^I want to set the initial End Point URL for mymoney api
	// \"([^\"]*)\" for test case \"([^\"]*)\"$")
	// public void
	// i_want_to_set_the_initial_End_Point_URL_for_mymoney_api_for_test_case(String
	// url, String testname) throws Throwable {
	// logger.info(testname + ":Test execution Started");
	// endPointUri = url;
	// logger.info("Passing url to set :" + endPointUri);
	// }

	@When("^I set header for mymoney api \"([^\"]*)\"$")
	public void i_set_header_for_mymoney_api(String header) throws Throwable {
		this.header = header;

	}

/*	 @When("^I hit the myMoney API with requestbody and Post method
	 \"([^\"]*)\"$")
	 public void i_hit_the_myMoney_API_with_requestbody_and_Post_method(String
	 requestBodyPath) throws Throwable {
	 if (requestBodyPath != null && !requestBodyPath.isEmpty()) {
	 JSONParser jsonParser = new JSONParser();
	 FILE_PATH = System.getProperty("user.dir") + "/ApiRequest/" +
	 requestBodyPath;
	 logger.info("Path of requestbody file is :: " + FILE_PATH);
	 try (FileReader reader = new FileReader(FILE_PATH)) {
	 Object obj = jsonParser.parse(reader);
	 APIREQUEST = obj.toString();
	 logger.info("Request Body is :: " + APIREQUEST);
	 } catch (FileNotFoundException | ParseException exc) {
	 exc.printStackTrace();
	 }
	 if (APIREQUEST.length() > 0) {
	
	 responseOFbody = WebservicesMethod.POST_METHOD(endPointUri, APIREQUEST ,
	 ReusableFunction.requestHeaders(apiHeader));
	 } else {
	 logger.info(" Request Body cannot be null or empty!");
	 }
	 }
	 STATUS_CODE = String.valueOf(responseOFbody.getStatusCode());
	 logger.info("Response Body is " + responseOFbody.body().prettyPrint());
	
	 }
*/
	@When("^I hit the myMoney API with requestbody and Post method$")
	public void i_hit_the_myMoney_API_with_requestbody_and_Post_method() throws Throwable {
		if (getRequestBody() != null && !getRequestBody().isEmpty()) {
			if (requestBody.length() > 0) {

				responseBody = WebservicesMethod.Select_API_METHOD(method_Type,endPointUrl, getRequestBody(),
						ReusableFunction.requestHeaders(header));
			} else {
				logger.info(" Request Body cannot be null or empty!");
			}
		}
		responseCode = String.valueOf(responseBody.getStatusCode());
		logger.info("Response Body is " + responseBody.body().prettyPrint());

	}

	@Then("^I try to verify mymoney rsponse status code is\"([^\"]*)\"$")
	public void i_try_to_verify_mymoney_response_status_code_is(String statusCode) throws Throwable {
		Assert.assertEquals(statusCode, responseCode);
		logger.info("Varification of response status ");
	}

	@Then("^I want to validate the response message for mymoney\"([^\"]*)\"$")

	public void VerryFyResponseMessage(String responseMsg) throws Throwable {
		responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(responseMsg));
		logger.info("Varification of response message is " + responseMsg);
	}

	@Then("^I try to validate the response time response type and response app id for myMoney api$")
	public void i_try_to_validate_the_response_time_response_type_and_response_app_id_for_myMoney_api()
			throws Throwable {
		responseBody.then().spec(SpecificationFactory.getGenericResponseSpec());
	}

	/*
	 * @Then("^I want to validate if mymoney response body contains specific string \"([^\"]*)\" and \"([^\"]*)\"$"
	 * ) public void
	 * i_want_to_validate_if_mymoney_response_body_contains_specific_string_and(
	 * String collectedAmount, String BounceAmount) throws Throwable { String
	 * bodyStringValue =responseOFbody.asString();
	 * Assert.assertTrue(bodyStringValue.contains(collectedAmount));
	 * Assert.assertTrue(bodyStringValue.contains(BounceAmount)); }
	 */
	@Then("^I want to validate the collected amount\"([^\"]*)\"$")

	public void i_want_to_validate_the_collected_amount(String expCollectedAmount) throws Throwable {
		String bodyStringValue = responseBody.asString();
		Assert.assertTrue(bodyStringValue.contains(expCollectedAmount));
		logger.info("Validated collected amount is " + expCollectedAmount);
	}

	@Then("^i want to validate the bounce amount\"([^\"]*)\"$")
	public void i_want_to_validate_the_bounce_amount(String expBounceAmount) throws Throwable {
		String bodyStringValue = responseBody.asString();
		Assert.assertTrue(bodyStringValue.contains(expBounceAmount));
		logger.info("Validate bounce amount is " + expBounceAmount);

	}

	@Then("^i want to validate the clear amount\"([^\"]*)\"$")
	public void i_want_to_validate_the_clear_aamount(String expClearAmount) throws Throwable {
		String bodyStringValue = responseBody.asString();
		Assert.assertTrue(bodyStringValue.contains(expClearAmount));
		logger.info("Validate clear amount is " + expClearAmount);

	}
	
	@Then("^I want to validate the response status of policy\"([^\"]*)\"$")
	public void i_want_to_validate_the_response_status_of_policy(String expResponseMessage) throws Throwable {
		String bodyStringValue = responseBody.asString();
		Assert.assertTrue(bodyStringValue.contains(expResponseMessage));
		logger.info("Validated Policy Status is " + expResponseMessage);

	}

/*	@When("^I hit the myMoney API with requestbody and Post method \"([^\"]*)\" and policyNoKey \"([^\"]*)\" and PolicyNumValue \"([^\"]*)\"$")
	public void i_hit_the_myMoney_API_with_requestbody_and_Post_method_and_policyNoKey_and_PolicyNumValue(
			String requestBodyPath, String policyNumKey, String PolicyNumValue) throws Throwable {

		if (requestBodyPath != null && !requestBodyPath.isEmpty()) {
			JSONParser jsonParser = new JSONParser();
			FILE_PATH = System.getProperty("user.dir") + "/ApiRequest/" + requestBodyPath;
			logger.info("Path of requestbody file is :: " + FILE_PATH);
			try (FileReader reader = new FileReader(FILE_PATH)) {
				Object obj = jsonParser.parse(reader);
				APIREQUEST = obj.toString();
				JSONObject jsonobject = ReusableFunction.createJSONObject(APIREQUEST);
				JSONObject jsonRequest = ReusableFunction.replacekeyInJSONObject(jsonobject, policyNumKey,
						PolicyNumValue);
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
*/
	@Then("^I want to validate if mymoney api response body contains specific string \"([^\"]*)\"$")
	public void i_want_to_validate_if_mymoney_api_response_body_contains_specific_string(String errorMesg)
			throws Throwable {
		String bodyStringValue = responseBody.asString();
		Assert.assertTrue(bodyStringValue.contains(errorMesg));
		logger.info("Validation of error message successfull.");
	}

	@When("^I set header for myMoney api \"([^\"]*)\"$")
	public void i_set_header_for_myMoney_api(String inputheader) throws Throwable {

		header = inputheader;
		logger.info("Set the request header successfully.");

	}

	@Then("^I want to validate if myMoney api response body contains specific string \"([^\"]*)\"$")
	public void i_want_to_validate_if_myMoney_api_response_body_contains_specific_string(String message)
			throws Throwable {
		responseBody.then().body("message", Matchers.equalTo(message));
		logger.info("Validation of response body message when send the header as null.");
	}

}
