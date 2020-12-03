package com.cg.healthassistlaboratory.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This is a Medical Test class which has fields related to medical test
 * This entity creates a table in database
 * @author SUSHMITHA
 *
 */
@Entity
public class MedicalTest {
	/**
	 * creating a Integer instance variable medicalTestId;
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int medicalTestId;
	/**
	 * creating String instance variable medicalTestName;
	 */
	private String medicalTestName;
	/**
	 * creating double instance variable medicalTestPrice
	 */
	private double medicalTestPrice;
	
	/**
	 * creating getter for medicalTestId which returns the value of medicalTestId
	 * @return
	 */
	public int getMedicalTestId() {
		return medicalTestId;
	}
	/**
	 * creating setter for medicalTestId which sets the parameter for medicalTestId
	 * @param medicalTestId
	 */
	public void setMedicalTestId(int medicalTestId) {
		this.medicalTestId = medicalTestId;
	}
	/**
	 * creating a getter for medicalTestName which returns the value for medicalTestName
	 * @return
	 */
	public String getMedicalTestName() {
		return medicalTestName;
	}
	/**
	 * creating a setter for medicalTestName to set the parameter value for medicalTestName
	 * @param medicalTestName
	 */
	public void setMedicalTestName(String medicalTestName) {
		this.medicalTestName = medicalTestName;
	}
	/**
	 * creating a getter for medicalTestPrice which returns the value for medicalTestPrice
	 * @return
	 */
	public double getMedicalTestPrice() {
		return medicalTestPrice;
	}
	/**
	 * creating a setter for medicalTestPrice to set the values for medicalTestPrice
	 * @param medicalTestPrice
	 */
	public void setMedicalTestPrice(double medicalTestPrice) {
		this.medicalTestPrice = medicalTestPrice;
	}
	
	/**
	 * creating parameterized constructor for medicalTest which sets the parameters for respective fields when object is created
	 * @param medicalTestId
	 * @param medicalTestName
	 * @param medicalTestPrice
	 */
	public MedicalTest(int medicalTestId, String medicalTestName, double medicalTestPrice) {
		super();
		this.medicalTestId = medicalTestId;
		this.medicalTestName = medicalTestName;
		this.medicalTestPrice = medicalTestPrice;
	}
	
	/**
	 * creating a toString method to override the object as String
	 */
	@Override
	public String toString() {
		return "MedicalTest [medicalTestId=" + medicalTestId + ", medicalTestName=" + medicalTestName
				+ ", medicalTestPrice=" + medicalTestPrice + "]";
	}
	
	public MedicalTest() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
