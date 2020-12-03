package com.cg.healthassistlaboratory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AppointmentMedicalTestException extends RuntimeException {
	public AppointmentMedicalTestException() {
		super();
	}
	
	public AppointmentMedicalTestException(String message) {
		super(message);
	}

}
