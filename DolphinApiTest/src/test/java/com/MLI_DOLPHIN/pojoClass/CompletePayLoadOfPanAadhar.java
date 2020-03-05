package com.MLI_DOLPHIN.pojoClass;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompletePayLoadOfPanAadhar {

	private PanDobMetaData metadata;
	private AadharRequest aadharRequest;
	private PanRequest panRequest;
	
	

	public CompletePayLoadOfPanAadhar(PanDobMetaData metadata ,AadharRequest aadharRequest,PanRequest panRequest){
		this.aadharRequest=aadharRequest;
		this.panRequest=panRequest;
		this.metadata=metadata;
	}
	
	@JsonProperty("metadata")
	public PanDobMetaData getMetadata() {
		return metadata;
	}
	
	@JsonProperty("metadata")
	public void setMetadata(PanDobMetaData metadata) {
		this.metadata = metadata;
	}


	@JsonProperty("aadharRequest")
	public AadharRequest getAadharRequest() {
		return aadharRequest;
	}

	@JsonProperty("aadharRequest")
	public void setAadharRequest(AadharRequest value) {
		this.aadharRequest = value;
	}

	@JsonProperty("panRequest")
	public PanRequest getPanRequest() {
		return panRequest;
	}

	@JsonProperty("panRequest")
	public void setPanRequest(PanRequest value) {
		this.panRequest = value;
	}

}
