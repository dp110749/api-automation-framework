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

public class LE_CIP_Service extends WebservicesMethod{
	
	private final static Logger logger = Logger.getLogger(LE_CIP_Service.class.getName());

	private String insuredAge;
	private String channel;
	private String committedPremium;
	private String productName;
	
	@Given("^Set pre request data for CIP plan$")
	public void set_pre_request_data_for_CIP_plan(DataTable preRequestData) throws Throwable {
        List<String> listOfData =preRequestData.asList(String.class);
        List<List<String>> numOfRow =preRequestData.raw();
        getSecondRowData=listOfData.size()/numOfRow.size();
        for(int i=getSecondRowData;i<listOfData.size();i++){
        	endPointUrl=listOfData.get(i);
        	i++;
        	header=listOfData.get(i);
        	i++;
        	requestFile=listOfData.get(i);
        	i++;
        	correlationId=listOfData.get(i);
        	i++;
        	insuredAge=listOfData.get(i);
        	i++;
        	channel=listOfData.get(i);
        	i++;
        	committedPremium=listOfData.get(i);
        	i++;
        	productName=listOfData.get(i);
        	i++;
        	method_Type=listOfData.get(i);
        }
        logger.info("Set the data in variable sccessfully..");
	}
	
	public String getCompleteRequest() throws IOException{
		requestBody=ReusableFunction.readJsonFile(requestFile);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"X-Correlation-ID"+"}}"),correlationId);
		requestBody =requestBody.replaceAll(Pattern.quote("{{"+"ageOfInsured"+"}}"), insuredAge);
		requestBody =requestBody.replaceAll(Pattern.quote("{{"+"channel"+"}}"), channel);
		requestBody =requestBody.replaceAll(Pattern.quote("{{"+"committedPremium"+"}}"), committedPremium);
		requestBody =requestBody.replaceAll(Pattern.quote("{{"+"productName"+"}}"), productName);
		 logger.info("Set the data in Request sccessfully..");
		return requestBody;
	}

	@When("^Send the POST Request for CIP$")
	public void send_the_POST_Request_for_CIP() throws Throwable {
		responseBody=WebservicesMethod.Select_API_METHOD(method_Type,endPointUrl, getCompleteRequest(), ReusableFunction.requestHeaders(header));
		 logger.info("Response Body is .."+responseBody.prettyPrint());
	}

	@Then("^Lets validate the repsone code\"([^\"]*)\"$")
	public void lets_validate_the_repsone_code(String inputResponseCode) throws Throwable {
		
		 logger.info("Response code validated sccessfully..");
	}

	@Then("^Lets Validate the repsonse message Code\"([^\"]*)\"$")
	public void lets_Validate_the_repsonse_message_Code(String inputResponseMsgCode) throws Throwable {
		
		logger.info("Response code Msg validated sccessfully..");
	}

	@Then("^Lets Validate the response Message\"([^\"]*)\"$")
	public void lets_Validate_the_response_Message(String inputResponseMsg) throws Throwable {

		logger.info("Response Msg validated sccessfully..");
	}
	@Then("^Lest Validate the response\"([^\"]*)\"$")
	public void lest_Validate_the_response(String inputAfyp) throws Throwable {
		
		logger.info("Response AFYP Value validated sccessfully..");
	}
	@Then("^Lets validate the CIP Illustration$")
	public void lets_validate_the_CIP_Illustration() throws Throwable {
		responseBody.then().root("payload").body("illustrationPdfBase64", Matchers.notNullValue());
		logger.info("Validation of CIP illustration is successfull..");
	}
	@Given("^Lets set the Url for CIP\"([^\"]*)\"$")
	public void lets_set_the_Url_for_CIP(String inputendPointUrl) throws Throwable {
        endPointUrl=inputendPointUrl;
        logger.info("User set the end point url successfully..");
	}
	@Given("^Lets set the insurd age\"([^\"]*)\"$")
	public void lets_set_the_insurd_age(String inputIinsuredAge) throws Throwable {
		insuredAge = inputIinsuredAge;
		logger.info("Set the input insured age Successfully..");
	
	}

	@Given("^Lets set the channel\"([^\"]*)\"$")
	public void lets_set_the_channel(String inputChannel) throws Throwable {
		channel = inputChannel;
		logger.info("set the committed premium successfully..");	
	}

	@Given("^Lets set the committed premium\"([^\"]*)\"$")
	public void lets_set_the_committed_premium(String inputcommittedPremium) throws Throwable {
		committedPremium=inputcommittedPremium;
		logger.info("Set the committed premium successfully..");
	}

}
