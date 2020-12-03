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

@RestController
@RequestMapping("api/appointments")
public class AppointmentMedicalTestController {
	
	@Autowired
	private AppointmentMedicalTestService appointmentService;
	
	@PostMapping("")
	public void saveAppointmentMedicalTest(@RequestBody AppointmentMedicalTest appointmentMedicalTest) {
		appointmentService.save(appointmentMedicalTest);
	}
	
	@GetMapping("/all")
	public List<AppointmentMedicalTest> viewAppointmentMedicalTests() {
		return appointmentService.viewMedicalTest();
	}
	
	@GetMapping("/{medicalTestId}/{testResult}")
	public AppointmentMedicalTest addPatientTestResult(@PathVariable("medicalTestId") long medicalTestId, @PathVariable("testResult") String testResult) {
		return appointmentService.addPatientMedicalTestResult(medicalTestId, testResult);
	}

}
