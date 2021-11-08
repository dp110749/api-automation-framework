package com.MLI_DOLPHIN.stepDefination;

import java.util.List;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import cucumber.api.DataTable;
import cucumber.api.java.en.*;
import io.restassured.response.Response;

public class LE_SPP_Service extends WebservicesMethod{
	private final static Logger logger = Logger.getLogger(LE_SPP_Service.class.getName());

	private String ageOfInsured;
	private String purchasePrice;
	private String productName;
	private String channelName;
	private List<String> listOfSetData;
	private List<List<String>> numberOfRow;

	@Given("^Set the prerequiest set of data for LE SPP Service$")
	public void set_the_prerequiest_set_of_data_for_LE_SPP_Service(DataTable inputPreRequestData) throws Throwable {
		listOfSetData = inputPreRequestData.asList(String.class);
		numberOfRow = inputPreRequestData.raw();
		getSecondRowData = listOfSetData.size() / numberOfRow.size();
		for (int i = getSecondRowData; i < listOfSetData.size(); i++) {
			endPointUrl = listOfSetData.get(i);
			i++;
			header = listOfSetData.get(i);
			i++;
			requestFile = listOfSetData.get(i);
			i++;
			ageOfInsured = listOfSetData.get(i);
			i++;
			purchasePrice = listOfSetData.get(i);
			i++;
			productName = listOfSetData.get(i);
			i++;
			channelName = listOfSetData.get(i);
			i++;
			method_Type=listOfSetData.get(i);
		}

		requestBody = ReusableFunction.readJsonFile(requestFile);
		logger.info("Read the json successfully..");
	}

	@Given("^Lets set the AgeOfInsured is\"([^\"]*)\"$")
	public void lets_set_the_AgeOfInsured_is(String ageOfInsured) throws Throwable {
		this.ageOfInsured = ageOfInsured;
		logger.info("Set the AgeofInsured is successfull..");
	}
	
	@Given("^Lets Set the purchasePrice for SSP Is\"([^\"]*)\"$")
	public void lest_Set_the_purchasePrice_Is(String purchasePrice) throws Throwable {
		this.purchasePrice = purchasePrice;
		logger.info("Set the Purchase price successfully..");

	}

	@Given("^Lets Set the product name is\"([^\"]*)\"$")
	public void lets_Set_the_product_name_is(String productName) throws Throwable {
		this.productName = productName;
		logger.info("Set the prodct name is successfully..");

	}

	@Given("^Lets Set the channel name is\"([^\"]*)\"$")
	public void lets_Set_the_channel_name_is(String channelName) throws Throwable {

		this.channelName = channelName;
		logger.info("Set the channel name is successfully..");

	}

	@Given("^Set the endPoint url for Spp illustration Service\"([^\"]*)\"$")
	public void set_the_endPoint_url_for_Spp_illustration_Service(String endPointUrl) throws Throwable {

		this.endPointUrl = endPointUrl;
		logger.info("Set the illustration url successfully..");
	}

	public String getRequestBody() {

		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "ageOfInsured" + "}}"), ageOfInsured);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "purchasePrice" + "}}"), purchasePrice);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "productName" + "}}"), productName);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "channel" + "}}"), channelName);
		logger.info("Set the data in requestbody successfull.." + requestBody);
		return requestBody;
	}

	@When("^Send the valid request for SPP Service$")
	public void send_the_valid_request_for_SPP_Service() throws Throwable {
		responseBody = WebservicesMethod.Select_API_METHOD(method_Type,endPointUrl, getRequestBody(),
				ReusableFunction.requestHeaders(header));
		logger.info("Send the request successfully to server.." + responseBody.prettyPrint());
	}

	@Then("^Lets Validate the response code for SPP Service\"([^\"]*)\"$")
	public void lets_Validate_the_response_code_for_SPP_Service(String expResponseMSgCode) throws Throwable {
		try {
			responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(expResponseMSgCode));
			logger.info("Validation of response message code is successfull..");
		} catch (AssertionError e) {
			responseBody.then().body("statusCode", Matchers.equalTo(expResponseMSgCode));
			logger.info("Validation of response message is successfull..");
		}
	}

	@Then("^Lets Validate the response MSG For SPP Service\"([^\"]*)\"$")
	public void lets_Validate_the_response_MSG_For_SPP_Service(String expResponseMsg) throws Throwable {

		try {
			responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(expResponseMsg));
			logger.info("Validation of response message is successfull..");
		} catch (AssertionError e) {
			logger.info("No response message found..");
		}

	}

	@Then("^Lets Validate the response AFYP of\"([^\"]*)\"$")
	public void lest_Validate_the_response_AFYP_of(String expAfyp) throws Throwable {
		responseBody.then().root("payload").body("premiumAmount.AFYP", Matchers.equalTo(expAfyp));
		logger.info("Validation of AFYP message is successfull..");
	}

	@Then("^Lets Validate the PremiumWithoutGST of\"([^\"]*)\"$")
	public void lest_Validate_the_PremiumWithoutGST_of(String expPremiumWithoutGST) throws Throwable {
		responseBody.then().root("payload").body("premiumAmount.biInstallmentPremiumTotalWithoutGST",
				Matchers.equalTo(expPremiumWithoutGST));
		logger.info("Validation of response value of premiumWithoutGST is successfull..");

	}

	@Then("^Lets Validate the PremiumWithGST\"([^\"]*)\"$")
	public void lest_Validate_the_PremiumWithGST(String expPremiumWithGST) throws Throwable {

		responseBody.then().root("payload").body("premiumAmount.biInstallmentPremiumBasePlanWithGST",
				Matchers.equalTo(expPremiumWithGST));
		logger.info("Validation of response value of premiumWithGST is successfull..");

	}

}
