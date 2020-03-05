package com.MLI_DOLPHIN.pojoClass;

import com.fasterxml.jackson.annotation.JsonProperty;
public class AadharRequest {

	private String aadharNumber;
	
	public AadharRequest(String aadharNumber){
		this.aadharNumber=aadharNumber;
	}

	@JsonProperty("aadharNumber")
	public String getAadharNumber() {
		return aadharNumber;
	}

	@JsonProperty("aadharNumber")
	public void setAadharNumber(String value) {
		this.aadharNumber = value;
	}

}
