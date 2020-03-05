package com.MLI_DOLPHIN.pojoClass;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PanDobMetaData {
	
		private String soaCorrelationId;
		
		private String appId;
		
		public PanDobMetaData(String soaCorrelationId,String appId){
			this.soaCorrelationId=soaCorrelationId;
			this.appId=appId;
		}

		@JsonProperty("soaCorrelationId")
		public void setSoaCorrelationId(String soaCorrelationId) {
		this.soaCorrelationId = soaCorrelationId;
		}
 
		@JsonProperty("soaCorrelationId")
		public String getSoaCorrelationId() {
		return soaCorrelationId;
		}

		@JsonProperty("appId")
		public void setAppId(String appId) {
		this.appId = appId;
		}

		@JsonProperty("appId")
		public String getAppId() {
		return appId;
		}



}
