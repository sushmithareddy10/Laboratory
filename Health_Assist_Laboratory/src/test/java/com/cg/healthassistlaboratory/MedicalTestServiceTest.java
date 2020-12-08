package com.cg.healthassistlaboratory;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.cg.healthassistlaboratory.domain.Doctor;
import com.cg.healthassistlaboratory.domain.MedicalTest;
import com.cg.healthassistlaboratory.exception.MedicalTestException;
import com.cg.healthassistlaboratory.repository.DoctorRepository;
import com.cg.healthassistlaboratory.repository.MedicalTestRespository;
import com.cg.healthassistlaboratory.service.DoctorService;
import com.cg.healthassistlaboratory.service.MedicalTestService;

/**
 * creating class MedicalTestServiceTest which tests the behavior and
 * functionalities of the services of Medical Test class
 *
 */
@ExtendWith(MockitoExtension.class)
class MedicalTestServiceTest {

	/**
	 * creating medicaltestRepository to instantiate the object by using @Mock
	 * annotation
	 */
	@Mock
	private MedicalTestRespository medicalRepository;

	/**
	 * creating the medicalTestService to instantiate the object by
	 * using @InjectMocks annotation
	 */
	@InjectMocks
	private MedicalTestService medicalTestService;

	/**
	 * creating save test case to save the details of medical test and returns the
	 * medical test object
	 */
	@Test
	public void test_Save_GivenMedicaTestObject() {
		MedicalTest medicalTest = new MedicalTest(1, "ortho", 2000.0);
		when(medicalRepository.save(Mockito.any(MedicalTest.class))).thenReturn(medicalTest);
		MedicalTest medicalTestFound = medicalTestService.saveMedicalTest(medicalTest);
		assertEquals(medicalTest.getMedicalTestName(), medicalTestFound.getMedicalTestName());
	}

	/**
	 * creating findAll test case to check whether the list of medical test is being
	 * returned
	 */
	@Test
	public void test_FindAll_ReturnsListOfMedicalTests() {
		List<MedicalTest> list = new ArrayList<>();
		list.add(new MedicalTest(1, "ortho", 2000.0));
		list.add(new MedicalTest(2, "skin", 3500.0));
		list.add(new MedicalTest(2, "heart", 5200.0));
		when(medicalRepository.findAll()).thenReturn(list);
		List<MedicalTest> medicalTestList = medicalTestService.findAll();
		assertEquals(list.size(), medicalTestList.size());
	}

	/**
	 * creating remove test case to check whether the medical test is being removed
	 */
	@Test
	public void test_Remove_ReturnsABooleanValue() {
		MedicalTest medicalTest = new MedicalTest(1, "ortho", 2000.0);
		Mockito.when(medicalRepository.findById(1L)).thenReturn(medicalTest).thenReturn(null);
		boolean result = medicalTestService.remove(1);
		assertThat(result);
	}

	/**
	 * creating updateByMedicalTestName test case to check whether the name medical
	 * test name is updated by returning object
	 */
	@Test
	public void test_UpdateByMedicalTestName_GivenMedicalTestName() {
		MedicalTest medicalTest = new MedicalTest(2, "skin", 3000.0);
		when(medicalRepository.findById(2)).thenReturn(medicalTest);
		when(medicalRepository.save(Mockito.any(MedicalTest.class))).thenReturn(medicalTest);
		MedicalTest resultTest = medicalTestService.updateByMedicalTestName(2, "ortho");
		assertEquals("ortho", resultTest.getMedicalTestName());
	}

	/**
	 * creating updateByMedicalTestPrice test case to check whether the medical test
	 * price is updated by returning object
	 */
	@Test
	public void test_UpdateByMedicalTestPrice_GivenMedicalTestPrice() {
		MedicalTest medicalTest = new MedicalTest(1, "skin", 3000.0);
		when(medicalRepository.findById(1)).thenReturn(medicalTest);
		when(medicalRepository.save(Mockito.any(MedicalTest.class))).thenReturn(medicalTest);
		MedicalTest resultTest = medicalTestService.updateByMedicalTestPrice(1, 4000.0);
		assertEquals(4000.0, resultTest.getMedicalTestPrice());
	}

	/**
	 * creating update test case to update the medical test details
	 */
	@Test
	public void test_Update_GivenMedicalTestObject() {
		MedicalTest medicalTest = new MedicalTest(3, "hair", 4000.0);
		when(medicalRepository.findById(3)).thenReturn(medicalTest);
		when(medicalRepository.save(Mockito.any(MedicalTest.class))).thenReturn(medicalTest);
		MedicalTest resultTest = medicalTestService.update(3L, medicalTest);
		assertEquals(medicalTest.getMedicalTestName(), resultTest.getMedicalTestName());

	}
	
	/**
	 * creating findAll test case to check whether it throws exception when list is empty
	 */
	@Test
	public void test_FindAll_ThrowsException() {
		List<MedicalTest> list = new ArrayList<>();
		list.add(new MedicalTest(1, "ortho", 2000.0));
		list.add(new MedicalTest(2, "skin", 3500.0));
		list.add(new MedicalTest(3, "heart", 5200.0));
		assertThrows(MedicalTestException.class, () -> medicalTestService.findAll());
	}
	
	/**
	 * creating remove test case to check whether it throws excpetion when medical Test Id is not found
	 */
	@Test
	public void test_Remove_ThrowsException() {
		List<MedicalTest> list = new ArrayList<>();
		list.add(new MedicalTest(1, "ortho", 2000.0));
		list.add(new MedicalTest(2, "skin", 3500.0));
		list.add(new MedicalTest(3, "heart", 5200.0));
		assertThrows(MedicalTestException.class, () -> medicalTestService.remove(4L));
	}
	
	/**
	 * creatinf upadteMedicalTestName to check whether it throws excpetion when medical test id is not found
	 */
	@Test
	public void test_UpdateMedicalTestName_ThrowsException() {
		List<MedicalTest> list = new ArrayList<>();
		list.add(new MedicalTest(1, "ortho", 2000.0));
		list.add(new MedicalTest(2, "skin", 3500.0));
		list.add(new MedicalTest(3, "heart", 5200.0));
		assertThrows(MedicalTestException.class, () -> medicalTestService.updateByMedicalTestName(4, "ortho"));
	}
	
	/**
	 * creating medicalTestPrice to check whether it throws exception when medicalTest id not found
	 */
	@Test
	public void test_UpdateMedicalTestPrice_ThrowsException() {
		List<MedicalTest> list = new ArrayList<>();
		list.add(new MedicalTest(1, "ortho", 2000.0));
		list.add(new MedicalTest(2, "skin", 3500.0));
		list.add(new MedicalTest(3, "heart", 5200.0));
		assertThrows(MedicalTestException.class, () ->  medicalTestService.updateByMedicalTestPrice(4, 300.0));
	}
	
	/**
	 * creating update testcase to check if it throws excpetion when medical test id is not found
	 */
	@Test
	public void test_Update_ThrowsException() {
		MedicalTest medicalTest = new MedicalTest(4, "ortho", 2000.0);
		List<MedicalTest> list = new ArrayList<>();
		list.add(new MedicalTest(1, "ortho", 2000.0));
		list.add(new MedicalTest(2, "skin", 3500.0));
		list.add(new MedicalTest(3, "heart", 5200.0));
		assertThrows(MedicalTestException.class, () -> medicalTestService.update(4, medicalTest));
		
	}
}
