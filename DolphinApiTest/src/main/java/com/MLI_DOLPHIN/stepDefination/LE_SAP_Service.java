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

public class LE_SAP_Service extends WebservicesMethod{
	
	private final static Logger logger = Logger.getLogger(LE_SAP_Service.class.getName());
	private String responseCode;
	private String responseMsgCode;
	private String responseMessge;
	private String testData;
	private String oparationType;
	private String outPutSumAssured;
	private String inputKey;
	private String inputValue;
	
	@Given("^Set pre set of test data for SAP$")
	public void set_pre_set_of_test_data_for_SAP(DataTable preSetOfData) throws Throwable {
	List<String> listOfData =preSetOfData.asList(String.class);
	List<List<String>>numbOfRow =preSetOfData.raw();
	getSecondRowData =listOfData.size()/numbOfRow.size();
	for (int i=getSecondRowData;i<listOfData.size();i++){
		endPointUrl=listOfData.get(i);
		i++;
		header=listOfData.get(i);
		i++;
		requestFile=listOfData.get(i);
		i++;
		method_Type=listOfData.get(i);
     	}
	   requestBody =ReusableFunction.readJsonFile(requestFile);
	}
	
	@Given("^I want to set url for Illustration\"([^\"]*)\"$")
	public void i_want_to_set_url_for_Illustration(String illustrationUrl) throws Throwable {
       endPointUrl=illustrationUrl;
       logger.info("illustration set successfully..");
	}

	@When("^I want send the request for SAP$")
	public void i_want_send_the_request_for_SAP() throws Throwable {
    responseBody=WebservicesMethod.Select_API_METHOD(method_Type,endPointUrl, requestBody, ReusableFunction.requestHeaders(header));
	logger.info("Request Send Successfully.."+responseBody.prettyPrint());
	}

	@Then("^I want to validate the responseCode\"([^\"]*)\"$")
	public void i_want_to_validate_the_responseCode(String responseStatusCode) throws Throwable {
     Assert.assertEquals(responseStatusCode, String.valueOf(responseBody.getStatusCode()));
	logger.info("Validation of response status code is successfull"+responseBody.getStatusCode());
	} 

	@Then("^I want to validate the responseMsgCode\"([^\"]*)\"$")
	public void i_want_to_validate_the_responseMsgCode(String responseMsgCode) throws Throwable {
    responseBody.then().root("msgInfo").body("msgCode", Is.is(responseMsgCode));
    logger.info("Validation of response Msg Code is successfull");
	}

	@Then("^I want to validate the responseMessage\"([^\"]*)\"$")
	public void i_want_to_validate_the_responseMessage(String expResponseMessage) throws Throwable {
		if(expResponseMessage.equals("Success")||expResponseMessage.contains("Fail")||expResponseMessage.contains("Bad Request")){
		responseBody.then().root("msgInfo").body("msg", Is.is(expResponseMessage));
	    logger.info("Validation of response Msg is successfull");
		}else if(expResponseMessage.equalsIgnoreCase("Bad Request")){
  			responseBody.then().body("error", Matchers.equalTo(expResponseMessage));
			logger.info("Validation of bas request successfull..");

		}else{
			logger.info("No Response received from SAP API...");
		}
	}

	@Then("^I want to validate the calaculated Sumassured\"([^\"]*)\"$")
	public void i_want_to_validate_the_calaculated_Sumassured(String expSumAssured) throws Throwable {
		responseBody.then().root("payload").body("premiumAmount.sumAssusred", Is.is(expSumAssured));
	    logger.info("Validation of response Msg is successfull");

	}
	
	@Then("^I want to validate illustration is generated or not$")
	public void i_want_to_validate_illustration_is_generated_or_not() throws Throwable {
      responseBody.then().root("payload").body("illustrationPdfBase64", Matchers.notNullValue());
      logger.info("Validation of illustration is successfull..");
	}

	@Given("^pass the set of test data$")
	public void pass_the_set_of_test_data(DataTable setOfTestData) throws Throwable {
		List<String> listOfData= setOfTestData.asList(String.class);
		List<List<String>> numOfRow=setOfTestData.raw();
		getSecondRowData=listOfData.size()/numOfRow.size();
		for(int i=getSecondRowData;i<listOfData.size();i++){
			testData=listOfData.get(i);
			i++;
			oparationType=listOfData.get(i);
			i++;
			responseCode=listOfData.get(i);
			i++;
			responseMsgCode=listOfData.get(i);
			i++;
			responseMessge=listOfData.get(i);
			i++;
			outPutSumAssured=listOfData.get(i);
			
			requestBody=ReusableFunction.getSpecificRequest(requestBody, testData, oparationType);
			Thread.sleep(1000  );
			responseBody=WebservicesMethod.Select_API_METHOD(method_Type,endPointUrl, requestBody, ReusableFunction.requestHeaders(header));
			logger.info("Response body is ::"+responseBody.prettyPrint());
			if(responseMessge.equals("Success")){
				Assert.assertEquals(responseCode, String.valueOf(responseBody.getStatusCode()));
				logger.info("Validation of response code is successfull..");
			    responseBody.then().root("msgInfo").body("msgCode", Is.is(responseMsgCode))
			    .and().body("msg", Is.is(responseMessge));
			    logger.info("Validation of response Msg Code is successfull");
				responseBody.then().root("payload").body("premiumAmount.sumAssusred", Is.is(outPutSumAssured))
				.and()
				.body("premiumAmount.AFYP", Matchers.notNullValue());
			    logger.info("Validation of response Msg is successfull");

			}else if(responseMessge.equals("Failure")){
				Assert.assertEquals(responseCode, String.valueOf(responseBody.getStatusCode()));
				logger.info("Validation of response code is successfull..");
			    responseBody.then().root("msgInfo").body("msgCode", Is.is(responseMsgCode))
			    .and().body("msg", Is.is(responseMessge));
			    logger.info("Validation of response Msg Code is successfull");
				
			}else if(responseMessge.equals("Bad Request")){
				responseBody.then().body("error", Is.is(responseMessge));
				logger.info("Validation of bas request successfull..");
			}
		}
	}
	
	@Given("^To Set the correlatinKey\"([^\"]*)\" and value\"([^\"]*)\" in the request for SAP$")
	public void to_Set_the_correlatinKey_and_value_in_the_request_for_SAP(String correlationKey, String correlationValue) throws Throwable {
		JSONObject convertedJsonRequestIntoJsonObject =ReusableFunction.createJSONObject(requestBody);
		convertedJsonRequestIntoJsonObject=ReusableFunction.replacekeyInJSONObject(convertedJsonRequestIntoJsonObject, correlationKey, correlationValue);
		requestBody =String.valueOf(convertedJsonRequestIntoJsonObject);
		
	}
	
	@Given("^Set the test data for SAP\"([^\"]*)\"$")
	public void set_the_test_data_for_SAP(String inputTestData) throws Throwable {

		if(inputTestData.contains("x-api-key:")){
			header=inputTestData;
		}else if(inputTestData.contains("developer")){
			endPointUrl=inputTestData;
		}
	}

}
