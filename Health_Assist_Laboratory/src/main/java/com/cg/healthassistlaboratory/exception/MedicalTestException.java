package com.cg.healthassistlaboratory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * creating a MedicalTestException class which is an userDefined Exception for
 * Medical Test
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MedicalTestException extends RuntimeException {
	/**
	 * creating non-parameterized constructor which class super method
	 */
	public MedicalTestException() {
		super();
	}

	/**
	 * creating a parameterized constructor passing userdefined message as parameter
	 * 
	 * @param message
	 */
	public MedicalTestException(String message) {
		super(message);
	}

}
