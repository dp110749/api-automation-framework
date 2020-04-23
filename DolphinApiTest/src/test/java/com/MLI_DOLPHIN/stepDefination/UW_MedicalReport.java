package com.MLI_DOLPHIN.stepDefination;

import java.util.List;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.hamcrest.core.StringStartsWith;

import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.specs.SpecificationFactory;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import junit.framework.Assert;

public class UW_MedicalReport {
	private final static Logger logger = Logger.getLogger(UW_MedicalReport.class.getName());
	public String requestfile;
	public String header;
	public String endPointUrl;
	public String inputData;
	public String requestBody;
	public Response responseBody;
	public String actionToperform;
	public String newRequestBody;

	@Given("^i want to set the header url and request$")
	public void i_want_to_set_the_header_url_and_request(DataTable inputdata) throws Throwable {
		List<String> data = inputdata.asList(String.class);
		int numberOfElement = data.size();
		List<List<String>> tableRow = inputdata.raw();
		int sizeOfRow = tableRow.size();
		int startLoop = numberOfElement / sizeOfRow;
		int count = 0;
		for (int i = startLoop; i < numberOfElement; i++) {
			header = data.get(i + count);
			count++;
			endPointUrl = data.get(i + count);
			count++;
			requestfile = data.get(i + count);
			break;
		}
		requestBody = ReusableFunction.readJsonFile(requestfile);
	}

	@When("^i want to send the request$")
	public void i_want_to_send_the_request() throws Throwable {
		
		responseBody = WebservicesMethod.POST_METHOD(endPointUrl, requestBody, ReusableFunction.requestHeaders(header));
		logger.info("Response Body is :"+responseBody.getBody().prettyPrint());
	}

	@Then("^i want to check \"([^\"]*)\" is the output$")
	public void i_want_to_check_is_the_output(String statusCode) throws Throwable {
		String STATUS_CODE = String.valueOf(responseBody.getStatusCode());
		Assert.assertEquals(STATUS_CODE, statusCode.toString());
		logger.info("validation of response status code.");
	}

	@Then("^i want to check the respone time and response type and app ID$")
	public void i_want_to_check_the_respone_time_and_response_type_and_app_ID() throws Throwable {
		SpecificationFactory.getGenericResponseSpec();
	}

	@Then("^i want to check the \"([^\"]*)\" and \"([^\"]*)\" in the response$")
	public void i_want_to_check_the_and_in_the_response(String responseMessage, String medicalReportOutput)
			throws Throwable {
		responseBody.then().root("response").body("msgInfo.msg", Matchers.comparesEqualTo(responseMessage));
		logger.info("Varification of response message is :" + responseMessage);
		responseBody.then().root("response").body("payload.medicalReportOutput", Matchers.comparesEqualTo(responseMessage)).and().body("kickoutMsg", Matchers.equalTo("Insured life cover exceeds 2 crore"));
		logger.info("Varification of generate medical report outPut is :" + medicalReportOutput);
	}

	@Given("^i want to change the cotegory in request$")
	public void i_want_to_change_the_cotegory_in_request(DataTable testData) throws Throwable {
        List<String >testdata =testData.asList(String.class);
		List<List<String>> tableRow = testData.raw();
		int sizeOfRow = tableRow.size();
		int startLoop = testdata.size() / sizeOfRow;
		int count = 0;

        for(int i=startLoop;i<testdata.size();i++){
        	inputData=testdata.get(i+count);
        	count++;
        	actionToperform=testdata.get(i+count);
        	break;
        }
        requestBody= ReusableFunction.getSpecificRequest(requestBody, inputData, actionToperform);
//	   responseBody = WebservicesMethod.POST_METHOD(endPointUrl, newRequestBody, ReusableFunction.requestHeaders(header));
//	   logger.info("Response Body is-:"+responseBody.getBody().prettyPrint());
	}

	@Then("^i want to check the respone time and response type$")
	public void i_want_to_check_the_respone_time_and_response_type() throws Throwable {

	}
	
	@Given("^i want to set header in request \"([^\"]*)\"$")
	public void i_want_to_set_header_in_request(String header) throws Throwable {
		header=this.header;
	}

	@Then("^i want to check the \"([^\"]*)\" in the response$")
	public void i_want_to_check_the_in_the_response(String responseMessage) throws Throwable {
		
		responseBody.then().statusCode(200).extract();
		logger.info("=========="+responseBody.getBody().prettyPrint());
		
	}


}
