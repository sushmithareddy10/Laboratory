package com.cg.healthassistlaboratory.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;



/**
 * This is a Doctor class which has fields regarding doctor personal details.
 * This Doctor entity creates as doctor table in database
 * @author SUSHMITHA
 *
 */
@Entity
public class Doctor {
	/**
	 * creating long instance variable doctorId
	 */
	@Id
	@Column(unique=true, updatable=false)
	private long doctorId;
	/**
	 * creating String instance variable doctorName
	 */
	@NotBlank(message="Doctor Name is requried")
	private String doctorName;
	/**
	 * creating String instance variable doctorSpecialization
	 */
	@NotBlank(message="Doctor Specialization is required")
	private String doctorSpecialization;
	/**
	 * creating Long instance variable doctorPhoneNumber
	 */
	@NotBlank(message="Doctor Phone Number is required")
	private String doctorPhoneNumber;
	/**
	 * creating String instance variable doctorEmail
	 */
	@NotBlank(message="Doctor Email is required")
	private String doctorEmail;
	
	
	
	/**
	 * creating getter for doctorId to get the doctorId details
	 * @return
	 */
	public long getDoctorId() {
		return doctorId;
	}
	/**
	 * creating setter for doctorId to set the parameter values to the doctorId
	 * @param doctorId
	 */
	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 * creating a getter for doctorName to get the doctorName details
	 * @return
	 */
	public String getDoctorName() {
		return doctorName;
	}
	/**
	 * creating a setter for set doctoName to set the parameter value to doctorName
	 * @param doctorName
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	/**
	 * creating getter for doctorSpecialization to get the doctorSpecialization details
	 * @return
	 */
	public String getDoctorSpecialization() {
		return doctorSpecialization;
	}
	/**
	 * creating setter for the doctor specialization to set the parameter value to doctorSpecialization
	 * @param doctorSpecialization
	 */
	public void setDoctorSpecialization(String doctorSpecialization) {
		this.doctorSpecialization = doctorSpecialization;
	}
	/**
	 * creating getter for doctorPhoneNumber to get the details for doctorPhoneNumber
	 * @return
	 */
	public String getDoctorPhoneNumber() {
		return doctorPhoneNumber;
	}
	/**
	 * creating setter for doctorPhoneNumber to set the parameter value for doctorPhoneNumber
	 * @param doctorPhoneNumber
	 */
	public void setDoctorPhoneNumber(String doctorPhoneNumber) {
		this.doctorPhoneNumber = doctorPhoneNumber;
	}
	/**
	 * creating getter for doctorEmail to get the details of dooctorEmail
	 * @return
	 */
	public String getDoctorEmail() {
		return doctorEmail;
	}
	/**
	 * creating a setter for doctorEmail to the parameter value for dooctorEmail
	 * @param doctorEmail
	 */
	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}
	
	/**
	 * creating a parameterized constructor to set the values passed as parameters to respective instance variables while creating constructor object 
	 * @param doctorId
	 * @param doctorName
	 * @param doctorSpecialization
	 * @param doctorPhoneNumber
	 * @param doctorEmail
	 */
	public Doctor(long doctorId, String doctorName, String doctorSpecialization, String doctorPhoneNumber,
			String doctorEmail) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.doctorSpecialization = doctorSpecialization;
		this.doctorPhoneNumber = doctorPhoneNumber;
		this.doctorEmail = doctorEmail;
	}
	
	/**
	 * creating a toString method to override the String statement of the object
	 */
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", doctorSpecialization="
				+ doctorSpecialization + ", doctorPhoneNumber=" + doctorPhoneNumber + ", doctorEmail=" + doctorEmail
				+ "]";
	}
	
	/**
	 * creating non-parameterized constructor 
	 */
	public Doctor() {
		
	}
	
	
	
	

}
