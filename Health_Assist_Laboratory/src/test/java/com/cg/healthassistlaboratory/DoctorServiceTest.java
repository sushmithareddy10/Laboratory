package com.cg.healthassistlaboratory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import com.cg.healthassistlaboratory.domain.Doctor;
import com.cg.healthassistlaboratory.exception.DoctorException;
import com.cg.healthassistlaboratory.repository.DoctorRepository;
import com.cg.healthassistlaboratory.service.DoctorService;

/**
 * creating DoctorServiceTest class to test the behavior and functionalities of
 * the services of Doctor Service test class
 *
 */
@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {
	/**
	 * creating a doctorRespository object and instantiating it by using the @Mock
	 * annotation
	 */
	@Mock
	private DoctorRepository doctorRepository;

	/**
	 * creating a doctorService object and instantiating it by using @InjectMocks
	 * annotation
	 */
	@InjectMocks
	private DoctorService doctorService;

	/**
	 * creating Save test case to check whether the details are being saved and
	 * returning the doctor object
	 */
	@Test
	public void test_Save_GivenDoctor() {
		Doctor newdoctor = new Doctor(5, "Sindhu", "liver", "77996600", "sindhu@gmail");
		when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(newdoctor);
		Doctor doctorFound = doctorService.saveDoctor(newdoctor);
		assertEquals("Sindhu", doctorFound.getDoctorName());
	}

	/**
	 * creating findAll test case to check whether the details are being fetched and
	 * returning the list
	 */
	@Test
	public void test_FindAll_ReturnsListOfDoctors() {
		List<Doctor> list = new LinkedList<>();
		list.add(new Doctor(6, "Suma", "skin", "55443322", "suma@gmail"));
		list.add(new Doctor(7, "sindhu", "hair", "55997711", "sindhu@gmail"));
		list.add(new Doctor(8, "tommy", "kidney", "11223344", "tommy@gmail.com"));
		when(doctorRepository.findAll()).thenReturn(list);
		List result = doctorService.findAllDoctors();
		assertEquals(list.size(), result.size());
	}

	/**
	 * creating findBySpecialization test case to check whether the list of doctors
	 * based on specialization is fetched
	 */
	@Test
	public void test_FindBySpecialization_ReturnDoctorObject() {
		List<Doctor> list = new ArrayList<>();
		list.add(new Doctor(6, "Suma", "skin", "55443322", "suma@gmail"));
		list.add(new Doctor(7, "sindhu", "skin", "55997711", "sindhu@gmail"));
		list.add(new Doctor(8, "tommy", "kidney", "11223344", "tommy@gmail.com"));
		when(doctorRepository.findAll()).thenReturn(list);
		List<Doctor> result = doctorService.findByDoctorSpecialization("skin");
		assertEquals(2, result.size());

	}

	/**
	 * creating removeDoctor test case to check whether the details being removed
	 * from the database
	 */
	@Test
	public void test_RemoveDoctor_GivenDoctorId() {
		Doctor newdoctor = new Doctor(5, "Sushmmitha", "Kidney", "77996600", "Sushmitha@gmail");
		Mockito.when(doctorRepository.findById(5L)).thenReturn(newdoctor).thenReturn(null);
		boolean result = doctorService.remove(5);
		assertThat(result);

	}

	/**
	 * creating update test case to test whether the details are being updated or
	 * not
	 */
	@Test
	public void test_Update_GivenDoctorObject() {
		Doctor newdoctor = new Doctor(5, "Sushmmitha", "Kidney", "77996600", "Sushmitha@gmail");
		when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(newdoctor);
		when(doctorRepository.findById(5)).thenReturn(newdoctor);
		Doctor result = doctorService.update(5L, newdoctor);
		assertEquals(newdoctor.getDoctorName(), result.getDoctorName());
	}

	/**
	 * creating updateByName test case to check whether the name is updated by
	 * returning object
	 */
	@Test
	public void test_UpdateName_GivenDoctorName() {
		Doctor doctor = new Doctor(7, "sindhu", "skin", "55997711", "sindhu@gmail");
		when(doctorRepository.findById(7)).thenReturn(doctor);
		when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(doctor);
		Doctor newDoctor = doctorService.updateName(7, "Sush");
		assertEquals("Sush", newDoctor.getDoctorName());
	}

	@Test
	public void test_UpdateSpecialization_GivenDoctorSpecialization() {
		Doctor doctor = new Doctor(1, "sindhu", "skin", "55997711", "sindhu@gmail");
		when(doctorRepository.findById(1)).thenReturn(doctor);
		when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(doctor);
		Doctor newDoctor = doctorService.updateSpecialization(1, "ortho");
		assertEquals("ortho", newDoctor.getDoctorSpecialization());
	}

	/**
	 * creating updateByPhoneNumber test case to check whether the phoneNumber is
	 * being updated by returning object
	 */
	@Test
	public void test_UpdatePhoneNumber_GivenDoctorPhoneNumber() {
		Doctor doctor = new Doctor(3, "sindhu", "skin", "55997711", "sindhu@gmail");
		when(doctorRepository.findById(3)).thenReturn(doctor);
		when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(doctor);
		Doctor newDoctor = doctorService.updatePhoneNumber(3, "77334411");
		assertEquals("77334411", newDoctor.getDoctorPhoneNumber());
	}

	/**
	 * creating updateEmail test case to check whether the email is updated by
	 * returning the object
	 */
	@Test
	public void test_UpdateEmail_GivenDoctorEmail() {
		Doctor doctor = new Doctor(2, "sindhu", "skin", "55997711", "sindhu@gmail");
		when(doctorRepository.findById(2)).thenReturn(doctor);
		when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(doctor);
		Doctor newDoctor = doctorService.updateEmail(2, "sindhuja@gmail.com");
		assertEquals("sindhuja@gmail.com", newDoctor.getDoctorEmail());
	}
	
	/**
	 * creating a findAlltest case to check whether the exception is thrown when list is empty
	 */
	@Test
	public void test_FindALL_ThrowsExceptionIfListIsEmpty() {
		List<Doctor> list = new ArrayList<>();
		assertThrows(DoctorException.class, () -> doctorService.findAllDoctors());
			
	}
	
	/**
	 * creating a FindByDoctorSpecialization test case to check whether the exception is thrown when specialization is not found
	 */
	@Test
	public void test_FindByDoctorSpecialization_ThrowsException() {
		List<Doctor> list = new ArrayList<>();
		list.add(new Doctor(6, "Suma", "skin", "55443322", "suma@gmail"));
		list.add(new Doctor(7, "sindhu", "skin", "55997711", "sindhu@gmail"));
		list.add(new Doctor(8, "tommy", "kidney", "11223344", "tommy@gmail.com"));
		assertThrows(DoctorException.class, () ->  doctorService.findByDoctorSpecialization("ortho"));
	}
	
	/**
	 * creating remove test case to check whether exception is thrown when doctorId is not found
	 */
	@Test
	public void test_Remove_ThrowsExcpetion() {
		List<Doctor> list = new ArrayList<>();
		list.add(new Doctor(6, "Suma", "skin", "55443322", "suma@gmail"));
		list.add(new Doctor(7, "sindhu", "skin", "55997711", "sindhu@gmail"));
		list.add(new Doctor(8, "tommy", "kidney", "11223344", "tommy@gmail.com"));
		assertThrows(DoctorException.class, () -> doctorService.remove(1));
	}
	
	/**
	 * creating updateName test case to check whether the exception is thrown when doctorId is not found
	 */
	@Test
	public void test_UpdateName_ThrowsException() {
		List<Doctor> list = new ArrayList<>();
		list.add(new Doctor(6, "Suma", "skin", "55443322", "suma@gmail"));
		list.add(new Doctor(7, "sindhu", "skin", "55997711", "sindhu@gmail"));
		list.add(new Doctor(8, "tommy", "kidney", "11223344", "tommy@gmail.com"));
		assertThrows(DoctorException.class, () -> doctorService.updateName(1, "sush"));
	}
	
	/**
	 * creating updateSpecialization test case to check whether the exception is thrown when doctorId is not found
	 */
	@Test
	public void test_UpdateSpecialization_ThrowsException() {
		List<Doctor> list = new ArrayList<>();
		list.add(new Doctor(6, "Suma", "skin", "55443322", "suma@gmail"));
		list.add(new Doctor(7, "sindhu", "skin", "55997711", "sindhu@gmail"));
		list.add(new Doctor(8, "tommy", "kidney", "11223344", "tommy@gmail.com"));
		assertThrows(DoctorException.class, () -> doctorService.updateSpecialization(1, "ortho"));
	}
	
	/**
	 * creating updatePhoneNumber test case to check whether the exception is thrown when doctorId is not found
	 */
	@Test
	public void test_UpdatePhoneNumber_ThrowsException() {
		List<Doctor> list = new ArrayList<>();
		list.add(new Doctor(6, "Suma", "skin", "55443322", "suma@gmail"));
		list.add(new Doctor(7, "sindhu", "skin", "55997711", "sindhu@gmail"));
		list.add(new Doctor(8, "tommy", "kidney", "11223344", "tommy@gmail.com"));
		assertThrows(DoctorException.class, () -> doctorService.updatePhoneNumber(1, "2233115"));
	}
	
	/**
	 * creating updateEmail test case to check whether the exception is thrown when the doctorId is not found
	 */
	@Test
	public void test_UpdateEmail_ThrowsException() {
		List<Doctor> list = new ArrayList<>();
		list.add(new Doctor(6, "Suma", "skin", "55443322", "suma@gmail"));
		list.add(new Doctor(7, "sindhu", "skin", "55997711", "sindhu@gmail"));
		list.add(new Doctor(8, "tommy", "kidney", "11223344", "tommy@gmail.com"));
		assertThrows(DoctorException.class, () -> doctorService.updateEmail(1, "sush@gmail.com"));
	}
	
	/**
	 * creating update test case to check whether the exception is thrown when the doctorId is not found
	 */
	@Test
	public void test_Update_ThrowsException() {
		Doctor doctor = new Doctor(1, "Suma", "skin", "55443322", "suma@gmail");
		List<Doctor> list = new ArrayList<>();
		list.add(new Doctor(6, "Suma", "skin", "55443322", "suma@gmail"));
		list.add(new Doctor(7, "sindhu", "skin", "55997711", "sindhu@gmail"));
		list.add(new Doctor(8, "tommy", "kidney", "11223344", "tommy@gmail.com"));
		assertThrows(DoctorException.class, () -> doctorService.update(1, doctor));
	}
}
