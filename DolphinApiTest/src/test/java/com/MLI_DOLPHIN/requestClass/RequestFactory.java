package com.MLI_DOLPHIN.requestClass;
import org.json.simple.JSONObject;
import com.MLI_DOLPHIN.pojoClass.AadharRequest;
import com.MLI_DOLPHIN.pojoClass.CompletePayLoadOfPanAadhar;
import com.MLI_DOLPHIN.pojoClass.PanDobMetaData;
import com.MLI_DOLPHIN.pojoClass.PanRequest;
import com.MLI_DOLPHIN.utilities.GetToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.MLI_DOLPHIN.baseclass.BaseClass;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;

import io.qameta.allure.Step;

import io.restassured.response.Response;

public class RequestFactory extends BaseClass {

	public AadharRequest aadharRequest;
	public PanRequest panRequest;
	public PanDobMetaData panDobMetadata;
	public CompletePayLoadOfPanAadhar panAadharPayload;
	public GetToken gettoken;

//	@Step("oAuth API Token Generator.")
//	public Response Oauth_APiPost_Request(String source, String referenceId, String userName, String password,
//			String UrlEndPoint,String headerKey,String headerValue,String headerKey1,String headerValue1) throws InterruptedException {
//
//		JSONObject metaData = new JSONObject();
//		metaData.put("appId", source);
//		metaData.put("soaCorrelationId", referenceId);
//
//		JSONObject credential = new JSONObject();
//		credential.put("username", userName);
//		credential.put("password", password);
//
//		JSONObject requestparam = new JSONObject();
//		requestparam.put("metadata", metaData);
//		requestparam.put("payload", credential);
//
//		return WebservicesMethod.POST_METHOD(UrlEndPoint, requestparam.toJSONString(), headerKey,headerValue,headerKey1,headerValue1);
//	}
	
//	@Step("oAuth API Token Generator.")
//	public Response Oauth_APiPost_Request(String source, String referenceId, String userName, String password,
//			String UrlEndPoint,String headerKey,String headerValue,String headerKey1,String headerValue1) throws InterruptedException {
//
//		JSONObject metaData = new JSONObject();
//		metaData.put("appId", source);
//		metaData.put("soaCorrelationId", referenceId);
//
//		JSONObject credential = new JSONObject();
//		credential.put("password", password);
//
//		JSONObject requestparam = new JSONObject();
//		requestparam.put("metadata", metaData);
//		requestparam.put("payload", credential);
//
//		return WebservicesMethod.POST_METHOD(UrlEndPoint, requestparam.toJSONString(), headerKey,headerValue,headerKey1,headerValue1);
//	}


//	@Step("PAN DOB Validation.")
//	public Response PanDoB_Request(String Appid, String correlationId, String aadharNumber,
//			String firstName, String middleName, String lastName, String dob, String gender, String email,
//			String careOf, String houseNumber, String street, String landmark, String location, String postOffice,
//			String villageCity, String subDistrict, String district, String state, String stateCode, String postalCode,
//			String mobileNumber, String panNumber, String validationType, String UrlEndPoint,String headerKey,String headervalue,String hederKey1) throws InterruptedException {
//
//		String requestBody = null;
//
//		panDobMetadata = new PanDobMetaData(Appid,correlationId);
//		aadharRequest = new AadharRequest(aadharNumber);
//
//		panRequest = new PanRequest(firstName, middleName, lastName, dob, gender, email, careOf, houseNumber, street,
//				landmark, location, postOffice, villageCity, subDistrict, district, state, stateCode, postalCode,
//				mobileNumber, panNumber, validationType);
//
//		CompletePayLoadOfPanAadhar completePayload = new CompletePayLoadOfPanAadhar(panDobMetadata, aadharRequest, panRequest);
//
//		ObjectMapper objectmapper = new ObjectMapper();
//		try {
//			requestBody = objectmapper.writerWithDefaultPrettyPrinter().writeValueAsString(completePayload);
//		} catch (JsonProcessingException e) {
//
//			e.printStackTrace();
//		}

//		return WebservicesMethod.POST_METHOD(UrlEndPoint, requestBody,headerKey,headervalue,hederKey1,"");
//	}
//	
//	@Step("PAN DOB Validation.")
//	public Response PanDoB_Request(String request, String hederKey, String headervalue,String endPoinrUri) throws InterruptedException {
//		return WebservicesMethod.POST_METHODForPAN(request.trim(),hederKey,headervalue,endPoinrUri);
//	}
//	
//	@Step("PAN DOB Validation.")
//	public Response Post_Request(String request, String hederKey, String headervalue,String endPoinrUri)throws InterruptedException {
		
//		return WebservicesMethod.POST_METHODForPAN1(request.trim(),endPoinrUri);
//	}



}
