package com.cg.healthassistlaboratory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.healthassistlaboratory.domain.AppointmentMedicalTest;
import com.cg.healthassistlaboratory.exception.AppointmentMedicalTestException;
import com.cg.healthassistlaboratory.repository.AppointmentMedicalTestRepository;

@Service
public class AppointmentMedicalTestService {
	@Autowired
	private AppointmentMedicalTestRepository appointmentRepository;
	
	public AppointmentMedicalTest save(AppointmentMedicalTest appointmentMedicalTest) {
		return appointmentRepository.save(appointmentMedicalTest);
	}
	
	public List<AppointmentMedicalTest> viewMedicalTest() {
		List<AppointmentMedicalTest> appointmentList = appointmentRepository.findAll();
		if(appointmentList.isEmpty()) {
			throw new AppointmentMedicalTestException("Could not find any Appointments for Medical Test");
		}
		return appointmentList;
	}
	
	public AppointmentMedicalTest addPatientMedicalTestResult(long medicalTestId, String testResult) {
		AppointmentMedicalTest appointmentMedicalTest = appointmentRepository.findById(medicalTestId);
		if(appointmentMedicalTest==null) {
			throw new AppointmentMedicalTestException("Appointment for Medical Test Id: "+medicalTestId+" is not found");
		}
		appointmentMedicalTest.getPatient().setPatientTestResult(testResult);
		return appointmentRepository.save(appointmentMedicalTest);
	}

}
