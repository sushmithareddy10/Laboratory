package com.cg.healthassistlaboratory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.healthassistlaboratory.domain.AppointmentMedicalTest;
import com.cg.healthassistlaboratory.domain.Doctor;
import com.cg.healthassistlaboratory.domain.Patient;
import com.cg.healthassistlaboratory.exception.AppointmentMedicalTestException;
import com.cg.healthassistlaboratory.repository.AppointmentMedicalTestRepository;
import com.cg.healthassistlaboratory.service.AppointmentMedicalTestService;

/**
 * creating class AppoinAppointmentMedicalTestServiceTest which tests the
 * behavior and functionalities of services of AppoinmentMediicalTest class
 *
 */
@ExtendWith(MockitoExtension.class)
class AppointmentMedicalTestServiceTest {
	/**
	 * creating Mock object for appointment repository to instantiate the object
	 */
	@Mock
	private AppointmentMedicalTestRepository appointmentRepository;

	/**
	 * creating InjectMocks for appointment medical test service to instantiate the
	 * object
	 */
	@InjectMocks
	private AppointmentMedicalTestService appointmentService;

	/**
	 * creating test_FindAll_ReturnsListOfAppointmentMedicalTest test case to test
	 * whether list is returned
	 */
	@Test
	public void test_FindAll_ReturnsListOfAppointmentMedicalTest() {
		Patient p1 = new Patient("Sush", 22, 99595864, "1-1384", "");
		Patient p2 = new Patient("Poojitha", 22, 33445544, "1-22-3", "");
		List<AppointmentMedicalTest> appointmentList = new ArrayList<>();
		appointmentList.add(new AppointmentMedicalTest(1, p1, "skin", "A+"));
		appointmentList.add(new AppointmentMedicalTest(2, p2, "hair", "B-"));
		when(appointmentRepository.findAll()).thenReturn(appointmentList);
		List<AppointmentMedicalTest> resultList = appointmentService.viewAppointmentMedicalTest();
		assertEquals(appointmentList.size(), resultList.size());
	}

	/**
	 * creating AddPatientMedicalTestResult test case to check whether it is
	 * returning medical test object
	 */
	@Test
	public void test_AddPatientMedicalTestResult_ReturnsMedicalTestObject() {
		Patient p1 = new Patient("Sush", 22, 99595864, "1-1384", "");
		AppointmentMedicalTest test = new AppointmentMedicalTest(1, p1, "skin", "A+");
		when(appointmentRepository.findById(1)).thenReturn(test);
		when(appointmentRepository.save(Mockito.any(AppointmentMedicalTest.class))).thenReturn(test);
		AppointmentMedicalTest result = appointmentService.addPatientMedicalTestResult(1, "need to walk");
		assertEquals("need to walk", result.getPatient().getPatientTestResult());

	}
	
	/**
	 * creating findAll test case to check whether it throws Exception when list is empty
	 */
	@Test
	public void test_FindAll_ThrowsExcpetion() {
		List<AppointmentMedicalTest> list = new ArrayList<>();
		assertThrows(AppointmentMedicalTestException.class, () -> appointmentService.viewAppointmentMedicalTest());
	}
	
	/**
	 * creating addPatientMEdicalTestResult to check whether it throws exception when appointment medical test id is not found
	 */
	@Test 
	public void test_AddPatientMedicalTestResult_ThrowsExcpetion() {
		Patient p = new Patient("Sush",22,99595864,"1-1384","");
		AppointmentMedicalTest test = new AppointmentMedicalTest(3,p,"skin","A+");
		Patient p1 = new Patient("Sush", 22, 99595864, "1-1384", "");
		Patient p2 = new Patient("Poojitha", 22, 33445544, "1-22-3", "");
		List<AppointmentMedicalTest> appointmentList = new ArrayList<>();
		appointmentList.add(new AppointmentMedicalTest(1, p1, "skin", "A+"));
		appointmentList.add(new AppointmentMedicalTest(2, p2, "hair", "B-"));
		assertThrows(AppointmentMedicalTestException.class, () -> appointmentService.addPatientMedicalTestResult(3L, "need rest"));
	}
}
