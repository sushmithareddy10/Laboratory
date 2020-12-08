package com.cg.healthassistlaboratory.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This is a Patient class which has fields related to patient This entity
 * creates a table in the database
 * 
 * @author SUSHMITHA
 *
 */
@Embeddable
public class Patient {

	/**
	 * creating String instance variable patientName
	 */
	@NotBlank(message = "Patient Name is required")
	private String patientName;
	/**
	 * creating Integer instance variable patientAge
	 */
	@NotNull(message = "required")
	private int patientAge;
	/**
	 * creating long instance varibale patientPhoneNumber
	 */
	@NotBlank(message = "Patient Phone Number is Required")
	private long patientPhoneNumber;
	/**
	 * creating String instance variable patientAddress
	 */
	@NotBlank(message = "Patient Address is required")
	private String patientAddress;
	/**
	 * creating String instance variable patientTestResult
	 */
	@NotBlank(message = "Patient Test Result is required")
	private String patientTestResult;

	/**
	 * creating getter for patientName which returns patientName
	 * 
	 * @return
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * creating setter for patientName which sets the parameter value for
	 * patientName
	 * 
	 * @param patientName
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * creating getter for patientAge which returns the patientName
	 * 
	 * @return
	 */
	public int getPatientAge() {
		return patientAge;
	}

	/**
	 * creating a setter for patientAge which sets the parameter value to patientAge
	 * 
	 * @param patientAge
	 */
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	/**
	 * creating getter for patientPhoneNumber which returns the patientPhoneNumber
	 * 
	 * @return
	 */
	public long getPatientPhoneNumber() {
		return patientPhoneNumber;
	}

	/**
	 * creating setter for patientPhoneNumber which sets the parameter value for
	 * patientPhoneNumber
	 * 
	 * @param patientPhoneNumber
	 */
	public void setPatientPhoneNumber(long patientPhoneNumber) {
		this.patientPhoneNumber = patientPhoneNumber;
	}

	/**
	 * creating a getter for patientAddress which returns patientAddress
	 * 
	 * @return
	 */
	public String getPatientAddress() {
		return patientAddress;
	}

	/**
	 * creating setter for patientAddress which sets the parameter value for
	 * patientAdress
	 * 
	 * @param patientAddress
	 */
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	/**
	 * creating getter for patientTestResult which returns the value of
	 * patientTestResult
	 * 
	 * @return
	 */
	public String getPatientTestResult() {
		return patientTestResult;
	}

	/**
	 * creating a setter for patientTestResult which sets the parameter for
	 * patientTestResult
	 * 
	 * @param patientTestResult
	 */
	public void setPatientTestResult(String patientTestResult) {
		this.patientTestResult = patientTestResult;
	}

	/**
	 * creating parameterized constructor which sets all the parameters to the
	 * respective fields
	 * 
	 * @param patientId
	 * @param patientName
	 * @param patientAge
	 * @param patientPhoneNumber
	 * @param patientAddress
	 * @param patientTestResult
	 */
	public Patient(String patientName, int patientAge, long patientPhoneNumber, String patientAddress,
			String patientTestResult) {
		super();
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.patientPhoneNumber = patientPhoneNumber;
		this.patientAddress = patientAddress;
		this.patientTestResult = patientTestResult;
	}


	/**
	 * creating a non-parameterized constructor for patient class
	 */
	public Patient() {
		
	}

}
