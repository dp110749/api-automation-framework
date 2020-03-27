package com.MLI_DOLPHIN.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

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
	
	public static Properties readPropertiesFile() {
		File file = new File(System.getProperty("user.dir") + "/src/test/resources/propertiesFile.properties");
		Properties properties=null;
		try {
			FileInputStream fileinput = new FileInputStream(file);
			properties = new Properties();
			try {
				properties.load(fileinput);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	// This function is used to replace the value based on key
    public static JSONObject replacekeyInJSONObject(JSONObject jsonObject, String jsonKey,String jsonValue) {

        for (Object key : jsonObject.keySet()) {
            if (key.equals(jsonKey) && ((jsonObject.get(key) instanceof String)||(jsonObject.get(key) instanceof Number)||jsonObject.get(key) ==null)) {
                jsonObject.put(key, jsonValue);
                return jsonObject;
            } else if (jsonObject.get(key) instanceof JSONObject) {
                JSONObject modifiedJsonobject = (JSONObject) jsonObject.get(key);
                if (modifiedJsonobject != null) {
                    replacekeyInJSONObject(modifiedJsonobject, jsonKey, jsonValue);
                }
            }

        }
        return jsonObject;
    }
 // This Function is used to convert String to json object 
    public static JSONObject createJSONObject(String jsonString){
        JSONObject  jsonObject=new JSONObject();
        JSONParser jsonParser=new  JSONParser();
        if ((jsonString != null) && !(jsonString.isEmpty())) {
            try {
                jsonObject=(JSONObject) jsonParser.parse(jsonString);
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }


}
