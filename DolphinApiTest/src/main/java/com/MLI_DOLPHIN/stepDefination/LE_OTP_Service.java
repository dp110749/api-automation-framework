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

public class LE_OTP_Service {
	
	private final static Logger logger = Logger.getLogger(LE_OTP_Service.class.getName());
	private String endPointUrl;
	private String header;
	private String requestFile;
	private String correlationId;
	private String insurdGender;
	private String sumAssurd;
	private int getSecondOfData;
	private String requestBody;
	private Response responseBody;
	
	
	@Given("^To set the pre request set of data for OTP$")
	public void to_set_the_pre_request_set_of_data_for_OTP(DataTable preRequistSetOfData) throws Throwable {
      List<String> listOfpreData = preRequistSetOfData.asList(String.class);
      List<List<String>> numberOfRow= preRequistSetOfData.raw();
      getSecondOfData=listOfpreData.size()/numberOfRow.size();
      for(int i=getSecondOfData;i<listOfpreData.size();i++){
      endPointUrl=listOfpreData.get(i);
      i++;
      header =listOfpreData.get(i);
      i++;
      requestFile=listOfpreData.get(i);
      i++;
      correlationId=listOfpreData.get(i);
      i++;
      insurdGender=listOfpreData.get(i);
      i++;
      sumAssurd=listOfpreData.get(i);     
      }
      logger.info("Pre request data set successfully...");     
	}
	public String setData_in_RequestBody(){
		try {
			requestBody=ReusableFunction.readJsonFile(requestFile);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"X-Correlation-ID"+"}}"),correlationId );
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"genderOfInsured"+"}}"),insurdGender );
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"sumAssured"+"}}"),sumAssurd );
        logger.info("All data set into request successfully..");
				
		return requestBody;
	}

	@When("^Lets send the POST request for OTP product$")
	public void lets_send_the_POST_request_for_OTP_product() throws Throwable {
		responseBody=WebservicesMethod.POST_METHOD(endPointUrl, setData_in_RequestBody(), ReusableFunction.requestHeaders(header));
		logger.info("response body of OPT post request :"+responseBody.prettyPrint());
		
	}

	@Then("^Lets validate the reponse code for OTP\"([^\"]*)\"$")
	public void lets_validate_the_reponse_code_for_OTP(String expResponseCode) throws Throwable {
		Assert.assertEquals(String.valueOf(responseBody.getStatusCode()),expResponseCode);
		logger.info("Validation of response code successfull..");
		
	}

	@Then("^Lets validate the response Msg Code for OTP\"([^\"]*)\"$")
	public void lets_validate_the_response_Msg_Code_for_OTP(String responseMsgCode) throws Throwable {
        responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(responseMsgCode));
        logger.info("Validation of response Message code is "+ responseMsgCode);
	
	}

	@Then("^Lets validate the response message for OTP\"([^\"]*)\"$")
	public void lets_validate_the_response_message_for_OTP(String responseMsg) throws Throwable {
		responseBody.then().root("msgInfo").body("msg", Matchers.equalTo(responseMsg));
		logger.info("Validation of response Message is "+ responseMsg);
	}

	@Then("^Let validate the committed premium for OTP\"([^\"]*)\"$")
	public void let_validate_the_committed_premium_for_OTP(String committedPremium) throws Throwable {
		responseBody.then().root("payload.premiumAmount").body("biInstallmentPremium", Matchers.equalTo(committedPremium));
		logger.info("Validation of committed premium is "+ committedPremium);
	}
	@Given("^Set the url for illustration\"([^\"]*)\"$")
	public void set_the_url_for_illustration(String inputendPointUrl) throws Throwable {
		endPointUrl = inputendPointUrl;
		logger.info("User set the end point url ::" + endPointUrl);
	}
	@Then("^Lets validate the response of illustration$")
	public void lets_validate_the_response_of_illustration() throws Throwable {
	    responseBody.then().root("payload").body("illustrationPdfBase64", Matchers.notNullValue());
	    logger.info("validation of illustration value is not..");	
	}
	@Given("^Lets Set the SumAssurd in request\"([^\"]*)\"$")
	public void lets_set_SummAssurdInRequest(String inputsumAssurd) throws Throwable {
       sumAssurd=inputsumAssurd;
       logger.info("Set sumAssurd successfully..");
	}

	@Given("^Lets Set the insurd gender\"([^\"]*)\"$")
	public void lets_set_InsurdGender(String inputInsurd) throws Throwable {
       insurdGender=inputInsurd;
       logger.info("Set Insurd Gender successfully..");
	}
	@Given("^Set the header for OTP\"([^\"]*)\"$")
	public void set_the_header_for_OTP(String inputHeader) throws Throwable {
		header = inputHeader;
		logger.info("Set the header is successfully..");
	}

	@Given("^Lets Set the correlationId for OTP\"([^\"]*)\"$")
	public void set_the_correlation_for_OTP(String inputcorrelationID) throws Throwable {
		correlationId = inputcorrelationID;
		logger.info("Set the correlationID is successfully..");
	}



}
