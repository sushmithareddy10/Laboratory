package com.cg.healthassistlaboratory.exception;

public class DoctorResponseException {
	String repsonseMessage;
	
	public DoctorResponseException(String responseMessage) {
		this.repsonseMessage = responseMessage;
	}

	public String getRepsonseMessage() {
		return repsonseMessage;
	}

	public void setRepsonseMessage(String repsonseMessage) {
		this.repsonseMessage = repsonseMessage;
	}
	
	

}
