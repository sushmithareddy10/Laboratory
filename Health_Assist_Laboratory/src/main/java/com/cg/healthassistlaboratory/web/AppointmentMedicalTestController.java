package com.cg.healthassistlaboratory.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthassistlaboratory.domain.AppointmentMedicalTest;
import com.cg.healthassistlaboratory.service.AppointmentMedicalTestService;

/**
 * creating AppointmentMedicalTestController which controls all the service of
 * AppointmentMedicalTestn using RESTful Api's
 *
 */
@RestController
@RequestMapping("/api/appointments")
public class AppointmentMedicalTestController {
	/**
	 * creating AppointmentMedicalTestService by autowiring to instantiate the
	 * appointmentService object
	 */
	@Autowired
	private AppointmentMedicalTestService appointmentService;

	/**
	 * creating saveAppointmentMedicalTest to save the medicalTest details
	 * 
	 * @param appointmentMedicalTest
	 */
//	@PostMapping("/insert")
//	public void saveAppointmentMedicalTest(@RequestBody AppointmentMedicalTest appointmentMedicalTest) {
//		appointmentService.save(appointmentMedicalTest);
//	}

	/**
	 * creating viewAppointmentMedicalTests which returns all the list of medical
	 * tests from database
	 * 
	 * @return
	 */
	@GetMapping("/all")
	public List<AppointmentMedicalTest> viewAppointmentMedicalTests() {
		return appointmentService.viewAppointmentMedicalTest();
	}

	/**
	 * creating addPatientTestResult method which returns the appointment medical
	 * test object given medicalTestId and testResult as parameters
	 * 
	 * @param medicalTestId
	 * @param testResult
	 * @return
	 */
	@GetMapping("/{medicalTestId}/{testResult}")
	public AppointmentMedicalTest addPatientTestResult(@PathVariable("medicalTestId") long medicalTestId,
			@PathVariable("testResult") String testResult) {
		return appointmentService.addPatientMedicalTestResult(medicalTestId, testResult);
	}

}
