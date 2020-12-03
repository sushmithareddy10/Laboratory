package com.cg.healthassistlaboratory.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthassistlaboratory.domain.Doctor;
import com.cg.healthassistlaboratory.service.DoctorService;
import com.cg.healthassistlaboratory.service.MapValidationErrorService;

import net.bytebuddy.asm.Advice.Return;

/**
 * creating a class DoctorController which controls all the services related to Doctor
 *
 */
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
	/**
	 * creating an instance for doctorService and autowiring is used to instantiate the object
	 */
	@Autowired
	private DoctorService doctorService;
	
	/**
	 * creating MapValidationService instance to instantiate mapValidationError to show fields which are given blank
	 * by returning errorMap
	 */
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> addDoctor(@Valid @RequestBody Doctor doctor, BindingResult result) {
		ResponseEntity<?> errorMap =  mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) {
			return  errorMap;
		}
		Doctor newDoctor = doctorService.saveDoctor(doctor);
		return new ResponseEntity<Doctor>(newDoctor,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public List<Doctor> viewListOfAvailableDoctors() {
		return doctorService.findAllDoctors();
	}
	
	@GetMapping("/spec/{specialization}")
	public List<Doctor> viewListBySpecialization(@PathVariable("specialization") String specialization) {
		return doctorService.findByDoctorSpecialization(specialization);
	}
	
	@DeleteMapping("/delete/{doctorId}")
	public ResponseEntity<?> removeDoctor(@PathVariable("doctorId") long doctorId) {
		 doctorService.remove(doctorId);
		return new ResponseEntity<String>("Deleted Doctor details with Id: "+doctorId+"!",HttpStatus.OK);
	}
	
	@GetMapping("/updateName/{id}/{name}")
	public ResponseEntity<?> updateByName(@PathVariable("id") long doctorId, @PathVariable("name") String doctorName) {
		Doctor updatedDoctorByName = doctorService.updateName(doctorId, doctorName);
		return new ResponseEntity<Doctor>(updatedDoctorByName,HttpStatus.CREATED);
	}
	
	@GetMapping("/updateSpec/{id}/{specialization}")
	public ResponseEntity<?> updateBySpecialization(@PathVariable("id") long doctorId, @PathVariable("specialization") String doctorSpecialization) {
		Doctor updatedDoctorByName = doctorService.updateSpecialization(doctorId, doctorSpecialization);
		return new ResponseEntity<Doctor>(updatedDoctorByName,HttpStatus.CREATED);
	}
	
	@GetMapping("/updatePhone/{id}/{phone}")
	public ResponseEntity<?> updateByPhoneNumber(@PathVariable("id") long doctorId, @PathVariable("phone") String doctorPhoneNumber) {
		Doctor updatedDoctorByName = doctorService.updatePhoneNumber(doctorId, doctorPhoneNumber);
		return new ResponseEntity<Doctor>(updatedDoctorByName,HttpStatus.CREATED);
	}
	
	@GetMapping("/updateEmail/{id}/{email}")
	public ResponseEntity<?> updateByEmail(@PathVariable("id") long doctorId, @PathVariable("email") String doctorEmail) {
		Doctor updatedDoctorByName = doctorService.updateEmail(doctorId, doctorEmail);
		return new ResponseEntity<Doctor>(updatedDoctorByName,HttpStatus.CREATED);
	}
	
	@PutMapping("/{doctorId}")
	public ResponseEntity<?> update(@PathVariable("doctorId") long doctorId, @RequestBody Doctor doctor) {
		Doctor updateDoctor = doctorService.update(doctorId, doctor);
		return new ResponseEntity<Doctor>(updateDoctor,HttpStatus.OK); 
	}
	
	
	
	

}
