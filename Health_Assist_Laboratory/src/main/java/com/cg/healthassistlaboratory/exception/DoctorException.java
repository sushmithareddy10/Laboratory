package com.cg.healthassistlaboratory.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * creating a DoctorException class which is an userDefined Exception for Doctor
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DoctorException extends RuntimeException {
	/**
	 * creating non-parameterized constructor which class super method
	 */
	public DoctorException() {
		super();
	}

	/**
	 * creating a parameterized constructor passing userdefined message as parameter
	 * 
	 * @param message
	 */
	public DoctorException(String message) {
		super(message);
	}

}
