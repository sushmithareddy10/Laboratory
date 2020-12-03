package com.cg.healthassistlaboratory.exception;

public class MedicalTestResponseException {
	String responseMessage;
	
	public MedicalTestResponseException(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	

}
