package com.cg.healthassistlaboratory.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * creating MapValidationErrorService which gives services for errors occured
 * while validating the Entity fields
 *
 */
@Service
public class MapValidationErrorService {
	/**
	 * creating mapValidationError method which returns errorMap when is encounters
	 * and errors and null if there are no errors
	 * 
	 * @param result
	 * @return
	 */
	public ResponseEntity<?> mapValidationError(BindingResult result) {
		if (result.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError fielderror : result.getFieldErrors()) {
				errorMap.put(fielderror.getField(), fielderror.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}
