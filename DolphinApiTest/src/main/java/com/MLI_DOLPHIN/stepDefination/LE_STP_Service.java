package com.MLI_DOLPHIN.stepDefination;

import java.util.List;

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
import io.restassured.response.Response;

public class LE_STP_Service {
	private final static Logger logger = Logger.getLogger(LE_STP_Service.class.getName());
	private String endPointUrl;
	private String headers;
	private String requestFile;
	private String requestBody;
	private Response responseBody;
	private String expResponseCode;
	private String expResponseMSgCode;
	private String expResponseMessage;
	private String expOutPutData;
	private String expAFYP;
	private String testData;
	private String oparationType;
	private List<String> listOfSetData ;
	private List<List<String>> numOfRow;
	private int getSecondRow;

	
	@Given("^Set the pre request test data for STP$")
	public void set_the_pre_request_test_data_for_STP(DataTable preRequestData) throws Throwable {
		listOfSetData=preRequestData.asList(String.class);
		numOfRow=preRequestData.raw();
		getSecondRow=listOfSetData.size()/numOfRow.size();
		for(int i=getSecondRow;i<listOfSetData.size();i++){
			endPointUrl=listOfSetData.get(i);
			i++;
			headers=listOfSetData.get(i);
			i++;
			requestFile=listOfSetData.get(i);
		}
          requestBody=ReusableFunction.readJsonFile(requestFile);
	}

	@Given("^I want to set output data for STP$")
	public void i_want_to_set_output_data_for_STP(DataTable expOutPutListData) throws Throwable {

		listOfSetData=expOutPutListData.asList(String.class);
		numOfRow=expOutPutListData.raw();
		getSecondRow=listOfSetData.size()/numOfRow.size();
		for(int i= getSecondRow;i<listOfSetData.size();i++){
			expResponseCode=listOfSetData.get(i);
			i++;
			expResponseMSgCode=listOfSetData.get(i);
			i++;
			expResponseMessage=listOfSetData.get(i);
			i++;
			expOutPutData=listOfSetData.get(i);
		  }		
	}
	
	@Given("^I want to set multiple set of data for STP$")
	public void i_want_to_set_multiple_set_of_data_for_STP(DataTable multipleSetOfData) throws Throwable {
    listOfSetData= multipleSetOfData.asList(String.class);
    numOfRow=multipleSetOfData.raw();
    getSecondRow=listOfSetData.size()/numOfRow.size();
    for(int i=getSecondRow;i<listOfSetData.size();i++){
    	testData=listOfSetData.get(i);
    	i++;
    	oparationType=listOfSetData.get(i);
    	i++;
    	expResponseMSgCode=listOfSetData.get(i);
    	i++;
    	expResponseMessage=listOfSetData.get(i);
    	i++;
    	expOutPutData=listOfSetData.get(i);
    	i++;
    	expAFYP=listOfSetData.get(i);
    	
    	requestBody =ReusableFunction.getSpecificRequest(requestBody, testData, oparationType);
    	responseBody=WebservicesMethod.POST_METHOD(endPointUrl, requestBody, ReusableFunction.requestHeaders(headers));
		logger.info("Response Body is :"+responseBody.prettyPrint());
		if(expResponseMessage.equalsIgnoreCase("Successful")){
		    responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(expResponseMSgCode))
		    .and()
		    .body("msgCode", Matchers.equalTo(expResponseMessage));		    
		    responseBody.then().root("payload").body("premiumAmount.sumAssusred", Matchers.equalTo(expOutPutData))
		    .and()
		    .body("premiumAmount.AFYP", Matchers.equalTo(expAFYP));
		    logger.info("Validation of successfull response with valid data for STP");

		}else if(expResponseMessage.equalsIgnoreCase("Failed")){
		    responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(expResponseMSgCode))
		    .and()
		    .body("msg", Matchers.equalTo(expResponseMessage));
            logger.info("Validation of message Code and message is successful..");
		    
		}else if (expResponseMessage.equalsIgnoreCase("Bad Request")){
		    responseBody.then().body("error", Matchers.equalTo(expResponseMessage));
            logger.info("Validation of response for bad request is successful");
		 }else{
			logger.info("Api not returning any response..."); 
		 }
       }
		
	}
	
	@Given("^I want to set the endpoint url for illustration\"([^\"]*)\"$")
	public void i_want_to_set_the_endpoint_url_for_illustration(String url) throws Throwable {
		endPointUrl=url;
		logger.info("End point url set successfully for illustration.");
	}

	@Given("^I want to set multiple set of data for STP to generate illustration$")
	public void i_want_to_set_multiple_set_of_data_for_STP_to_generate_illustration(DataTable inputTestData) throws Throwable {
	    listOfSetData= inputTestData.asList(String.class);
	    numOfRow=inputTestData.raw();
	    getSecondRow=listOfSetData.size()/numOfRow.size();
	    for(int i=getSecondRow;i<listOfSetData.size();i++){
	    	testData=listOfSetData.get(i);
	    	i++;
	    	oparationType=listOfSetData.get(i);
	    	i++;
	    	expResponseMSgCode=listOfSetData.get(i);
	    	i++;
	    	expResponseMessage=listOfSetData.get(i);
	    	requestBody =ReusableFunction.getSpecificRequest(requestBody, testData, oparationType);
	    	responseBody=WebservicesMethod.POST_METHOD(endPointUrl, requestBody, ReusableFunction.requestHeaders(headers));
			logger.info("Response Body is :"+responseBody.prettyPrint());
			if(expResponseMessage.equalsIgnoreCase("Successful")){
			    responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(expResponseMSgCode));
			    
			}else if(expResponseMessage.equalsIgnoreCase("Failure")){
			    responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(expResponseMSgCode));
			    logger.info("Api getting fail.");
	       }
	    }
	}
		
	@Given("^I want to set the header for request\"([^\"]*)\"$")
	public void i_want_to_set_the_header_for_request(String inputHeader) throws Throwable {
		headers=inputHeader;
		logger.info("input header set the successfully..");

	}
	@When("^I want to send the request for STP$")
	public void i_want_to_send_the_request_for_STP() throws Throwable {
		responseBody=WebservicesMethod.POST_METHOD(endPointUrl, requestBody, ReusableFunction.requestHeaders(headers));
		logger.info("Response Body is :"+responseBody.prettyPrint());
	}

	@Then("^I want to validate response code for STP$")
	public void i_want_to_validate_response_code_for_STP() throws Throwable {
     Assert.assertEquals(String.valueOf(responseBody.getStatusCode()), expResponseCode);
     logger.info("Validation of expected response code is "+responseBody.getStatusCode()+" Successfull");
		
	}

	@Then("^I want to validate response MsgCode for STP$")
	public void i_want_to_validate_response_MsgCode_for_STP() throws Throwable {
    responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(expResponseMSgCode));
    logger.info("Validation of expected response MsgCode is :"+ expResponseMSgCode +" Successfull");
	}

	@Then("^I want to validate response Message for STP$")
	public void i_want_to_validate_response_Message_for_STP() throws Throwable {
		responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(expResponseMSgCode));
		logger.info("Validation of expected response Msg is :" + expResponseMessage + " Successfull");

	}

	@Then("^I want to validate response output data for STP$")
	public void i_want_to_validate_response_output_data_for_STP() throws Throwable {
		responseBody.then().root("payload").body("premiumAmount.sumAssusred", Matchers.equalTo(expOutPutData));
		logger.info("Validation of response sumAassured is" +expOutPutData+ "successfull.");

	}

	@Then("^I want to validate the response time for STP$")
	public void i_want_to_validate_the_response_time_for_STP() throws Throwable {
     SpecificationFactory.getGenericResponseSpec();
     logger.info("Validation of response AppId and Response time is successful.");
	}
	
	@Then("^I want to validate the forbidden message$")
	public void i_want_to_validate_the_forbidden_message() throws Throwable {
		responseBody.then().body("message", Matchers.equalTo(expResponseMessage));
		logger.info("Validation of response message is successfull.");
	}
	
	@Given("^To Set the correlatinKey\"([^\"]*)\" and value\"([^\"]*)\" for STP$")
	public void to_Set_the_correlatinKey_and_value_for_STP(String requestKey, String requestValue) throws Throwable {

		JSONObject convertedJsonRequestIntoJsonObject =ReusableFunction.createJSONObject(requestBody);
		convertedJsonRequestIntoJsonObject=ReusableFunction.replacekeyInJSONObject(convertedJsonRequestIntoJsonObject, requestKey, requestValue);
		requestBody =String.valueOf(convertedJsonRequestIntoJsonObject);
		logger.info("Set the test data in the request..");

	}

	@Then("^I want to validate the bad request message$")
	public void i_want_to_validate_the_bad_request_message() throws Throwable {
      responseBody.then().body("error", Matchers.equalTo(expResponseMessage));
      logger.info("Validation of response maessage is" +expResponseMessage+"Successful..");
		
	}



}
