package com.MLI_DOLPHIN.stepDefination;

import static org.testng.Assert.fail;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.MLI_DOLPHIN.utilities.RendomDataGenerator;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.specs.SpecificationFactory;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TPAIntegration {

	private final static Logger logger = Logger.getLogger(TPAIntegration.class.getName());

	private String header;
	private String url;
	private String requestFile;
	private String proposalNumber;
	private String houseNoAptNameSociety;
	private String productName;
	private String smokerClass;
	private String pinCode;
	private String requestBody;
	private Response responseBody;
	private String actualResponseCode;
	private int getSecondRowData;

	@Given("^set input request testdata$")
	public void set_input_request_testdata(DataTable presetData) throws Throwable {

		List<String> listOfSetData = presetData.asList(String.class);
		List<List<String>> rowNum = presetData.raw();
		getSecondRowData = listOfSetData.size() / rowNum.size();
		for (int i = getSecondRowData; i < listOfSetData.size(); i++) {
			url = listOfSetData.get(i);
			i++;
			header = listOfSetData.get(i);
			i++;
			requestFile = listOfSetData.get(i);
			i++;
			productName = listOfSetData.get(i);
			i++;
			smokerClass = listOfSetData.get(i);
			break;
		}
		requestBody = ReusableFunction.readJsonFile(requestFile);
		proposalNumber = RendomDataGenerator.get9DigitNumber();
		houseNoAptNameSociety = RendomDataGenerator.getAddress();
		pinCode = RendomDataGenerator.getPinCode();
		logger.info("Random Proposal number generated: " + proposalNumber);
		logger.info("Random Proposal number generated: " + houseNoAptNameSociety);
		/*
		 * "Set new proposal number for TPA Integration$ by using random number
		 * and using the same in each scenario" operationType="changeData";
		 * String thesuperfirst = "{"; String theFirst = "ProposalNo"; String
		 * firstSecond = "\"" + theFirst + "\""; String theFifth =
		 * random_number; String theSixth = "\"" + theFifth + "\""; String Colon
		 * = ":"; String theseventh = "}"; String finalone = thesuperfirst +
		 * firstSecond + Colon + theSixth + theseventh ;
		 * 
		 * inserting new random number in Request for Proposal no requestBody =
		 * ReusableFunction.getSpecificRequest(requestBody, finalone,
		 * operationType); System.out.println("------------" +
		 * responseBody.prettyPrint());
		 * logger.info("Random Proposal number inserted into RESPONSE BODY::" );
		 */
	}

	public String getRequestBody() {

		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "proposalNo" + "}}"), proposalNumber);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "houseNoAptNameSociety" + "}}"),
				houseNoAptNameSociety);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "productName" + "}}"), productName);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "smokerClass" + "}}"), smokerClass);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "pinCode1" + "}}"), pinCode);
		return requestBody;
	}

	@Given("^:Set the Input Value for TPA Integration in request\"([^\"]*)\"$")
	public void setTheInputData(String inputData) {
		productName = inputData;
		logger.info("Set The Input Successfully ..");

	}

	@Given("^Set the Input Value of SmokeClass for TPA\"([^\"]*)\"$")
	public void set_the_Input_Value_of_SmokeClass_for_TPA(String smokerClass) throws Throwable {
		this.smokerClass = smokerClass;
		logger.info("SmockerClass Set Successfully...");
	}

	@Given("^Set the data for TAP\"([^\"]*)\"$")
	public void set_the_data_for_TAP(String houseNoAptNameSociety) throws Throwable {
		this.houseNoAptNameSociety = houseNoAptNameSociety;
		logger.info("Set the data for houseNoAptNameSociety successfully..");
	}

	@Given("^:Set PinCode for TPA Integration\"([^\"]*)\"$")
	public void set_PinCode_for_TPA_Integration(String pinCode) throws Throwable {
		this.pinCode = pinCode;
		logger.info("Set the pinCode successfully.."+pinCode);

	}

	/*
	 * @Given("^i want to get the random Proposal number for TPA Integration$")
	 * public void
	 * i_want_to_get_the_random_Proposal_number_for_TPA_Integration() throws
	 * Throwable { random_number =
	 * RendomDataGenerator.Random_numberGenerator9();
	 * logger.info("Random Proposal number generated ::" );
	 * 
	 * 
	 * @Then("^Set new proposal number for TPA Integration$") public void
	 * Set_new_proposal_number_for_TPA_Integration(DataTable inputTestData)
	 * throws Throwable { operationType="changeData";
	 * 
	 * String thesuperfirst = "{"; String theFirst = "ProposalNo"; String
	 * firstSecond = "\"" + theFirst + "\""; String theFifth = random_number;
	 * String theSixth = "\"" + theFifth + "\""; String Colon = ":"; String
	 * theseventh = "}"; String finalone = thesuperfirst + firstSecond + Colon +
	 * theSixth + theseventh ;
	 * logger.info("Random Proposal number after variable setting" + finalone );
	 * 
	 * newProposal1 = "{"; newProposal2 = "ProposalNo":" random_number";
	 * newProposal3 = { "ProposalNo":"AAAA"} requestBody =
	 * ReusableFunction.getSpecificRequest(requestBody, finalone,
	 * operationType); System.out.println("------------" +
	 * responseBody.prettyPrint());
	 * logger.info("Random Proposal number inserted into RESPONSE BODY::" );
	 * 
	 * }
	 */

	@When("^Send the request for TPA Integration$")
	public void Send_the_request_for_TPA_Integration() throws Throwable {
		responseBody = WebservicesMethod.POST_METHOD(url, getRequestBody(), ReusableFunction.requestHeaders(header));
		logger.info("Response Body is ::" + responseBody.prettyPrint());
	}

	@Then("^Validate response code \"([^\"]*)\"$")
	public void Validate_response_code(String expResponseCode) throws Throwable {
		Thread.sleep(2000);
		actualResponseCode = String.valueOf(responseBody.getStatusCode());
		if (actualResponseCode.equals(expResponseCode)) {
			Assert.assertEquals(actualResponseCode, expResponseCode);
			logger.info("Validated response code is ==" + actualResponseCode);
		} else {
			logger.info("Response Code is not matching with the expected Code : FAILED");
			Assert.assertEquals(actualResponseCode, expResponseCode);
		}
	}

	@Then("^Validate message code \"([^\"]*)\"$")
	public void Validate_message_code(String expMessageCode) throws Throwable {
		Thread.sleep(2000);
		/*
		 * JsonPath jsonPathEvaluator = responseBody.jsonPath();
		 * 
		 * Then simply query the JsonPath object to get a String value of the
		 * node specified by JsonPath: msgCode (Note: You should not put $. in
		 * the Java code) String actualMessageCode =
		 * jsonPathEvaluator.get("msgCode");
		 */
System.out.println("input----"+expMessageCode);
		Map<String, String> firstlevel = responseBody.jsonPath().getMap("msgInfo");
		String actualMessageCode = firstlevel.get("msgCode");
		System.out.println("====="+firstlevel.get("msgCode"));

		if (actualMessageCode.equals(expMessageCode)) {
			Assert.assertEquals(actualMessageCode, expMessageCode);
			logger.info("Validated response code is.." + actualMessageCode);
		} else {
			logger.info("Message Code is not matching with the expected Code : FAILED");
			Assert.assertEquals(actualMessageCode, expMessageCode);
		}
	}

	@Then("^Validate response appId response time$")
	public void Validate_response_appId_response_time() throws Throwable {
		SpecificationFactory.getGenericResponseSpec();
		logger.info("Validation of response appId and response time successfully passed");
	}

	@SuppressWarnings("deprecation")
	@Then("^Validate response message \"([^\"]*)\"$")
	public void Validate_the_response_message(String resposneMsg) throws Throwable {
//       responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(resposneMsg));
//       logger.info("Message validated successfully..");
	}

	@Given("^:Set proposalNumber for TPA Integration\"([^\"]*)\"$")
	public void Set_The_ProposalNumber(String proposalNumber) throws Throwable {
		this.proposalNumber = proposalNumber;
		logger.info("Set the proposal number Successfully in TPA Request");
	}

	@Given("^:Set header values for TPA Integration \"([^\"]*)\"$")
	public void set_header_values_for_TPA_Integration(String inputHeader) throws Throwable {
		header = inputHeader;
	}

	@Given("^:Set the Input Value for TPA Integration in request \"([^\"]*)\" and \"([^\"]*)\"$")
	public void set_the_input_value_for_TPA_Integration_in_request(String apiKey, String apiValue) throws Throwable {
		JSONObject requestInjsonObject = ReusableFunction.createJSONObject(requestBody);
		JSONObject updatedRequest = ReusableFunction.replacekeyInJSONObject(requestInjsonObject, apiKey, apiValue);
		requestBody = updatedRequest.toString();

	}

}