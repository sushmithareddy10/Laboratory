package com.cg.healthassistlaboratory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * creating a AppointmentMedicalTestException class which is an userDefined
 * Exception for appointment medical test
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AppointmentMedicalTestException extends RuntimeException {
	/**
	 * creating non-parameterized constructor which class super method
	 */
	public AppointmentMedicalTestException() {
		super();
	}

	/**
	 * creating a parameterized constructor passing userdefined message as parameter
	 * 
	 * @param message
	 */
	public AppointmentMedicalTestException(String message) {
		super(message);
	}

}
