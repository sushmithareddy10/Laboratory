package com.cg.healthassistlaboratory.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthassistlaboratory.domain.MedicalTest;
import com.cg.healthassistlaboratory.service.MapValidationErrorService;
import com.cg.healthassistlaboratory.service.MedicalTestService;

/**
 * creating class MedicalTestController which controls the all the serviced of
 * Medical Test Using RESTApis's
 *
 */
@RestController
@RequestMapping("/api/medicaltests")
public class MedicalTestController {
	/**
	 * autowiring medicalTestService to instantiate the object
	 */
	@Autowired
	private MedicalTestService medicalTestService;

	/**
	 * autowiring mapValidationErrorService to instantiate the object
	 */
	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	/**
	 * creating addMedicalTest method to add the medicalTest details into the
	 * database
	 * 
	 * @param medicalTest
	 * @param result
	 * @return
	 */

	@PostMapping("/insert")
	public ResponseEntity<?> addMedicalTest(@Valid @RequestBody MedicalTest medicalTest, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null) {
			return errorMap;
		}
		MedicalTest newmedicalTest = medicalTestService.saveMedicalTest(medicalTest);
		return new ResponseEntity<MedicalTest>(newmedicalTest, HttpStatus.OK);
	}

	/**
	 * creating viewMedicalTest method to view all the medicalTest from the database
	 * 
	 * @return
	 */
	@GetMapping("/all")
	public List<MedicalTest> viewMedicalTest() {
		return medicalTestService.findAll();
	}

	/**
	 * creating a removedById method to remove the medicalTest details based on
	 * medicalTestId
	 * 
	 * @param medicalTestId
	 * @return
	 */
	@DeleteMapping("/delete/{medicalTestId}")
	public ResponseEntity<?> removeById(@PathVariable("medicalTestId") long medicalTestId) {
		boolean truth = medicalTestService.remove(medicalTestId);
		return new ResponseEntity<String>("Medical Test with " + medicalTestId + " is deleted!", HttpStatus.OK);
	}

	/**
	 * creating updateByNameId to update the medicalTestName based on medicalTestId
	 * 
	 * @param medicalTestId
	 * @param medicalTestName
	 * @return
	 */
	@PatchMapping("/byName/{Id}/{name}")
	public ResponseEntity<?> updateByName(@PathVariable("Id") long medicalTestId,
			@PathVariable("name") String medicalTestName) {
		MedicalTest medicalTest = medicalTestService.updateByMedicalTestName(medicalTestId, medicalTestName);
		return new ResponseEntity<MedicalTest>(medicalTest, HttpStatus.OK);
	}

	/**
	 * creating updateByPrice to the medicalTestPrive based on medicalTestId
	 * 
	 * @param medicalTestId
	 * @param medicalTestPrice
	 * @return
	 */
	@PatchMapping("/byPrice/{Id}/{price}")
	public ResponseEntity<?> updateByPrice(@PathVariable("Id") long medicalTestId,
			@PathVariable("price") double medicalTestPrice) {
		MedicalTest medicalTest = medicalTestService.updateByMedicalTestPrice(medicalTestId, medicalTestPrice);
		return new ResponseEntity<MedicalTest>(medicalTest, HttpStatus.OK);
	}

	/**
	 * creating update method the update the details of the medicalTest
	 * 
	 * @param medicalTestId
	 * @param medicalTest
	 * @return
	 */
	@PutMapping("/{medicalTestId}")
	public ResponseEntity<?> update(@PathVariable("medicalTestId") long medicalTestId,
			@RequestBody MedicalTest medicalTest) {
		MedicalTest updateMedicalTest = medicalTestService.update(medicalTestId, medicalTest);
		return new ResponseEntity<MedicalTest>(updateMedicalTest, HttpStatus.OK);
	}

}
