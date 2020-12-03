package com.cg.healthassistlaboratory.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DoctorException extends RuntimeException{
	public DoctorException() {
		super();
	}
	
	public DoctorException(String message) {
		super(message);
	}

}
