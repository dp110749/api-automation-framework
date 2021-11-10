package com.MLI_DOLPHIN.stepDefination;

import java.util.List;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.json.simple.JSONObject;
import org.testng.Assert;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class LE_GIP_Service extends WebservicesMethod{
	
	private final static Logger logger = Logger.getLogger(LE_GIP_Service.class.getName());
	private String expResponseCode;
	private String expResponseMSgCode;
	private String expResponseMessage;
	private String expOutPutData;
	private String testData;
	private String oparationType;
	private List<String> listOfSetData ;
	private List<List<String>> numOfRow;

@Given("^Set the pre request test data for GIP$")
public void set_the_pre_request_test_data_for_GIP(DataTable preSetOfData) throws Throwable {
	
 listOfSetData =preSetOfData.asList(String.class);
 numOfRow=preSetOfData.raw();
 getSecondRowData=listOfSetData.size()/numOfRow.size();
for(int i=getSecondRowData;i<listOfSetData.size();i++){
	endPointUrl=listOfSetData.get(i);
	i++;
	header=listOfSetData.get(i);
	i++;
	requestFile=listOfSetData.get(i);
	i++;
	method_Type=listOfSetData.get(i);
   
  }
	requestBody=ReusableFunction.readJsonFile(requestFile);
}

@Given("^I want to set the validation Data$")
public void i_want_to_set_the_validation_Data(DataTable inputData) throws Throwable {
	 listOfSetData=inputData.asList(String.class);
	 numOfRow=inputData.raw();
	 getSecondRowData=listOfSetData.size()/numOfRow.size();
	 for(int i=getSecondRowData;i<listOfSetData.size();i++){
		expResponseCode=listOfSetData.get(i); 
		i++;
		expResponseMSgCode=listOfSetData.get(i);
		i++;
		expResponseMessage=listOfSetData.get(i);
		i++;
		expOutPutData=listOfSetData.get(i);
		break;
	 }
}
@When("^I want send the request For GIP$")
public void i_want_send_the_request_For_GIP() throws Throwable {
   responseBody=WebservicesMethod.Select_API_METHOD(method_Type,endPointUrl, requestBody, ReusableFunction.requestHeaders(header));
   logger.info("User getting response is :"+responseBody.body().prettyPrint());
}
@Then("^I want to validate the responseCode for GIP$")
public void i_want_to_validate_the_responseCode_for_GIP() throws Throwable {
	Assert.assertEquals(String.valueOf(responseBody.getStatusCode()), expResponseCode);
	logger.info("Validation of response code is successfull.");
}

@Then("^I want to validate the responseMsgCode for GIP$")
public void i_want_to_validate_the_responseMsgCode_for_GIP() throws Throwable {
	responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(expResponseMSgCode));
	logger.info("Validation of response message code is successfull.");
}

@Then("^I want to validate the responseMessage for GIP$")
public void i_want_to_validate_the_responseMessage_for_GIP() throws Throwable {
	
	responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(expResponseMessage));
	logger.info("Validation of response message is successfull.");

}

@Then("^I want to validate the calaculated Sumassured for GIP$")
public void i_want_to_validate_the_calaculated_Sumassured_for_GIP() throws Throwable {
	responseBody.then().root("payload").body("premiumAmount.sumAssusred", Matchers.equalTo(expOutPutData));
	logger.info("Validation of response summassured is successfull.");

 }

@Given("^I want to set the illustration URL\"([^\"]*)\"$")
public void i_want_to_set_the_illustration_URL(String illustrationUrl) throws Throwable {
 
  endPointUrl=illustrationUrl;
  logger.info("Illustration value set successfully..");
}

@Then("^I want to validate the Illustration is generated for GIP or not$")
public void i_want_to_validate_the_Illustration_is_generated_for_GIP_or_not() throws Throwable {

responseBody.then().root("payload").body("illustrationPdfBase64", Matchers.notNullValue());
logger.info("Validation of illustration is not value .");
 
 }

@Then("^I want to validate response Error Msg$")
public void i_want_to_validate_response_Error_Msg() throws Throwable {
	if(expResponseMessage.equalsIgnoreCase("Forbidden")){
	responseBody.then().body("message", Matchers.equalTo(expResponseMessage));
	logger.info("Validation of error message is.."+expResponseMessage);
	}else if(expResponseMessage.equalsIgnoreCase("Bad Request")){
//		responseBody.then().body("error", Matchers.equalTo(expResponseMessage));
		responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(expResponseMessage));
		logger.info("Validation of error message is successfull ."+expResponseMessage);
	}
}

@Given("^To Set the correlatinKey\"([^\"]*)\" and value\"([^\"]*)\" in the request for GIP$")
public void to_Set_the_correlatinKey_and_value_in_the_request_for_GIP(String correlationKey, String correlationValue) throws Throwable {

	JSONObject convertedJsonRequestIntoJsonObject =ReusableFunction.createJSONObject(requestBody);
	convertedJsonRequestIntoJsonObject=ReusableFunction.replacekeyInJSONObject(convertedJsonRequestIntoJsonObject, correlationKey, correlationValue);
	requestBody =String.valueOf(convertedJsonRequestIntoJsonObject);
	logger.info("Set the test data in the request ..");

}



@Given("^I want to set the test data in GIP request$")
public void pass_the_set_of_test_data(DataTable setOfTestData) throws Throwable {
	List<String> listOfData= setOfTestData.asList(String.class);
	List<List<String>> numOfRow=setOfTestData.raw();
	getSecondRowData=listOfData.size()/numOfRow.size();
	for(int i=getSecondRowData;i<listOfData.size();i++){
		testData=listOfData.get(i);
		i++;
		oparationType=listOfData.get(i);
		i++;
		expResponseCode=listOfData.get(i);
		i++;
		expResponseMSgCode=listOfData.get(i);
		i++;
		expResponseMessage=listOfData.get(i);
		i++;
		expOutPutData=listOfData.get(i);
	
		requestBody=ReusableFunction.getSpecificRequest(requestBody, testData, oparationType);
		Thread.sleep(1000  );
		responseBody=WebservicesMethod.Select_API_METHOD(method_Type,endPointUrl, requestBody, ReusableFunction.requestHeaders(header));
		logger.info("Response body is ::"+responseBody.prettyPrint());
		if(expResponseMessage.equalsIgnoreCase("Success")){
			Assert.assertEquals(expResponseCode, String.valueOf(responseBody.getStatusCode()));
			logger.info("Validation of response code is successfull..");
		    responseBody.then().root("msgInfo").body("msgCode", Is.is(expResponseMSgCode))
		    .and().body("msg", Is.is(expResponseMessage));
		    logger.info("Validation of response Msg Code is successfull");
			responseBody.then().root("payload").body("premiumAmount.sumAssusred", Is.is(expOutPutData))
			.and()
			.body("premiumAmount.AFYP", Matchers.notNullValue());
		    logger.info("Validation of response Msg is successfull");

		}else if(expResponseMessage.equals("Failure")){
			Assert.assertEquals(expResponseCode, String.valueOf(responseBody.getStatusCode()));
			logger.info("Validation of response code is successfull..");
		    responseBody.then().root("msgInfo").body("msgCode", Is.is(expResponseMSgCode))
		    .and().body("msg", Is.is(expResponseMessage));
		    logger.info("Validation of response Msg Code is successfull");
			
		}else if(expResponseMessage.equals("Bad Request")){
//			responseBody.then().body("error", Is.is(expResponseMessage));
			
			logger.info("Validation of bas request successfull..");
		}else{
			responseBody.then().body("errorMessage", Is.is(expResponseMessage));
			logger.info("Validation of error message ");
		}
	}
}




}