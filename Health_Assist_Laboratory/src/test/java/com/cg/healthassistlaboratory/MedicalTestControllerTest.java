package com.cg.healthassistlaboratory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.apache.catalina.mapper.Mapper;
import org.hamcrest.Matchers;

import com.cg.healthassistlaboratory.domain.MedicalTest;
import com.cg.healthassistlaboratory.service.MapValidationErrorService;
import com.cg.healthassistlaboratory.service.MedicalTestService;
import com.cg.healthassistlaboratory.web.MedicalTestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * creating MedicalTestControllerTest to check the controllers provide by
 * MedicalTestController using RESTApi
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MedicalTestController.class)
class MedicalTestControllerTest {
	/**
	 * autowiring Mocked mvc to instantiate the object
	 */
	@Autowired
	MockMvc mockMvc;

	/**
	 * autowriring medicalTestService @MockBean to instantiate the object
	 */
	@MockBean
	MedicalTestService medicalTestService;

	/**
	 * creating mapValidationErrorService @MockBean to instantiate the object
	 */
	@MockBean
	MapValidationErrorService mapValidationErrorService;

	/**
	 * creating a private static Object Mapper to convert Json to String
	 */
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * creating AddMedoicalTest test case to check if the medical test details are
	 * being added to the database and returning the medical test object
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_AddMedicalTest_GivenMedicalTestObject() throws Exception {
		MedicalTest medicalTest = new MedicalTest(1, "ortho", 200.0);
		when(medicalTestService.saveMedicalTest(ArgumentMatchers.any())).thenReturn(medicalTest);
		String json = mapper.writeValueAsString(medicalTest);
		mockMvc.perform(post("/api/medicaltests/insert").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("medicalTestId").value(1)).andExpect(jsonPath("medicalTestName").value("ortho"))
				.andExpect(jsonPath("medicalTestPrice").value(200.0));

	}

	/**
	 * creating FindAll test case to check if the list of medical test is returned
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_FindAll_ReturnsListOfMedicalTests() throws Exception {
		List<MedicalTest> list = new ArrayList<>();
		list.add(new MedicalTest(1, "ortho", 2000.0));
		list.add(new MedicalTest(2, "skin", 3500.0));
		list.add(new MedicalTest(2, "heart", 5200.0));
		when(medicalTestService.findAll()).thenReturn(list);
		mockMvc.perform(get("/api/medicaltests/all")).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(3)))
				.andExpect(jsonPath("$[0].medicalTestName", Matchers.equalTo("ortho")));
	}

	/**
	 * creating remove test case to check if the details are removed from the
	 * database based on medicalTestId
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_Remove_ReturnsBooelanvalue() throws Exception {
		MedicalTest medicalTest = new MedicalTest(1, "ortho", 200.0);
		when(medicalTestService.remove(1)).thenReturn(true);
		mockMvc.perform(delete("/api/medicaltests/delete/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.anything("Medical Test with \"+medicalTestId+\" is deleted!")));
	}

	/**
	 * creating UpdateByName to check if the name is updated and returnes object
	 * based on medicalTestId
	 * 
	 * @throws Exception
	 */
	@Test
	void test_UpdateByName_ReturnsMedicalTestObject() throws Exception {
		MedicalTest medicalTest = new MedicalTest(2, "ortho", 300.0);
		when(medicalTestService.updateByMedicalTestName(2, "skin")).thenReturn(medicalTest);
		medicalTest.setMedicalTestName("skin");
		String json = mapper.writeValueAsString(medicalTest);
		mockMvc.perform(patch("/api/medicaltests/byName/2/skin").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.medicalTestName").value("skin"));

	}

	/**
	 * creating UpdateByPrice test case to check if the price is updated and returns
	 * object based on medicalTestId
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_UpdateByPrice_ReturnsMedicalTestObject() throws Exception {
		MedicalTest medicalTest = new MedicalTest(2, "ortho", 300.0);
		when(medicalTestService.updateByMedicalTestPrice(2, 400.0)).thenReturn(medicalTest);
		medicalTest.setMedicalTestPrice(400.0);
		String json = mapper.writeValueAsString(medicalTest);
		mockMvc.perform(patch("/api/medicaltests/byPrice/2/400.0").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.medicalTestPrice").value(400.0));

	}

	/**
	 * creating Update test case to check if the medical test details are updated
	 * based on medicalTestId
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_Update_ReturnsMedicalTestObject() throws Exception {
		MedicalTest medicalTest = new MedicalTest(2, "ortho", 300.0);
		when(medicalTestService.update(Mockito.anyLong(), Mockito.any(MedicalTest.class))).thenReturn(medicalTest);
		medicalTest.setMedicalTestId(2);
		medicalTest.setMedicalTestName("skin");
		medicalTest.setMedicalTestPrice(400.0);
		String json = mapper.writeValueAsString(medicalTest);
		mockMvc.perform(put("/api/medicaltests/2").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("medicalTestId").value(2)).andExpect(jsonPath("medicalTestName").value("skin"));

	}

}
