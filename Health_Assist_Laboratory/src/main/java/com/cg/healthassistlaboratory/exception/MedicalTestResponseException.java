package com.cg.healthassistlaboratory.exception;

/**
 * creating a class MedicalTestResponseEception which takes the user defined
 * response
 *
 */
public class MedicalTestResponseException {
	/**
	 * declaring a string variable reposneMessage
	 */
	String responseMessage;

	/**
	 * creating a parameterized constructor which takes userdefined messages as
	 * parameter
	 * 
	 * @param responseMessage
	 */
	public MedicalTestResponseException(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	/**
	 * creating getter method to get the response message
	 * 
	 * @return
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * creating setter method to set the response message
	 * 
	 * @param responseMessage
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}
