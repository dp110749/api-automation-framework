package com.MLI_DOLPHIN.stepDefination;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import junit.framework.Assert;

public class LE_SWP_Service extends WebservicesMethod{
	
	private final static Logger logger = Logger.getLogger(LE_SWP_Service.class.getName());
//	private int getSecondrowData;
//	private String endPointUlr;
//	private String header;
//	private String requestFile;
	private String expResponseStatusCode;
	private String actResponseStatusCode;
	private String expResponseMsgCode;
	private String expResponseMessage;
	private String expATPvalue;
//	private String requestBody;
//	private Response responseBody;
	private String  correlationId;
	private String  comittedPremium;
	private String  productName;
	
	
	@Given("^Set the pre request test data for SWP$")
	public void set_the_pre_request_test_data_for_SWP(DataTable preSetofData) throws Throwable {
     List<String> listPreSetData =preSetofData.asList(String.class);
     List<List<String>> numOfRows =preSetofData.raw();
     getSecondRowData=listPreSetData.size()/numOfRows.size();
     for(int i =getSecondRowData;i<listPreSetData.size();i++){
    	 endPointUrl=listPreSetData.get(i);
    	 i++;
    	header= listPreSetData.get(i);
    	i++;
    	requestFile=listPreSetData.get(i);
    	i++;
    	correlationId=listPreSetData.get(i);
    	i++;
    	comittedPremium=listPreSetData.get(i);
    	i++;
    	productName=listPreSetData.get(i);
    	i++;
    	method_Type=listPreSetData.get(i);
    	
     }
 }
	public String  set_input_data_in_Request(){
		try {
			requestBody=ReusableFunction.readJsonFile(requestFile);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"X-Correlation-ID"+"}}"),correlationId );
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"committedPremium"+"}}"),comittedPremium );
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"productName"+"}}"),productName );
        logger.info("All data set into request successfully..");
		return requestBody;
	}

	@Given("^To set the expected data$")
	public void to_set_the_expected_data(DataTable expSetofData) throws Throwable {
	     List<String> listPreSetData =expSetofData.asList(String.class);
	     List<List<String>> numOfRows =expSetofData.raw();
	     getSecondRowData=listPreSetData.size()/numOfRows.size();
	     for(int i =getSecondRowData;i<listPreSetData.size();i++){
	    	 expResponseStatusCode=listPreSetData.get(i);
	    	 i++;
	    	expResponseMsgCode= listPreSetData.get(i);
	    	i++;
	    	expResponseMessage=listPreSetData.get(i);
	    	i++;
	    	expATPvalue=listPreSetData.get(i);
	     }
			logger.info("Expected data set Successfully...");
	 }

	@When("^Send the POST request for SWP$")
	public void send_the_POST_request_for_SWP() throws Throwable {
		
		responseBody = WebservicesMethod.Select_API_METHOD(method_Type,endPointUrl,set_input_data_in_Request(),
				ReusableFunction.requestHeaders(header));
		logger.info("Response Body is ::" + responseBody.prettyPrint());

	}

	@Then("^Lets Validate the response code for SWP$")
	public void lets_Validate_the_response_code_for_SWP() throws Throwable {
		actResponseStatusCode = String.valueOf(responseBody.getStatusCode());
		Assert.assertEquals(expResponseStatusCode, actResponseStatusCode);
		logger.info("Varification of status code is successfully pass:");

	}

	@Then("^Lets Validate response Msg Code and message for SWP$")
	public void lets_Validate_response_Msg_Code_and_message_for_SWP() throws Throwable {
     if(expResponseMsgCode.equals("200")){
    	 responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(expResponseMsgCode))
    	 .and()
    	 .body("msg", Matchers.equalToIgnoringCase(expResponseMessage));
    	 logger.info("Validation of response message code is "+ expResponseMsgCode + "and message is "+expResponseMessage);
    	 
     }else if(expResponseMsgCode.equals("500")){
       	 responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(expResponseMsgCode))
    	 .and()
    	 .body("msg", Matchers.equalToIgnoringCase(expResponseMessage));
       	 logger.info("Validation of response message code is "+ expResponseMsgCode + "and message is "+expResponseMessage);

    	 
     }else if(expResponseMsgCode.equals("400")){
       	 responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(expResponseMsgCode))
    	 .and()
    	 .body("msg", Matchers.equalToIgnoringCase(expResponseMessage));
       	 logger.info("Validation of response message code is "+ expResponseMsgCode + "and message is "+expResponseMessage);

    	 
     }else{
       	 logger.info("SWP API not response any data..");
       }
}

	@Then("^Lets validate calculated AFYP value$")
	public void lets_validate_calculated_ATP_value() throws Throwable {
     	 responseBody.then().root("payload.premiumAmount").body("AFYP", Matchers.equalTo(expATPvalue));
         logger.info("Validation of AFYP is "+expATPvalue);
	}
	
	@Given("^Set the endPoint url\"([^\"]*)\"$")
	public void set_the_endPoint_url(String urlEndPoint) throws Throwable {
    endPointUrl=urlEndPoint;
    logger.info("End point set successfully...");
	}

	@Then("^Lets validate illustration is generated or not$")
	public void lets_validate_illustration_is_generated_or_not() throws Throwable {
    responseBody.then().root("payload").body("illustrationPdfBase64", Matchers.notNullValue());
    logger.info("validation of illustration value is not..");
	}
	@Given("^Set the premium in request\"([^\"]*)\"$")
	public void set_the_input_data(String inputData) throws Throwable {		
    comittedPremium=inputData;
    logger.info("Committed premium set successfully in request");
	}
	@Given("^Set the correlationID\"([^\"]*)\"$")
	public void set_the_correlationID(String inputCorrelationId) throws Throwable {
		correlationId=inputCorrelationId;
		logger.info("Correlation id set successfully..");
	}
	@Given("^Set the header\"([^\"]*)\"$")
	public void set_the_header(String inputHeader) throws Throwable {
    header=inputHeader;
    logger.info("user set header as null..");
	}

}
