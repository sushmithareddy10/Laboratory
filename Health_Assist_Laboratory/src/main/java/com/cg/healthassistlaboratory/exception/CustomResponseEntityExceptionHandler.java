package com.cg.healthassistlaboratory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 * creating customResponseEntityExceptionHandler which acts like an try catch
 * handler
 *
 */
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler {

	/**
	 * This is an exception handler for Doctor Entity
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler
	public final ResponseEntity<Object> handleDoctorException(DoctorException ex, WebRequest request) {
		DoctorResponseException responseException = new DoctorResponseException(ex.getMessage());
		return new ResponseEntity<Object>(responseException, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This is an exception handler for MedicalTest Entity
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler
	public final ResponseEntity<Object> handlerMedicalTestException(MedicalTestException ex, WebRequest request) {
		MedicalTestResponseException reponseExcpetion = new MedicalTestResponseException(ex.getMessage());
		return new ResponseEntity<Object>(reponseExcpetion, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This is an exception handler for AppointmentMedicalTest Entity
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler
	public final ResponseEntity<Object> handlerAppointmentMedicalTestException(AppointmentMedicalTestException ex,
			WebRequest request) {
		AppointmentMedicalTestResponseException reponseExcpetion = new AppointmentMedicalTestResponseException(
				ex.getMessage());
		return new ResponseEntity<Object>(reponseExcpetion, HttpStatus.BAD_REQUEST);
	}
	

}