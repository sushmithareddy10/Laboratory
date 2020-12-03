package com.cg.healthassistlaboratory.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.healthassistlaboratory.domain.MedicalTest;
import com.cg.healthassistlaboratory.exception.MedicalTestException;
import com.cg.healthassistlaboratory.repository.MedicalTestRespository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class MedicalTestService {
	
	@Autowired
	private MedicalTestRespository medicalRespository;
	
	public MedicalTest saveMedicalTest(MedicalTest medicalTest) {
		return medicalRespository.save(medicalTest);
	}
	
	public List<MedicalTest> findAll() {
		List<MedicalTest> medicalTestList = medicalRespository.findAll();
		if(medicalTestList.isEmpty()) {
			throw new MedicalTestException("Could not find any Medical Tests");
		}
		return medicalTestList;
	}
	

	public MedicalTest remove(long medicalTestId) {
		MedicalTest medicalTest = medicalRespository.findById(medicalTestId);
		if(medicalTest==null) {
			throw new MedicalTestException("Medical Test with "+medicalTestId+" does not exist");
		}
		 medicalRespository.deleteById(medicalTestId);	
		 return medicalTest;
	}
	
	public MedicalTest updateByMedicalTestName(long medicalTestId, String medicalTestName) {
		MedicalTest medicalTest = medicalRespository.findById(medicalTestId);
		if(medicalTest==null) {
			throw new MedicalTestException("Medical Test with "+medicalTestId+" does not exist to update");
			
		}
		medicalTest.setMedicalTestName(medicalTestName);
		return medicalRespository.save(medicalTest);
		
	}
	
	public MedicalTest updateByMedicalTestPrice(long medicalTestId, double medicalTestPrice) {
		MedicalTest medicalTest = medicalRespository.findById(medicalTestId);
		if(medicalTest==null) {
			throw new MedicalTestException("Medical Test with "+medicalTestId+" does not exist to update");
			
		}
		medicalTest.setMedicalTestPrice(medicalTestPrice);
		return medicalRespository.save(medicalTest);
		
	}
	
	
	public MedicalTest update(long medicalTestId, MedicalTest medicalTest) {
		MedicalTest oldMedicalTest = medicalRespository.findById(medicalTestId);
		if(medicalTest==null) {
			throw new MedicalTestException("Medical Test with "+medicalTestId+" does not exist to update");
			
		}
		BeanUtils.copyProperties(medicalTest, oldMedicalTest, "Id");
		return medicalTest;
	}

}
