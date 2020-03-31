package com.MLI_DOLPHIN.utilities;
import com.MLI_DOLPHIN.baseclass.BaseClass;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.excelreader.ExcelFileReader;

import io.restassured.response.Response;

public class GetToken extends BaseClass {

	public Response responseBody;
	public Integer statuscode;
	public static String access_token = null;

	public  String getAccessToken() {

		try {
			responseBody = WebservicesMethod.OAUTHAPI_POST_METHOD(
					ExcelFileReader.readingdata("TokenApiTest", 1, 2),
					ExcelFileReader.readingdata("TokenApiTest", 1, 3),
					ReusableFunction.requestHeaders(ExcelFileReader.readingdata("TokenApiTest", 1, 4)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		logger.info("Response Body is ==" + responseBody.getBody().prettyPrint());
//		responseBody.then().root("payload").body("token", Matchers.notNullValue()).statusCode(statuscode);
		access_token = responseBody.then().extract().path("payload.token");
//		logger.info("Segrigated Access token :" + access_token);

		return access_token;
	}
	

}
