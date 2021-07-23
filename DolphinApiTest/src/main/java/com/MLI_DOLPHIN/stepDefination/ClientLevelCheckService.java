package com.MLI_DOLPHIN.stepDefination;

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

public class ClientLevelCheckService {
	private final static Logger logger = Logger.getLogger(ClientLevelCheckService.class.getName());
	private String responseCode;
	private String responseMsgCode;
	private String responseMsg;
	private int getSecondRow;
	private String endPointUrl;
	private String header;
	private String requestFile;
	private String correlationId;
	private String insuredAge;
	private String insuredClientId;
	private String currentBaseProductCode;
	private String currentBaseSumAssured;
	private String currentRiderProductCode;
	private String currentRiderSumAssured;
	private String requestBody;
	private Response responseBody;
	
	@Given("^To set the pre request data for client level check$")
	public void to_set_the_pre_request_data_for_client_level_check(DataTable preInputData) throws Throwable {
		List<String> preListData =preInputData.asList(String.class);
		List<List<String>> numberOfRow =preInputData.raw();
		getSecondRow=preListData.size()/numberOfRow.size();
		for(int i=getSecondRow;i<preListData.size();i++){
			endPointUrl=preListData.get(i);
			i++;
			header =preListData.get(i);
			i++;
			requestFile=preListData.get(i);
			i++;
			correlationId=preListData.get(i);
			i++;
			insuredAge=preListData.get(i);
			i++;
			insuredClientId=preListData.get(i);
			i++;
			currentBaseProductCode=preListData.get(i);
			i++;
			currentBaseSumAssured=preListData.get(i);
			i++;
			currentRiderProductCode=preListData.get(i);
			i++;
			currentRiderSumAssured=preListData.get(i);
			
		}
		requestBody=ReusableFunction.readJsonFile(requestFile);
		logger.info("Set the data successfully in variable..");
	}
	
	public String getRequestBody(){
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "X-Correlation-ID" + "}}"), correlationId);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "insuredAge" + "}}"), insuredAge);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "insuredClientId" + "}}"), insuredClientId);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "currentBaseProductCode" + "}}"),currentBaseProductCode);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "currentBaseSumAssured" + "}}"),currentBaseSumAssured);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "currentRiderProduct1" + "}}"),currentRiderProductCode);
		requestBody = requestBody.replaceAll(Pattern.quote("{{" + "currentRiderSumAssured1" + "}}"),currentRiderSumAssured);

		return requestBody;
	}

	@When("^Send the request for client level check$")
	public void send_the_request_for_client_level_check() throws Throwable {
	responseBody=WebservicesMethod.POST_METHOD(endPointUrl, getRequestBody(), ReusableFunction.requestHeaders(header));
	logger.info("User send the response data is  "+responseBody.prettyPrint());
	}

	@Then("^Validate the response code\"([^\"]*)\"$")
	public void validate_the_response_code(String inputResponseCode) throws Throwable {
      Assert.assertEquals(String.valueOf(responseBody.getStatusCode()), inputResponseCode);
	logger.info("Validation of response code is successfull...");
	
	}

	@Then("^Validat the response message code\"([^\"]*)\"$")
	public void validat_the_response_message_code(String inputmsgCode) throws Throwable {

	responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(inputmsgCode));
	logger.info("Validation of response message code is successful..");
	}

	@Then("^validate the response message\"([^\"]*)\"$")
	public void validate_the_response_message(String inputRespMsg) throws Throwable {
    responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(inputRespMsg));
    logger.info("Validation of response message is successful..");
	
	}
	@Given("^Set the sumassured data for Rider\"([^\"]*)\"$")
	public void set_the_sumassured_data_for_Rider(String inputRiderSumAssured) throws Throwable {
		currentRiderSumAssured = inputRiderSumAssured;
		logger.info("set the rider sumAssured ...");
	}


}
