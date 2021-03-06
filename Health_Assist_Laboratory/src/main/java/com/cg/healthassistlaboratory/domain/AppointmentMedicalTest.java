package com.cg.healthassistlaboratory.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * This is AppointmentMedicalTest class which has fields related to Appointment
 * booked for MedicalTest This entity created table in the database
 * 
 * @author SUSHMITHA
 *
 */
@Entity
public class AppointmentMedicalTest {
	/**
	 * creating Integer instance variable appointmentMedicalTestId
	 */
	@Id
	private long appointmentMedicalTestId;
	/**
	 * creating Patient instance object as variable for patient details
	 */
	@Embedded
	private Patient patient;
	/**
	 * creating String instance variable medicalTestName
	 */
	@NotBlank(message = "Medical Test Name is required")
	private String medicalTestName;
	/**
	 * creating String instance variable bloodGroup
	 */
	@NotBlank(message = "Blood group is required")
	private String bloodGroup;

	/**
	 * creating getter for appointmentMedicalTest which returns the
	 * appointmentMedicaTest value
	 * 
	 * @return
	 */
	public long getAppointmentMedicalTestId() {
		return appointmentMedicalTestId;
	}

	/**
	 * creating a setter for appointmentMedicalTestId to set the parameter value for
	 * appoinmentMedicalTestId
	 * 
	 * @param appointmentMedicalTestId
	 */
	public void setAppointmentMedicalTestId(long appointmentMedicalTestId) {
		this.appointmentMedicalTestId = appointmentMedicalTestId;
	}

	/**
	 * creating a getter for Patient object which returns the patient details
	 * 
	 * @return
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * creating a setter for Patient object to set the parameter value for Patient
	 * object
	 * 
	 * @param patient
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * creating a getter for medicalTestName which returns the value for
	 * medicalTestName
	 * 
	 * @return
	 */
	public String getMedicalTestName() {
		return medicalTestName;
	}

	/**
	 * creating a setter for medicalTestName which sets the parameter value for
	 * medicalTestName
	 * 
	 * @param medicalTestName
	 */
	public void setMedicalTestName(String medicalTestName) {
		this.medicalTestName = medicalTestName;
	}

	/**
	 * creating a getter for bloodGroup which return the value for bloodGroup
	 * 
	 * @return
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * creating a setter for bloodGroup to set the parameter values for the
	 * bloodGroup
	 * 
	 * @param bloodGroup
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * creating a parameterized constructor to set the parameter values for the
	 * respective instance variables
	 * 
	 * @param appointmentMedicalTestId
	 * @param patient
	 * @param medicalTestName
	 * @param bloodGroup
	 */
	public AppointmentMedicalTest(long appointmentMedicalTestId, Patient patient, String medicalTestName,
			String bloodGroup) {
		super();
		this.appointmentMedicalTestId = appointmentMedicalTestId;
		this.patient = patient;
		this.medicalTestName = medicalTestName;
		this.bloodGroup = bloodGroup;
	}


	/**
	 * creating a non-parameterized constructor
	 */
	public AppointmentMedicalTest() {
		
	}

}
