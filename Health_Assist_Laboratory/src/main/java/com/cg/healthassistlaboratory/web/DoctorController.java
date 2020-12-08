package com.cg.healthassistlaboratory.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthassistlaboratory.domain.Doctor;
import com.cg.healthassistlaboratory.service.DoctorService;
import com.cg.healthassistlaboratory.service.MapValidationErrorService;


/**
 * creating a class DoctorController which controls all the services related to
 * Doctor
 *
 */
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
	/**
	 * creating an instance for doctorService and autowiring is used to instantiate
	 * the object
	 */
	@Autowired
	private DoctorService doctorService;

	/**
	 * creating MapValidationService instance to instantiate mapValidationError to
	 * show fields which are given blank by returning errorMap
	 */
	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	/**
	 * creating add doctor method to add the doctor details into the database
	 * 
	 * @param doctor
	 * @param result
	 * @return
	 */
	@PostMapping("/insert")
	public ResponseEntity<?> addDoctor(@Valid @RequestBody Doctor doctor, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null) {
			return errorMap;
		}
		Doctor newDoctor = doctorService.saveDoctor(doctor);
		return new ResponseEntity<Doctor>(newDoctor, HttpStatus.OK);
	}

	/**
	 * creating method viewListOfAvaialable doctors to get the list of doctors from
	 * database
	 * 
	 * @return
	 */
	@GetMapping("/all")
	public List<Doctor> viewListOfAvailableDoctors() {
		return doctorService.findAllDoctors();
	}

	/**
	 * creating viewListBySpecialization method to view the list of doctors based on
	 * specialization
	 * 
	 * @param specialization
	 * @return
	 */
	@GetMapping("/spec/{specialization}")
	public List<Doctor> viewListBySpecialization(@PathVariable("specialization") String specialization) {
		return doctorService.findByDoctorSpecialization(specialization);
	}

	/**
	 * creating a removeDoctor method to remove the doctor details based on doctorId
	 * 
	 * @param doctorId
	 * @return
	 */
	@DeleteMapping("/delete/{doctorId}")
	public ResponseEntity<?> removeDoctor(@PathVariable("doctorId") long doctorId) {
		boolean truth = doctorService.remove(doctorId);
		return new ResponseEntity<String>("Deleted Doctor details with Id: " + doctorId + "!", HttpStatus.OK);
	}

	/**
	 * creating updateByName method to update the doctor name based on doctorId
	 * 
	 * @param doctorId
	 * @param doctorName
	 * @return
	 */
	@PatchMapping("/updateName/{id}/{name}")
	public ResponseEntity<?> updateByName(@PathVariable("id") long doctorId, @PathVariable("name") String doctorName) {
		Doctor updatedDoctorByName = doctorService.updateName(doctorId, doctorName);
		return new ResponseEntity<Doctor>(updatedDoctorByName, HttpStatus.CREATED);
	}

	/**
	 * creating updateBySpecialization method to update the specialization based on
	 * doctorId
	 * 
	 * @param doctorId
	 * @param doctorSpecialization
	 * @return
	 */
	@PatchMapping("/updateSpec/{id}/{specialization}")
	public ResponseEntity<?> updateBySpecialization(@PathVariable("id") long doctorId,
			@PathVariable("specialization") String doctorSpecialization) {
		Doctor updatedDoctorBySpecialization = doctorService.updateSpecialization(doctorId, doctorSpecialization);
		return new ResponseEntity<Doctor>(updatedDoctorBySpecialization, HttpStatus.CREATED);
	}

	/**
	 * creating updateByPhoneNumber method to update the doctor phone number based
	 * on doctor id
	 * 
	 * @param doctorId
	 * @param doctorPhoneNumber
	 * @return
	 */
	@PatchMapping("/updatePhone/{id}/{phone}")
	public ResponseEntity<?> updateByPhoneNumber(@PathVariable("id") long doctorId,
			@PathVariable("phone") String doctorPhoneNumber) {
		Doctor updatedDoctorByPhoneNumber = doctorService.updatePhoneNumber(doctorId, doctorPhoneNumber);
		return new ResponseEntity<Doctor>(updatedDoctorByPhoneNumber, HttpStatus.CREATED);
	}

	/**
	 * creating updateByEmail to update the email of the doctor based on doctorId
	 * 
	 * @param doctorId
	 * @param doctorEmail
	 * @return
	 */
	@PatchMapping("/updateEmail/{id}/{email}")
	public ResponseEntity<?> updateByEmail(@PathVariable("id") long doctorId,
			@PathVariable("email") String doctorEmail) {
		Doctor updatedDoctorByEmail = doctorService.updateEmail(doctorId, doctorEmail);
		return new ResponseEntity<Doctor>(updatedDoctorByEmail, HttpStatus.CREATED);
	}

	/**
	 * creating update method to update the details of doctor
	 * 
	 * @param doctorId
	 * @param doctor
	 * @return
	 */
	@PutMapping("/{doctorId}")
	public ResponseEntity<?> update(@PathVariable("doctorId") long doctorId, @RequestBody Doctor doctor) {
		Doctor updateDoctor = doctorService.update(doctorId, doctor);
		return new ResponseEntity<Doctor>(updateDoctor, HttpStatus.OK);
	}
}
