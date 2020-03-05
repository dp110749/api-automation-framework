package com.MLI_DOLPHIN.dolphinTest;

import org.hamcrest.Matchers;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.MLI_DOLPHIN.baseclass.BaseClass;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.excelreader.ExcelFileReader;
import com.MLI_DOLPHIN.requestClass.RequestFactory;
import com.MLI_DOLPHIN.specs.SpecificationFactory;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import io.restassured.response.Response;

public class TC_LE_FTSPTest extends BaseClass{
	
	public RequestFactory requestFactory;
	public Response responseBody;
	public Integer responseStatusCode;
	public String APIRequest;
	public String[] userName = null;
	public String responseErrorMessage;
	public String responseMessageCode;
	public String testRequest;
	
	@DataProvider(name = "TestData")
	public String[][] getTestData() {
		//Pass the Sheet name to get the data 
		String[][] testRecords = ExcelFileReader.getExcelData("LE_FTSP");
		return testRecords;
	}
	
	@Test(dataProvider="TestData")
	public void tc_LE_ftspProductTest(String testCaseNum,String runmode,String url,String requestBody,String headers
			,String inputData,String statusCode,String payloadTag,String responseMessage){
				
		if (runmode.equalsIgnoreCase("y")) {
			
			responseStatusCode = Integer.valueOf(statusCode.trim());
			testRequest = ReusableFunction.getSpecificRequest(requestBody.trim(), inputData.trim());			
			responseBody = WebservicesMethod.POST_METHOD(url, testRequest,ReusableFunction.requestHeaders(headers));
			logger.info("Response Body is::"+responseBody.getBody().prettyPrint().toString());
			
			if (responseBody.getStatusCode() == responseStatusCode) {
				responseBody.then().spec(SpecificationFactory.getGenericResponseSpec()).and().root(payloadTag)
						.body("illustrationPdfBase64", Matchers.notNullValue())
						.and()
						.body("", Matchers.equalTo(""))
						.and()
						.body("", Matchers.equalTo(""))
						.and()
						.body("", Matchers.equalTo(""));
				
			} else if (responseBody.getStatusCode() == responseStatusCode) {
				responseBody.then().spec(SpecificationFactory.getGenericResponseSpec());
			} else {
				logger.info("User not able to send the request..");
			}

		} else {
			throw new SkipException("Run mode marked as N in excel..");

		}
	}
	

		
}


