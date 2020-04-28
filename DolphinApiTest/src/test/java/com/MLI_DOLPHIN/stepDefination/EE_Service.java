package com.MLI_DOLPHIN.stepDefination;

import java.util.List;

import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;

import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import com.google.gson.JsonObject;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import junit.framework.Assert;

public class EE_Service {
	
	private final static Logger logger = Logger.getLogger(EE_Service.class.getName());
	public String endPointUrl;
	public String requestFile;
	public String header;
	public String requestBody;
	public Response responseBody;
	public String statuscode;
    public String testData;
	
	@Given("^:set the url request and header$")
	public void set_the_url_request_and_header(DataTable setinputData) throws Throwable {

		List<String> listOfdata = setinputData.asList(String.class);
		List<List<String>> numRow = setinputData.raw();
		int loopStart = listOfdata.size() / numRow.size();
		for (int i = loopStart; i < listOfdata.size(); i++) {
			endPointUrl = listOfdata.get(i);
			i++;
			header = listOfdata.get(i);
			i++;
			requestFile = listOfdata.get(i);
			break;

		}
		requestBody = ReusableFunction.readJsonFile(requestFile);

	}

	@When("^:i want to send the request$")
	public void i_want_to_send_the_request() throws Throwable {
		Thread.sleep(1000);
		responseBody=WebservicesMethod.POST_METHOD(endPointUrl, requestBody, ReusableFunction.requestHeaders(header));
        logger.info("response body is ::"+responseBody.prettyPrint());
	}

	@Then("^:i want to validate the response \"([^\"]*)\"$")
	public void i_want_to_validate_the_response(String statusCode) throws Throwable {
		
		statuscode = String.valueOf(responseBody.getStatusCode());
		Assert.assertEquals(statusCode, statuscode);
		logger.info("Varification of response status code successfully pass ");


	}

	@Then("^:i want to validate the \"([^\"]*)\" of job$")
	public void i_want_to_validate_the_of_job(String statusMsg) throws Throwable {

		responseBody.then().body("status", Matchers.equalTo(statusMsg));
		logger.info("Varification of status message successfully pass.");
	}

	@Given("^: i want to set the \"([^\"]*)\"$")
	public void i_want_to_set_the(String headers) throws Throwable {
       header=headers;
	}
	@Given("^: i want to set the url \"([^\"]*)\"$")
	public void i_want_to_set_url(String inputdata) throws Throwable {
       endPointUrl=inputdata;
	}
	
	@Given("^: i want to remove the field$")
	public void i_want_to_remove_the_field(DataTable inputdata) throws Throwable {		
		List<String> testData =inputdata.asList(String.class);
		String getdata=testData.get(1);
		
		JSONObject js=ReusableFunction.createJSONObject(requestBody);
		JSONObject newjs=ReusableFunction.replacekeyInJSONObject(js, "PolicyAgentCustomerInfo", "");
		
			
//		String request =ReusableFunction.getSpecificRequest(requestBody, getdata, "removeData");
		System.out.println("---------"+newjs.toString());
	}



	@Then("^: i want to validate the status message \"([^\"]*)\"$")
	public void i_want_to_validate_the_status_message(String responseMsg) throws Throwable {

	}

}
