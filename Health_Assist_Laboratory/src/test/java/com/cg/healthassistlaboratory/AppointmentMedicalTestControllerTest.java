package com.cg.healthassistlaboratory;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cg.healthassistlaboratory.domain.AppointmentMedicalTest;
import com.cg.healthassistlaboratory.domain.Patient;
import com.cg.healthassistlaboratory.service.AppointmentMedicalTestService;
import com.cg.healthassistlaboratory.web.AppointmentMedicalTestController;
import com.cg.healthassistlaboratory.web.DoctorController;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * creating AppointmentMedicalTestControllerTest to check the RESTApi
 * controllers provided by the Appointment Medical Test Contoller class
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AppointmentMedicalTestController.class)
@AutoConfigureMockMvc
class AppointmentMedicalTestControllerTest {

	/**
	 * autowiring Mockito mvc to instantiate the mock object
	 */
	@Autowired
	MockMvc mockMvc;

	/**
	 * autowiring appointmentService with @MockBean to instantiate the object
	 */
	@MockBean
	AppointmentMedicalTestService appointmentService;

	/**
	 * creating private ObjectMapper to covert the Json to string
	 */
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * creating viewAppointmentMedicalTest test to check whether the List is
	 * returned when RESTApi is used
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_ViewAppointmentMedicalTest_ReturnsListOfList() throws Exception {
		Patient p1 = new Patient("Sush", 22, 99595864, "1-1384", "");
		Patient p2 = new Patient("Poojitha", 22, 33445544, "1-22-3", "");
		List<AppointmentMedicalTest> appointmentList = new ArrayList<>();
		appointmentList.add(new AppointmentMedicalTest(1, p1, "skin", "A+"));
		appointmentList.add(new AppointmentMedicalTest(2, p2, "hair", "B-"));
		Mockito.when(appointmentService.viewAppointmentMedicalTest()).thenReturn(appointmentList);
		mockMvc.perform(get("/api/appointments/all")).andExpect(status().isOk());
	}

	/**
	 * creating AddAPatientTestResult test case to check whether the object is
	 * returned when test Result is added based on appointmentMedicalTestId using
	 * RESTApi
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_AddPatientTestResult_ResturnsObject() throws Exception {
		Patient p1 = new Patient("Sush", 22, 99595864, "1-1384", "");
		AppointmentMedicalTest test = new AppointmentMedicalTest(1, p1, "skin", "A+");
		when(appointmentService.addPatientMedicalTestResult(1, "needsrest")).thenReturn(test);
		test.getPatient().setPatientTestResult("needsrest");
		String json = mapper.writeValueAsString(test);
		mockMvc.perform(get("/api/appointments/1/needsrest").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
}
