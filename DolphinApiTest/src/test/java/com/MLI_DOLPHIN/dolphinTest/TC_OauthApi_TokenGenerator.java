package com.MLI_DOLPHIN.dolphinTest;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.MLI_DOLPHIN.baseclass.BaseClass;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.excelreader.ExcelFileReader;
import com.MLI_DOLPHIN.requestClass.RequestFactory;
import com.MLI_DOLPHIN.specs.SpecificationFactory;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import io.restassured.response.Response;

public class TC_OauthApi_TokenGenerator extends BaseClass {
	public RequestFactory requestFactory;
	public Response responseBody;
	public Integer responseStatusCode;
	public String APIRequest;
	public String[] userName = null;
	public String responseErrorMessage;
	public String responseMessageCode;
	public String testRequest;

	@BeforeClass()
	public void SetRequest() {

	}

	@DataProvider(name = "TestData")
	public String[][] getTestData() {
		String[][] testRecords = ExcelFileReader.getExcelData("TokenApiTest");
		return testRecords;
	}

	@Test(dataProvider = "TestData")
	public void tc001_(String tcName, String runmode, String url, String requestBody, String headers,
			String inputData, String statusCode, String jsonPayload, String responseValue, String actualMeaageCode,
			String responseMessage,String pathofMesgcode,String expErrorMessage) throws Exception {
		logger.info("Test Execution Started ");
		
		responseErrorMessage=expErrorMessage;

		if (runmode.equalsIgnoreCase("y")) {

			responseStatusCode = Integer.valueOf(statusCode.trim());
			testRequest = ReusableFunction.getSpecificRequest(requestBody.trim(), inputData.trim());
			responseBody = WebservicesMethod.OAUTHAPI_POST_METHOD(url, testRequest,
					ReusableFunction.requestHeaders(headers));
			
			logger.info("response body is ::"+responseBody.getBody().prettyPrint());
			 responseMessageCode = responseBody.then().extract().path(pathofMesgcode.trim());
			
			/* Validation of Status Code and Access Token */			
			if (statusCode.equals(responseMessage)) {
				Assert.assertEquals(responseBody.getStatusCode(), 200);
				responseBody.then().spec(SpecificationFactory.getGenericResponseSpec()).and().root(jsonPayload)
						.body(responseValue, Matchers.notNullValue())
						.and()
						.root("msgInfo")
						.body("msgDescription", Matchers.comparesEqualTo(expErrorMessage.trim()));
				
			} else if (responseMessageCode.equals(actualMeaageCode)) {
				Assert.assertEquals(responseBody.getStatusCode(),200);
				responseBody.then().spec(SpecificationFactory.getGenericResponseSpec())
				.and()
				.root("msgInfo")
				.body("msgDescription", Matchers.comparesEqualTo(expErrorMessage.trim()));				
			}else{
				
				logger.info("User not able to send the request..");
			}

		} else {
			throw new SkipException("Run mode marked as N in excel..");

		}
	}
}
