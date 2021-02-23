package com.MLI_DOLPHIN.stepDefination;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;

import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.specs.SpecificationFactory;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LE_FGEP_Service {
	
	private static final Logger logger = Logger.getLogger(LE_FGEP_Service.class);
	private int getSecondRowData;
	private String endPointUrl;
	private String header;
	private String requestFile;
	private String requestBody;
	private Response responseBody;
	private String actualResponseCode;
	private String msgCode;
	private String responseMsg;
	private String inputTestData;
	private String oparationType;
	
	@Given("^Set the preRequest test data for FGEP product$")
	public void SetPreRquestData(DataTable inputPreRequestData) throws IOException{		
		List<String> listOfData =inputPreRequestData.asList(String.class);
		List<List<String>> numOfRow=inputPreRequestData.raw();
		getSecondRowData=listOfData.size()/numOfRow.size();
		for(int i=getSecondRowData;i<listOfData.size();i++){
			endPointUrl=listOfData.get(i);
			i++;
			header=listOfData.get(i);
			i++;
            requestFile=listOfData.get(i);			
		}
		requestBody = ReusableFunction.readJsonFile(requestFile);
	}
	
	@Given("^I want to set the illustration endPointUrl\"([^\"]*)\"$")
	public void i_want_to_set_the_illustration_endPointUrl(String url) throws Throwable {
	    endPointUrl=url;
	    logger.info("Set the url successfully..");
	    
	}
	
	@When("^I want to send the request for FGEP$")
	public void i_want_to_send_the_request_for_FGEP() throws Throwable {

		responseBody =WebservicesMethod.POST_METHOD(endPointUrl, requestBody, ReusableFunction.requestHeaders(header));
		logger.info("Response data is :"+responseBody.prettyPrint());
	}

	@Then("^I want to validate the responseCode for FGEP\"([^\"]*)\"$")
	public void i_want_to_validate_the_responseCode_for_FGEP(String expResponseCode) throws Throwable {
		actualResponseCode=String.valueOf(responseBody.getStatusCode());
		Assert.assertEquals(actualResponseCode, expResponseCode);
		logger.info("Validation of response code is successfull..");

	}

	@Then("^I want to validate the responseMSg for FGEP\"([^\"]*)\"$")
	public void i_want_to_validate_the_responseMSg_for_FGEP(String responseMsg) throws Throwable {
//		JsonPath path =new JsonPath(responseBody.asString());
//		String S=path.getString(path);
//		System.out.println("============="+path.getString("msgInfo.msg"));
		if(responseMsg.equals("Success")){
		responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(responseMsg));
		logger.info("validation of response message is successfull..");
		}else if(responseMsg.equals("Failure")){
			responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(responseMsg));
			logger.info("validation of response message is successfull..");
	
		}else if(responseMsg.equals("Bad Request")){
//			responseBody.then().body("error", Matchers.equalTo(responseMsg));
			responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(responseMsg));
			logger.info("validation of response message is successfull..");

		}

	}

	@Then("^I want to validate the responseMsgCode for FGEP\"([^\"]*)\"$")
	public void i_want_to_validate_the_responseMsgCode_for_FGEP(String responseMsgCode) throws Throwable {
		responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(responseMsgCode));
		logger.info("validation of response message code is successfull..");

	}

	@Then("^I want to validate the sumAssured\"([^\"]*)\"$")
	public void i_want_to_validate_the_sumAssured(String expSumAssured) throws Throwable {
       responseBody.then().root("payload").body("premiumAmount.sumAssusred", Matchers.equalTo(expSumAssured));
       logger.info("Validation of SumAssuerd is Successfull..");
    
	}

	@Then("^I want to validate the AFYP\"([^\"]*)\"$")
	public void i_want_to_validate_the_AFYP(String expAFYP) throws Throwable {
	       responseBody.then().root("payload").body("premiumAmount.AFYP", Matchers.equalTo(expAFYP));
	       logger.info("Validation of SumAssuerd is Successfull..");

	}
	
	@Then("^I want to validate the response time for FGEP$")
	public void i_want_to_validate_the_response_time_for_FGEP() throws Throwable {
		SpecificationFactory.getGenericResponseSpec();
		logger.info("Validation of response is successfull..");
	}
	@Then("^I want to validate Illustration generated or not$")
	public void i_want_to_validate_Illustration_generated_or_not() throws Throwable {
		responseBody.then().root("payload").body("illustrationPdfBase64", Matchers.notNullValue());
		logger.info("Validation of illustration out successfull..");

	}
	
	@Given("^I want to test data in request for FGEP$")
	public void i_want_to_test_data_in_request_for_FGEP(DataTable SetOfTestData) throws Throwable {
		List<String> listOfrequestData =SetOfTestData.asList(String.class);
		List<List<String>> numOfRow =SetOfTestData.raw();
		int getSecondRow=listOfrequestData.size()/numOfRow.size();
		for(int i=getSecondRow;i<listOfrequestData.size();i++){
			inputTestData =listOfrequestData.get(i);
			i++;
			oparationType =listOfrequestData.get(i);
		}
		requestBody =ReusableFunction.getSpecificRequest(requestBody, inputTestData, oparationType);

	}
	@Then("^I want to validate the error responseMSg for FGEP\"([^\"]*)\"$")
	public void i_want_to_validate_the_error_responseMSg_for_FGEP(String responseErrorMsg) throws Throwable {
		responseBody.then().body("errorMessage", Matchers.equalTo(responseErrorMsg));
		logger.info("validation of response error message is successfull..");		
	}
	@Then("^I want to validate the fofbidden message\"([^\"]*)\"$")
	public void i_want_to_validate_the_fofbidden_message(String responseErrorMsg) throws Throwable {
		responseBody.then().body("message", Matchers.equalTo(responseErrorMsg));
		logger.info("validation of response forbidden message is successfull..");		

	}
	@Given("^I want to set the key\"([^\"]*)\" and Value\"([^\"]*)\" for FGEP$")
	public void SetCorrelationID(String inputKey, String inputValue) throws Throwable {
      JSONObject jsonObjectRequest =ReusableFunction.createJSONObject(requestBody);
      jsonObjectRequest =ReusableFunction.replacekeyInJSONObject(jsonObjectRequest, inputKey, inputValue);
      requestBody=jsonObjectRequest.toString();
	}


 }
