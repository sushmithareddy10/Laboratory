package com.cg.healthassistlaboratory.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.healthassistlaboratory.domain.MedicalTest;
import com.cg.healthassistlaboratory.exception.MedicalTestException;
import com.cg.healthassistlaboratory.repository.MedicalTestRespository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

/**
 * creating MedicalTestService class which give various services for MedicalTest
 * class
 *
 */
@Service
public class MedicalTestService {
	/**
	 * implementing logger to find out the particular error,warns and info
	 */
	private static final Logger logger = LoggerFactory.getLogger(MedicalTestService.class);
	/**
	 * Autowiring MedicalTestRespository which instantiates medicaltestReository
	 * object
	 */
	@Autowired
	private MedicalTestRespository medicalRespository;

	/**
	 * creating saveMedicalTest method which saves the medical test details given
	 * medicalTest object
	 * 
	 * @param medicalTest
	 * @return
	 */
	public MedicalTest saveMedicalTest(MedicalTest medicalTest) {
		return medicalRespository.save(medicalTest);
	}

	/**
	 * creating findAll method which returns List of medical test details from
	 * database
	 * 
	 * @return
	 */
	public List<MedicalTest> findAll() {
		List<MedicalTest> medicalTestList = medicalRespository.findAll();
		if (medicalTestList.isEmpty()) {
			logger.error("Could not find any Medical Tests");
			throw new MedicalTestException("Could not find any Medical Tests");
		}
		return medicalTestList;
	}

	/**
	 * creating remove method which removes the details from the database given
	 * medicalTestId
	 * 
	 * @param medicalTestId
	 * @return
	 */
	public boolean remove(long medicalTestId) {
		MedicalTest medicalTest = medicalRespository.findById(medicalTestId);
		if (medicalTest == null) {
			logger.error("Medical Test with " + medicalTestId + " does not exist");
			throw new MedicalTestException("Medical Test with " + medicalTestId + " does not exist");
		}
		medicalRespository.deleteById(medicalTestId);
		return true;
	}

	/**
	 * creating updateByMedicalTestName which updates medical test name given
	 * medicalTestId and medicalTestName as parameters
	 * 
	 * @param medicalTestId
	 * @param medicalTestName
	 * @return
	 */
	public MedicalTest updateByMedicalTestName(long medicalTestId, String medicalTestName) {
		MedicalTest medicalTest = medicalRespository.findById(medicalTestId);
		if (medicalTest == null) {
			logger.error("Medical Test with " + medicalTestId + " does not exist to update");
			throw new MedicalTestException("Medical Test with " + medicalTestId + " does not exist to update");

		}
		medicalTest.setMedicalTestName(medicalTestName);
		return medicalRespository.save(medicalTest);

	}

	/**
	 * creating updateByMedicalTestPrice which updates the medicalTestPrice given
	 * medicalTestId and medicalTestPrice as parameters
	 * 
	 * @param medicalTestId
	 * @param medicalTestPrice
	 * @return
	 */
	public MedicalTest updateByMedicalTestPrice(long medicalTestId, double medicalTestPrice) {
		MedicalTest medicalTest = medicalRespository.findById(medicalTestId);
		if (medicalTest == null) {
			logger.error("Medical Test with " + medicalTestId + " does not exist to update");
			throw new MedicalTestException("Medical Test with " + medicalTestId + " does not exist to update");

		}
		medicalTest.setMedicalTestPrice(medicalTestPrice);
		return medicalRespository.save(medicalTest);

	}

	/**
	 * creating update method which updates the medical test details given
	 * medicalTestId and medicalTest object
	 * 
	 * @param medicalTestId
	 * @param medicalTest
	 * @return
	 */
	public MedicalTest update(long medicalTestId, MedicalTest medicalTest) {
		MedicalTest oldMedicalTest = medicalRespository.findById(medicalTestId);
		if (oldMedicalTest == null) {
			logger.error("Medical Test with " + medicalTestId + " does not exist to update");
			throw new MedicalTestException("Medical Test with " + medicalTestId + " does not exist to update");

		}
		oldMedicalTest.setMedicalTestId(medicalTest.getMedicalTestId());
		oldMedicalTest.setMedicalTestName(medicalTest.getMedicalTestName());
		oldMedicalTest.setMedicalTestPrice(medicalTest.getMedicalTestPrice());
		return medicalRespository.save(oldMedicalTest);
	}

}
