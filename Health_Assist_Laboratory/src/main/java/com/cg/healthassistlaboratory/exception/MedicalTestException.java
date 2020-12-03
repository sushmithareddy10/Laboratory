package com.cg.healthassistlaboratory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MedicalTestException extends RuntimeException {
	
	public MedicalTestException() {
		super();
	}
	
	public MedicalTestException(String message) {
		super(message);
	}

}
