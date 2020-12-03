package com.cg.healthassistlaboratory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler {
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleDoctorException(DoctorException ex, WebRequest request){
		DoctorResponseException responseException=new DoctorResponseException(ex.getMessage());
		return new ResponseEntity<Object>(responseException,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handlerMedicalTestException(MedicalTestException ex, WebRequest request) {
		MedicalTestResponseException reponseExcpetion = new MedicalTestResponseException(ex.getMessage());
		return new ResponseEntity<Object>(reponseExcpetion,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handlerAppointmentMedicalTestException(AppointmentMedicalTestException ex, WebRequest request) {
		AppointmentMedicalTestResponseException reponseExcpetion = new AppointmentMedicalTestResponseException(ex.getMessage());
		return new ResponseEntity<Object>(reponseExcpetion,HttpStatus.BAD_REQUEST);
	}
	
	

}