package com.cg.healthassistlaboratory.exception;

public class DoctorResponseException {
	/**
	 * declaring a string variable reposneMessage
	 */
	String repsonseMessage;

	/**
	 * creating a parameterized constructor which takes userdefined messages as
	 * parameter
	 * 
	 * @param responseMessage
	 */
	public DoctorResponseException(String responseMessage) {
		this.repsonseMessage = responseMessage;
	}

	/**
	 * creating getter method to get the response message
	 * 
	 * @return
	 */
	public String getRepsonseMessage() {
		return repsonseMessage;
	}

	/**
	 * creating setter method to set the response message
	 * 
	 * @param responseMessage
	 */
	public void setRepsonseMessage(String repsonseMessage) {
		this.repsonseMessage = repsonseMessage;
	}

}
