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
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class LE_SJB_Service {
	private final static Logger logger = Logger.getLogger(LE_SJB_Service.class.getName());
	private String endPointUrl;
	private String header;
	private String requestFile;
	private String correlationID;
	private String insuredGender;
	private String sumAssured;
	private String channel;
	private String requestBody;
	private int getSecondRow;
	private String responseCode;
	private String responseMsgCode;
	private String responseMessage;
	private String afyp;
	private Response responseBody;
	
	@Given("^Set the pre request of data for SJB product$")
	public void set_the_pre_request_of_data_for_SJB_product(DataTable preRequestData) throws Throwable {
    List<String>listOfpreSetData =preRequestData.asList(String.class);
    List<List<String>> numOfRow =preRequestData.raw();
    getSecondRow=listOfpreSetData.size()/numOfRow.size();
    for(int i=getSecondRow;i<listOfpreSetData.size();i++){
    	endPointUrl=listOfpreSetData.get(i);
    	i++;
    	header=listOfpreSetData.get(i);
    	i++;
    	requestFile=listOfpreSetData.get(i);
    	i++;
    	correlationID=listOfpreSetData.get(i);
    	i++;
    	insuredGender=listOfpreSetData.get(i);
    	i++;
    	sumAssured=listOfpreSetData.get(i);
    	i++;
    	channel=listOfpreSetData.get(i);  	
      }
       requestBody=ReusableFunction.readJsonFile(requestFile);
		logger.info("Read json request Successfully..");
	}
	public String completeRequest(){
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"X-Correlation-ID"+"}}"),correlationID );
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"genderOfInsured"+"}}"),insuredGender );
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"sumAssured"+"}}"),sumAssured);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"channel"+"}}"),channel);
//		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"sumAssured"+"}}"),sumAssured);
        logger.info("All data set into request successfully..");
		return requestBody;
	}

	@Given("^Set the Expected data$")
	public void set_the_Expected_data(DataTable expOutData) throws Throwable {
      List<String> listOfData =expOutData.asList(String.class);
      List<List<String>> numOfrow=expOutData.raw();
      getSecondRow=listOfData.size()/numOfrow.size();
      for(int i=getSecondRow;i<listOfData.size();i++){
    	  responseCode=listOfData.get(i);
    	  i++;
    	  responseMsgCode=listOfData.get(i);
    	  i++;
    	  responseMessage=listOfData.get(i);
    	  i++;
    	  afyp=listOfData.get(i);
      }
      logger.info("All Expected Data Set into ");
	}

	@When("^send the POST request for SJB$")
	public void send_the_POST_request_for_SJB() throws Throwable {
		responseBody=WebservicesMethod.POST_METHOD(endPointUrl, completeRequest(), ReusableFunction.requestHeaders(header));
		logger.info("User send the POST request is ::"+responseBody.prettyPrint());
	}

	@When("^Lets Validate the response code$")
	public void lets_Validate_the_response_code() throws Throwable {
		Assert.assertEquals(String.valueOf(responseBody.getStatusCode()),responseCode);
		logger.info("Validation of Status Code is successfull..");

	}

	@When("^Lets Validate the response MsgCode and Message$")
	public void lets_Validate_the_response_message_code() throws Throwable {
		if (responseMsgCode.equals("200")) {
			responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(responseMsgCode)).and().body("msg",
					Matchers.equalTo(responseMessage));
			logger.info("Validation of response Msg Code :"+responseMsgCode);
		}else if(responseMsgCode.equals("500")){
				responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(responseMsgCode)).and().body("msg",
						Matchers.equalTo(responseMessage));
				logger.info("Validation of response Msg Code :"+responseMsgCode);
			}else if(responseMsgCode.equals("400")){
				responseBody.then().root("msgInfo").body("msgCode", Matchers.equalTo(responseMsgCode)).and().body("msg",
						Matchers.equalTo(responseMessage));
				logger.info("Validation of response Msg Code :"+responseMsgCode);
			}else{
				logger.info("Api not responding...");
			}

		}
	@When("^Lets validate the AFYP Value$")
	public void lets_validate_the_AFYP_Value() throws Throwable {
      responseBody.then().root("payload.premiumAmount").body("AFYP", Matchers.equalTo(afyp));
      logger.info("Validation of AFYP is Successfull..");
	}
	@Given("^Lets set the illustration url\"([^\"]*)\"$")
	public void lets_set_the_illustration_url(String illustrationUrl) throws Throwable {
     endPointUrl=illustrationUrl;
     logger.info("Set url successfully..");
	}

	@When("^Lets validate the illustration generated or not$")
	public void lets_validate_the_illustration_generated_or_not() throws Throwable {
              responseBody.then().root("payload").body("illustrationPdfBase64", Matchers.notNullValue());
              logger.info("illustration generated successfully...");
	}
	@Given("^Lets set the sumAssurd\"([^\"]*)\" and afypvalue\"([^\"]*)\"$")
	public void lets_set_the_sumAssurd(String inputSumAssurd,String inputafyp) throws Throwable {
       sumAssured=inputSumAssurd;
       logger.info("SumAssurd set successfully is :"+sumAssured);
       afyp=inputafyp;
	}
	@Given("^Set Correlation id\"([^\"]*)\"$")
	public void set_Correlation_id(String inputCorrelationID) throws Throwable {
		correlationID=inputCorrelationID;
		logger.info("Correlation Id set successfully..");
	}
	@Given("^Set the header for SJB\"([^\"]*)\"$")
	public void set_the_header_for_SJB(String inputHeadr) throws Throwable {
		header = inputHeadr;
		logger.info("Header set successfully..");
	}
	@Given("^Set the channel for SJB\"([^\"]*)\"$")
	public void set_the_chnnel_for_SJB(String inputchannel) throws Throwable {
		channel = inputchannel;
		logger.info("Channel set successfully..");
	}
	@Given("^Set the insurdGender for SJB\"([^\"]*)\"$")
	public void set_the_insurdGender_for_SJB(String inputInsurdGender) throws Throwable {
		insuredGender=inputInsurdGender;
		logger.info("Insurd Gender set successfully..");

	}

}
