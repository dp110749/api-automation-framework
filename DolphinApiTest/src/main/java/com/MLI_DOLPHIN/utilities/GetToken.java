package com.MLI_DOLPHIN.utilities;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.excelreader.ExcelFileReader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetToken{

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
			
			e.printStackTrace();
		}
		
		access_token = responseBody.then().extract().path("payload.token");
		
		return access_token;
	}
	

}
