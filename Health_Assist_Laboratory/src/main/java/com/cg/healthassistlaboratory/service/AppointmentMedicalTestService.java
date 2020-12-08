package com.cg.healthassistlaboratory.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.healthassistlaboratory.domain.AppointmentMedicalTest;
import com.cg.healthassistlaboratory.exception.AppointmentMedicalTestException;
import com.cg.healthassistlaboratory.repository.AppointmentMedicalTestRepository;

/**
 * creating AppointmentMedicalTestService class to provide various
 * AppointmentMedicalTest services
 *
 */
@Service
public class AppointmentMedicalTestService {
	/**
	 * implementing logger to find out the particular error,warns and info
	 */
	private static final Logger logger = LoggerFactory.getLogger(AppointmentMedicalTestService.class);
	/**
	 * autowiring appoinment repository for instantiating reposiotory class
	 */
	@Autowired
	private AppointmentMedicalTestRepository appointmentRepository;

	/**
	 * creating a save method to save the details of AppointmentMedicalTest given as
	 * object
	 * 
	 * @param appointmentMedicalTest
	 * @return
	 */
//	public AppointmentMedicalTest save(AppointmentMedicalTest appointmentMedicalTest) {
//		return appointmentRepository.save(appointmentMedicalTest);
//	}

	/**
	 * creating viewAppointmentMedicalTest method which returns the list of
	 * medicalTest
	 * 
	 * @return
	 */
	public List<AppointmentMedicalTest> viewAppointmentMedicalTest() {
		List<AppointmentMedicalTest> appointmentList = appointmentRepository.findAll();
		if (appointmentList.isEmpty()) {
			logger.error("Could not find any Appointments for Medical Test");
			throw new AppointmentMedicalTestException("Could not find any Appointments for Medical Test");
		}
		return appointmentList;
	}

	/**
	 * creating addPatientMedicalTestResult method which returns
	 * AppointmentMedicalTest giving medicaltestId and testRsult as parameters
	 * @param medicalTestId
	 * @param testResult
	 * @return
	 */
	public AppointmentMedicalTest addPatientMedicalTestResult(long medicalTestId, String testResult) {
		AppointmentMedicalTest appointmentMedicalTest = appointmentRepository.findById(medicalTestId);
		if (appointmentMedicalTest == null) {
			logger.error("Appointment for Medical Test Id: " + medicalTestId + " is not found");
			throw new AppointmentMedicalTestException("Appointment for Medical Test Id: " + medicalTestId + " is not found");
		}
		appointmentMedicalTest.getPatient().setPatientTestResult(testResult);
		return appointmentRepository.save(appointmentMedicalTest);
	}
}
