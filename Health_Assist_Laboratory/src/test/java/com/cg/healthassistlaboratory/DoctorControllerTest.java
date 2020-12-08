package com.cg.healthassistlaboratory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.catalina.mapper.Mapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;
import org.hamcrest.*;
import com.cg.healthassistlaboratory.domain.Doctor;
import com.cg.healthassistlaboratory.service.DoctorService;
import com.cg.healthassistlaboratory.service.MapValidationErrorService;
import com.cg.healthassistlaboratory.web.DoctorController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.bytebuddy.asm.Advice.Argument;

/**
 * creating DoctorControllerTest to check the Controllers provided by RESTApi's
 * by DoctorController class
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DoctorController.class)
class DoctorControllerTest {

	/**
	 * autowiring MockitoMVc to instantiate the object
	 */
	@Autowired
	MockMvc mockMvc;

	/**
	 * autowiring doctorService by @MockBean to instantiate the object
	 */
	@MockBean
	DoctorService doctorService;

	/**
	 * autowiring MapValidationErrorService by @MockBean to instantiate the object
	 */
	@MockBean
	MapValidationErrorService mapValidationErrorService;

	/**
	 * creating private ObjectMapper to convert Json to String
	 */
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * creating AddDoctor test case to check whether the doctor details are added to
	 * the database
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_AddDoctor_ReturnsDoctorJson() throws Exception {
		Doctor newdoctor = new Doctor(1, "Sindhu", "ortho", "77996600", "sindhu@gmail");
		Mockito.when(doctorService.saveDoctor(ArgumentMatchers.any())).thenReturn(newdoctor);
		String json = mapper.writeValueAsString(newdoctor);
		mockMvc.perform(post("/api/doctors/insert").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(status().isOk())
				.andExpect(jsonPath("$").isMap()).andExpect(jsonPath("doctorId").value(1))
				.andExpect(jsonPath("doctorName").value("Sindhu"))
				.andExpect(jsonPath("doctorSpecialization").value("ortho"))
				.andExpect(jsonPath("doctorPhoneNumber").value("77996600"))
				.andExpect(jsonPath("doctorEmail").value("sindhu@gmail"));

	}


	/**
	 * creating FindAll test case to check whether the list of all the doctor
	 * details are returned from the database
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_FindAll_ReturnListOfAllDoctors() throws Exception {
		List<Doctor> list = new ArrayList<>();
		list.add(new Doctor(6, "Suma", "skin", "55443322", "suma@gmail"));
		list.add(new Doctor(7, "sindhu", "hair", "55997711", "sindhu@gmail"));
		list.add(new Doctor(8, "tommy", "kidney", "11223344", "tommy@gmail.com"));
		Mockito.when(doctorService.findAllDoctors()).thenReturn(list);
		mockMvc.perform(get("/api/doctors/all")).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(3)))
				.andExpect(jsonPath("$[0].doctorName", Matchers.equalTo("Suma")));

	}

	/**
	 * creating FindBySpecialization test case to check if the list of doctor
	 * details is returned based on specialization
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_FindBySpecialization_ReturnsListOfDoctors() throws Exception {
		List<Doctor> list = new ArrayList<>();
		List<Doctor> specList = new ArrayList<>();
		list.add(new Doctor(6, "Suma", "skin", "55443322", "suma@gmail"));
		list.add(new Doctor(7, "sindhu", "hair", "55997711", "sindhu@gmail"));
		list.add(new Doctor(8, "tommy", "kidney", "11223344", "tommy@gmail.com"));
		for (Doctor d : list) {
			if (d.getDoctorSpecialization().equalsIgnoreCase("hair")) {
				specList.add(d);
			}
		}
		Mockito.when(doctorService.findByDoctorSpecialization("hair")).thenReturn(specList);
		mockMvc.perform(get("/api/doctors/spec/hair")).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].doctorName", Matchers.equalTo("sindhu")));
	}

	/**
	 * creating DeleteById test case to check if the doctor detail is removed based
	 * on doctorId
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_DeleteById_ReturnsBooleanValue() throws Exception {
		Doctor newdoctor = new Doctor(1, "Sindhu", "ortho", "77996600", "sindhu@gmail");
		Mockito.when(doctorService.remove(1)).thenReturn(true);
		mockMvc.perform(delete("/api/doctors//delete/101")).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.anything("Deleted Doctor details with Id: \"+doctorId+\"!\"")));
	}

	/**
	 * creating UpdateByName test case to check if the name is updated based on
	 * doctorId
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_UpdateByName_ReturnsUpdateDoctorDetails() throws Exception {
		Doctor doctor = new Doctor(1, "Sindhu", "ortho", "77996600", "sindhu@gmail");
		Mockito.when(doctorService.updateName(1, "Sush")).thenReturn(doctor);
		doctor.setDoctorName("Sush");
		String json = mapper.writeValueAsString(doctor);
		mockMvc.perform(patch("/api/doctors/updateName/1/Sush").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("doctorName").value("Sush"));
	}

	/**
	 * creating UpdateBySpecialization test case to check if the specialization is
	 * updated based on doctorId
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_UpdateBySpecialization_ReturnsUpdateDoctorDetails() throws Exception {
		Doctor doctor = new Doctor(1, "Sindhu", "ortho", "77996600", "sindhu@gmail");
		Mockito.when(doctorService.updateSpecialization(Mockito.anyLong(), Mockito.anyString())).thenReturn(doctor);
		doctor.setDoctorSpecialization("skin");
		String json = mapper.writeValueAsString(doctor);
		mockMvc.perform(patch("/api/doctors/updateSpec/1/skin").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("doctorSpecialization").value("skin"));
	}

	/**
	 * creating UpdateByPhoneNumber test case to check if the phone number is
	 * updated based on doctorId
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_UpdateByPhoneNumber_ReturnsUpdateDoctorDetails() throws Exception {
		Doctor doctor = new Doctor(1, "Sindhu", "ortho", "77996600", "sindhu@gmail");
		Mockito.when(doctorService.updatePhoneNumber(1, "55443311")).thenReturn(doctor);
		doctor.setDoctorPhoneNumber("55443311");
		String json = mapper.writeValueAsString(doctor);
		mockMvc.perform(patch("/api/doctors/updatePhone/1/55443311").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("doctorPhoneNumber").value("55443311"));
	}

	/**
	 * creating UpdateByEmail test case to check if the email is update based on
	 * doctorId
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_UpdateByEmail_ReturnsUpdateDoctorDetails() throws Exception {
		Doctor doctor = new Doctor(1, "Sindhu", "ortho", "77996600", "sindhu@gmail");
		Mockito.when(doctorService.updateEmail(1, "sindhuja@gmail.com")).thenReturn(doctor);
		doctor.setDoctorEmail("sindhuja@gmail.com");
		String json = mapper.writeValueAsString(doctor);
		mockMvc.perform(patch("/api/doctors/updateEmail/1/sindhuja@gmail.com").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("doctorEmail").value("sindhuja@gmail.com"));
	}

	/**
	 * creating update test case to check if the details of the doctor is updated
	 * and returns doctor object based on doctorId
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_Update_ReturnsDoctorDetails() throws Exception {
		Doctor doctor = new Doctor(5, "Sushmmitha", "Kidney", "77996600", "Sushmitha@gmail");
		Mockito.when(doctorService.update(Mockito.anyLong(), Mockito.any(Doctor.class))).thenReturn(doctor);
		doctor.setDoctorId(5);
		doctor.setDoctorName("Sushmmitha");
		doctor.setDoctorSpecialization("Kidney");
		doctor.setDoctorPhoneNumber("77996600");
		doctor.setDoctorEmail("Sushmitha@gmail");
		String json = mapper.writeValueAsString(doctor);
		mockMvc.perform(put("/api/doctors/5").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(status().isOk())
				.andExpect(jsonPath("doctorId").value(5)).andExpect(jsonPath("doctorName").value("Sushmmitha"))
				.andExpect(jsonPath("doctorSpecialization").value("Kidney"))
				.andExpect(jsonPath("doctorPhoneNumber").value("77996600"))
				.andExpect(jsonPath("doctorEmail").value("Sushmitha@gmail"));
	}
}