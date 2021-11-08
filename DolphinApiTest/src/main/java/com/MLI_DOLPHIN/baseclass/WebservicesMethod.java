package com.MLI_DOLPHIN.baseclass;
import static io.restassured.RestAssured.*;
import java.util.Map;
import com.MLI_DOLPHIN.specs.SpecificationFactory;
import com.MLI_DOLPHIN.utilities.GetToken;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class WebservicesMethod {
	
	public static GetToken gettoken;
	public String endPointUrl;
	public String header;
	public String requestFile;
	public String correlationId;
	public String method_Type;
	public int getSecondRowData;
	public String requestBody;
    public String responseCode;
	public static Response responseBody;
	
    /*This fun is used to send the post Request..*/
	public static Response Select_API_METHOD(String methodType,String uRI, String Requestbody,Map<String, String> headers) {
		
		gettoken = new GetToken();
		
		switch(methodType){
		
		case "POST":			
				
			responseBody= given()				
					.spec(SpecificationFactory
					.logPayloadResponseInfo())
					.headers(headers)
					.contentType(ContentType.JSON)
					.header("Authorization",gettoken.getAccessToken())
					.log().all()
					.when()
					.body(Requestbody)
					.post(uRI);
			break;
			
		case "GET":		
		responseBody=given()
			.spec(SpecificationFactory
			.logPayloadResponseInfo())
			.headers(headers)
			.contentType(ContentType.JSON)
			.log().all()
			.when()
			.get(uRI);
		break;
		
		case "PUT":
			responseBody= given()
					.spec(SpecificationFactory
					.logPayloadResponseInfo())
					.headers(headers)
					.contentType(ContentType.JSON)
					.header("Authorization",gettoken.getAccessToken())
					.log().all()
					.when()
					.body(Requestbody)
					.put(uRI);
			break;
			
		case "DELETE":
			responseBody= given()
					.spec(SpecificationFactory
					.logPayloadResponseInfo())
					.headers(headers)
					.header("Authorization",gettoken.getAccessToken())
					.contentType(ContentType.JSON)
					.log().all()
					.when()
					.body(Requestbody)
					.delete(uRI);
			break;
			
			default:
			responseBody=null;	
			System.out.println("There is no method Selected.");
			break;
		}
		
		return responseBody;
		
/*		
		gettoken = new GetToken();	
		return   given()				
				.spec(SpecificationFactory
				.logPayloadResponseInfo())
				.headers(headers)
				.contentType(ContentType.JSON)
				.header("Authorization",gettoken.getAccessToken())
				.log().all()
				.when()
				.body(Requestbody)
				.post(uRI);
*/	
		}

	/*This Fun is used to generate the token*/
	public static Response OAUTHAPI_POST_METHOD(String uRI,String Requestbody,Map<String, String> headers) {		
		return given()
				.contentType(ContentType.JSON)
				.headers(headers)																																																.headers(headers)
			    .spec(SpecificationFactory
 				.logPayloadResponseInfo()).log().all()
				.when()
				.body(Requestbody)
				.post(uRI);
	}
	
/*	public static Response POST_METHODForPAN(String Requestbody, String headerKey,String headerValue ,String uRI) {

		gettoken = new GetToken();
		
		return given()
				.contentType(ContentType.JSON)				
				.header(headerKey,headerValue)
				.header("Authorization",gettoken.getAccessToken())
				.spec(SpecificationFactory
				.logPayloadResponseInfo()).log().all()
				.when()
				.body(Requestbody)
				.post(uRI);
	}
*/    
	


}
