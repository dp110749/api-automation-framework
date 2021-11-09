package com.MLI_DOLPHIN.stepDefination;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.Assert;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class LE_OSP_Service extends WebservicesMethod{
	private final static Logger logger = Logger.getLogger(LE_OSP_Service.class.getName());
//	private int getSecondRow;
//	private String endPointUrl;
//	private String header;
//	private String requestFile;
//	private String correlationId;
	private String insuredGender;
	private String insuredAge;
	private String commitedPrimun;
	private String paymentMode;
//	private String requestBody;
//	private Response responseBody;

	@Given("^Set the pre request data for OSP$")
	public void set_the_pre_request_data_for_OSP(DataTable inputPreRequestData) throws Throwable {
		List<String> listOfpreSetOfData = inputPreRequestData.asList(String.class);
		List<List<String>> NumberOfRow = inputPreRequestData.raw();
		getSecondRowData = listOfpreSetOfData.size() / NumberOfRow.size();
		for (int i = getSecondRowData; i < listOfpreSetOfData.size(); i++) {
			endPointUrl = listOfpreSetOfData.get(i);
			i++;
			header = listOfpreSetOfData.get(i);
			i++;
			requestFile = listOfpreSetOfData.get(i);
			i++;
			correlationId = listOfpreSetOfData.get(i);
			i++;
			insuredGender = listOfpreSetOfData.get(i);
			i++;
			insuredAge = listOfpreSetOfData.get(i);
			i++;
			commitedPrimun = listOfpreSetOfData.get(i);
			i++;
			paymentMode = listOfpreSetOfData.get(i);
			i++;
			method_Type=listOfpreSetOfData.get(i);

		}
		logger.info("Set prequest data in variable successfully..");

	}

	public String getFinalRequestBody() throws IOException {
		requestBody = ReusableFunction.readJsonFile(requestFile);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "X-Correlation-ID" + "}}"), correlationId);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "genderOfInsured" + "}}"), insuredGender);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "ageOfInsured" + "}}"), insuredAge);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "paymentModeName" + "}}"), paymentMode);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "committedPremium" + "}}"), commitedPrimun);
		return requestBody;
	}

	@When("^Send the POST request for OSP$")
	public void send_the_POST_request_for_OSP() throws Throwable {
		responseBody = WebservicesMethod.Select_API_METHOD(method_Type,endPointUrl, getFinalRequestBody(),
				ReusableFunction.requestHeaders(header));
		logger.info("Response body of OSP is ==" + responseBody.prettyPrint());

	}

	@Then("^Lets Validate the response code for OSP is\"([^\"]*)\"$")
	public void lets_Validate_the_response_code_for_OSP_is(String inputStatusCode) throws Throwable {
		Assert.assertEquals(String.valueOf(responseBody.getStatusCode()), inputStatusCode);
		logger.info("Validation of Response code is Successfull passed..");

	}

	@Then("^Lets Validate the Response msgCode for OSP is\"([^\"]*)\"$")
	public void lets_Validate_the_Response_msgCode_for_OSP_is(String inputMsgCode) throws Throwable {
		responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(inputMsgCode));
		logger.info("Validation of response MSg Code is Successful passed..");

	}

	@Then("^Lets Validate the repsonse message for OSP is\"([^\"]*)\"$")
	public void lets_Validate_the_repsonse_message_for_OSP_is(String inputMsg) throws Throwable {
		responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(inputMsg));
		logger.info("Validation of response MSg is Successful passed..");

	}

	@Then("^Let Validate the SumAssurd for OSP is\"([^\"]*)\"$")
	public void let_Validate_the_SumAssurd_for_OSP_is(String inputSumAssurd) throws Throwable {
		responseBody.then().root("payload.premiumAmount").body("sumAssured", Matchers.equalTo(inputSumAssurd));
		logger.info("Validation of response SumAssured is Successful passed..");

	}

	@Then("^Let Validate the ModelPremium for OSP is\"([^\"]*)\"$")
	public void let_Validate_the_ModelPremium_for_OSP_is(String inputModelPremium) throws Throwable {
		responseBody.then().root("payload.premiumAmount").body("modalPremium", Matchers.equalTo(inputModelPremium));
		logger.info("Validation of response ModelPremium is Successful passed..");

	}
	@Given("^Lets Set the Insured Gander is\"([^\"]*)\"$")
	public void lets_Set_the_Insured_Gander_is(String inputInsurdGender) throws Throwable {
		insuredGender=inputInsurdGender;
		logger.info("Insured Gender set successfully...");

	}

	@Given("^Lest Set the Insured Age is\"([^\"]*)\"$")
	public void lest_Set_the_Insured_Age_is(String inputInsuredAge) throws Throwable {
		insuredAge=inputInsuredAge;
		logger.info("Insured Age set successfully...");

	}

	@Given("^Lets Set the Comitted Premium is\"([^\"]*)\"$")
	public void lets_Set_the_Comitted_Premium_is(String inputComittedPremium) throws Throwable {
		commitedPrimun=inputComittedPremium;
		logger.info("Comitted Premium Set successfully..");
		
	}

	@Given("^Lets Set the PaymentMode\"([^\"]*)\"$")
	public void lets_Set_the_PaymentMode(String inputPayMentMode) throws Throwable {
		paymentMode=inputPayMentMode;
		logger.info("paymentMode Set successfully..");		
	}
	@Given("^Lets Set the endpoint Url\"([^\"]*)\"$")
	public void lets_Set_the_endpoint_Url(String inputEndPoint) throws Throwable {
		endPointUrl = inputEndPoint;
		logger.info("Set the url successfully..");
		
	}

	@Then("^Lest Validate the illustration is generated or not$")
	public void lest_Validate_the_illustration_is_generated_or_not() throws Throwable {
		 responseBody.then().root("payload").body("illustrationPdfBase64", Matchers.notNullValue());
		logger.info("Validation of response illustration is Successful passed..");

	}
	@Given("^Lets Set the correlationId\"([^\"]*)\"$")
	public void lets_Set_the_correlationId(String inputCorrelationId) throws Throwable {
      correlationId=inputCorrelationId;
      logger.info("Correlation Id Set successfully..");
		
	}





}
