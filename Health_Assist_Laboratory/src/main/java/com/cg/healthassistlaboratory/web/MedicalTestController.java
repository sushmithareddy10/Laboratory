package com.cg.healthassistlaboratory.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthassistlaboratory.domain.MedicalTest;
import com.cg.healthassistlaboratory.service.MapValidationErrorService;
import com.cg.healthassistlaboratory.service.MedicalTestService;

@RestController
@RequestMapping("/api/medicaltests")
public class MedicalTestController {
	@Autowired
	private MedicalTestService medicalTestService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> addMedicalTest(@Valid @RequestBody MedicalTest medicalTest, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) {
			return errorMap;
		}
		MedicalTest newmedicalTest = medicalTestService.saveMedicalTest(medicalTest);
		return new ResponseEntity<MedicalTest>(newmedicalTest,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public List<MedicalTest> viewMedicalTest() {
		return medicalTestService.findAll();
	}
	
	@DeleteMapping("/delete/{medicalTestId}")
	public ResponseEntity<?> removeById(@PathVariable("medicalTestId") long medicalTestId) {
		medicalTestService.remove(medicalTestId);
		return new ResponseEntity<String>("Medical Test with "+medicalTestId+" is deleted!",HttpStatus.OK);
	}
	
	@GetMapping("/byName/{Id}/{name}")
	public ResponseEntity<?> updateByName(@PathVariable("Id") long medicalTestId,@PathVariable("name") String medicalTestName) {
		MedicalTest medicalTest = medicalTestService.updateByMedicalTestName(medicalTestId, medicalTestName);
		return new ResponseEntity<MedicalTest>(medicalTest, HttpStatus.CREATED);
	}
	
	@GetMapping("/byPrice/{Id}/{price}")
	public ResponseEntity<?> updateByName(@PathVariable("Id") long medicalTestId,@PathVariable("price") double medicalTestPrice) {
		MedicalTest medicalTest = medicalTestService.updateByMedicalTestPrice(medicalTestId, medicalTestPrice);
		return new ResponseEntity<MedicalTest>(medicalTest, HttpStatus.CREATED);
	}
	
	@PutMapping("/{medicalTestId}")
	public ResponseEntity<?> update(@PathVariable("medicalTestId") long medicalTestId, @RequestBody MedicalTest medicalTest) {
		MedicalTest updateMedicalTest =  medicalTestService.update(medicalTestId, medicalTest);
		return new ResponseEntity<MedicalTest>(updateMedicalTest,HttpStatus.CREATED);
	}

}
