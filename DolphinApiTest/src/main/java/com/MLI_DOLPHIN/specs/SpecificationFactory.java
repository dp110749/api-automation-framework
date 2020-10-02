package com.MLI_DOLPHIN.specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;
import com.MLI_DOLPHIN.utilities.ReusableFunction;

public class SpecificationFactory {

/*This function is use to validate the response 
 * 1: Response type and 
 * 2: Response AppId
 * 3: Response time 
 * 4: Response correlation id  */
	
	public static ResponseSpecification getGenericResponseSpec() {
		ResponseSpecBuilder responsBuilder;
		ResponseSpecification responseSpecification;
		responsBuilder = new ResponseSpecBuilder();
		responsBuilder.expectHeader("Content-Type", "application/json");
		//responsBuilder.expectHeader("Transfer-Encoding", "chunked");
		responsBuilder.expectResponseTime(lessThan(10L), TimeUnit.SECONDS);	
		responsBuilder.rootPath("metadata").expectBody("XAppID", equalTo(ReusableFunction.readPropertiesFile().getProperty("AppID")));
//		responsBuilder.rootPath("metadata").expectBody("appId","");		
		responseSpecification = responsBuilder.build();

		return responseSpecification;
	}

	public static RequestSpecification logPayloadResponseInfo() {
		RequestSpecBuilder logBuilder;
		RequestSpecification logrequestSpecification;
		logBuilder = new RequestSpecBuilder();
		if (ReusableFunction.readPropertiesFile().getProperty("allurelog").equals("ENABLED")) {
			logBuilder.addFilter(new AllureRestAssured());
		}
		logrequestSpecification = logBuilder.build();

		return logrequestSpecification;
	}
}
