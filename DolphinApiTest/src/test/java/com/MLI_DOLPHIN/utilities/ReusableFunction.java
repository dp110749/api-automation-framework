package com.MLI_DOLPHIN.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.MLI_DOLPHIN.baseclass.BaseClass;
import com.google.common.base.Splitter;

public class ReusableFunction extends BaseClass {

	// convert the request in form of key and value
	public static Map<String, String> requestHeaders;
	public static Map<String, String> inputParameterSeperator;

	public static Map<String, String> requestHeaders(String headers) {

		if (Splitter.on(",") != null)
			requestHeaders = Splitter.on(",").withKeyValueSeparator(':').split(headers.trim());
		else {
			requestHeaders = Splitter.on("").withKeyValueSeparator(':').split(headers.trim());
		}
		return requestHeaders;

	}
	
	


	// This function is usesd interchange the request body
	public static String getSpecificRequest(String requestBody, String inputData) {
		JSONParser parser = new JSONParser();
		Object obj = null;
		Object changeRequest = null;
		JSONObject payload = null;
		JSONObject request = null;
		try {
			obj = parser.parse(requestBody.toString());
			changeRequest = parser.parse(inputData);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		// Covert the object to json object.
		JSONObject jsonobject = (JSONObject) obj;

		if ((JSONObject) jsonobject.get("request") == null) {
			payload = (JSONObject) jsonobject.get("payload");
		} else {
			request = (JSONObject) jsonobject.get("request");
			payload = (JSONObject) request.get("payload");
		}

		// Manupulate the json request to input json
		JSONObject data = (JSONObject) changeRequest;
		Collection<String> changeKeys = data.keySet();
		// Change the Value of json Request.
		for (String key : changeKeys) {
			payload.put(key, data.get(key));
		}
		return obj.toString();
	}
}
