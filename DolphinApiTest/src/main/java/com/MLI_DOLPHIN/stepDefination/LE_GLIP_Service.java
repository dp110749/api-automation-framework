package com.MLI_DOLPHIN.stepDefination;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import cucumber.api.DataTable;
import cucumber.api.java.en.*;
import io.restassured.response.Response;

public class LE_GLIP_Service {
	private final static Logger logger = Logger.getLogger(LE_GLIP_Service.class.getName());
	private String endPointUrl;
	private String header;
	private String requestFile;
	private String purchesePrice;
	private String variant;
	private String productName;
	private String ageOfInsured;
	private String requestBody;
	private Response responseBody;
	private List<String> listOfSetData;
	private List<List<String>> numOfRow;
	private int getSecondRow;

	@Given("^Set the prerequest data for GLIP product$")
	public void setThePrerequistDataForGLIP(DataTable preSetOfData) throws IOException {
		listOfSetData = preSetOfData.asList(String.class);
		numOfRow = preSetOfData.raw();
		getSecondRow = listOfSetData.size() / numOfRow.size();
		for (int i = getSecondRow; i < listOfSetData.size(); i++) {
			endPointUrl = listOfSetData.get(i);
			i++;
			header = listOfSetData.get(i);
			i++;
			requestFile = listOfSetData.get(i);
			i++;
			purchesePrice = listOfSetData.get(i);
			i++;
			variant = listOfSetData.get(i);
			i++;
			productName = listOfSetData.get(i);
			i++;
			ageOfInsured = listOfSetData.get(i);
		}
		requestBody = ReusableFunction.readJsonFile(requestFile);
	}

	public String getRequestBody() {
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "ageOfInsured" + "}}"), ageOfInsured);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "variant" + "}}"), variant);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "purchasePrice" + "}}"), purchesePrice);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "productName" + "}}"), productName);

		return requestBody;
	}

	@When("^Lets send the POST request for GLIP$")
	public void lets_send_the_POST_request_for_GLIP() throws Throwable {
		responseBody = WebservicesMethod.POST_METHOD(endPointUrl, getRequestBody(),
				ReusableFunction.requestHeaders(header));
		logger.info("Getting Response is =: " + responseBody.prettyPrint());
	}

	@Then("^Lets validate the response code for GLIP\"([^\"]*)\"$")
	public void lets_validate_the_response_code_for_GLIP(String responseCode) throws Throwable {
		assertEquals(String.valueOf(responseBody.getStatusCode()), responseCode);
		logger.info("Validation of response code is successfull ..");
	}

	@Then("^Lets validate the response message for GLIP\"([^\"]*)\"$")
	public void lets_validate_the_response_message_for_GLIP(String responseMsg) throws Throwable {
		responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(responseMsg));
		logger.info("Validation of response message is successfull");
	}

	@Then("^Lets validate the response of AFYP\"([^\"]*)\"$")
	public void lets_validate_the_response_of_AFYP(String expResponseValueOfAFYP) throws Throwable {
		responseBody.then().root("payload.premiumAmount").body("AFYP", Matchers.equalTo(expResponseValueOfAFYP));
		logger.info("Validated  afyp is " + expResponseValueOfAFYP);
	}

	@Then("^Lets validate the response of Anualised premium\"([^\"]*)\"$")
	public void lets_validate_the_response_of_Anualised_premium(String expAnualisedPremium) throws Throwable {

		responseBody.then().root("payload.premiumAmount").body("biAnnualizedPremium",
				Matchers.equalTo(expAnualisedPremium));
		logger.info("Validated  anualized premium is " + expAnualisedPremium);

	}

	@Then("^Lets validate the response of installment Premium\"([^\"]*)\"$")
	public void lets_validate_the_response_of_installment_Premium(String expInstallMentPremium) throws Throwable {
		responseBody.then().root("payload.premiumAmount").body("biInstallmentPremium",
				Matchers.equalTo(expInstallMentPremium));
		logger.info("Validated installmentpremium is " + expInstallMentPremium);

	}

	@Then("^Lest Validate the response of anualincome\"([^\"]*)\"$")
	public void Lets_Validate_responseOfAnualincome(String expAnualIncome) {
		responseBody.then().root("payload.premiumAmount").body("annualIncome", Matchers.equalTo(expAnualIncome));
		logger.info("Validated installmentpremium is " + expAnualIncome);

	}

	@Given("^Lets Set the input data of\"([^\"]*)\"$")
	public void lets_Set_the_input_data_of(String purchesePrice) throws Throwable {
		this.purchesePrice = purchesePrice;
		logger.info("Purchase price set successfully..");
	}

	@Given("^Lets Set the insured age for GLIP\"([^\"]*)\"$")
	public void lets_Set_the_insured_age_for_GLIP(String ageOfInsured) throws Throwable {
		this.ageOfInsured = ageOfInsured;
		logger.info("Set the insure age successfuly");
	}

	@Given("^Lets Set url for illustration\"([^\"]*)\"")
	public void let_set_the_EndpointUrl(String endPointUrl) {
		this.endPointUrl = endPointUrl;
		logger.info("Set the url successfully..");

	}
	@Given("^Lets Set the header\"([^\"]*)\"$")
	public void lets_Set_the_header(String header) throws Throwable {
         this.header=header;
         logger.info("Set header  value successfully..");
	}
}
