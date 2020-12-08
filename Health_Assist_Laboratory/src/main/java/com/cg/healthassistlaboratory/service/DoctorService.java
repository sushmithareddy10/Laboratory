package com.cg.healthassistlaboratory.service;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.healthassistlaboratory.domain.Doctor;
import com.cg.healthassistlaboratory.exception.DoctorException;
import com.cg.healthassistlaboratory.repository.DoctorRepository;

/**
 * creating DoctorService class which provides different services for doctor
 * class
 *
 */
@Service
public class DoctorService {
	/**
	 * implementing logger to find out the particular error,warns and info
	 */
	private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);
	/**
	 * declaring doctorRepository instance and autowiring helps to instantiate the
	 * object
	 */
	@Autowired
	private DoctorRepository doctorRespository;

	/**
	 * creating saveDoctor method to save the details of doctor into the doctor
	 * table
	 * 
	 * @param doctor
	 * @return
	 */
	public Doctor saveDoctor(Doctor doctor) {
		return doctorRespository.save(doctor);

	}

	/**
	 * creating findAllDoctors which returns the list of doctors
	 * 
	 * @return
	 */
	public List<Doctor> findAllDoctors() {
		List<Doctor> doctorList = doctorRespository.findAll();
		if (doctorList.isEmpty()) {
			logger.error("Could not find any Doctors records");
			throw new DoctorException("Could not find any Doctors records");
		}
		return doctorList;
	}

	/**
	 * creating findByDoctorSpecialization method which returns list of doctors
	 * based on specific specialization
	 * 
	 * @param specialization
	 * @return
	 */
	public List<Doctor> findByDoctorSpecialization(String specialization) {
		List<Doctor> specializationList = new ArrayList<>();
		List<Doctor> doctorList = doctorRespository.findAll();
		for (Doctor doctor : doctorList) {
			if (doctor.getDoctorSpecialization().equalsIgnoreCase(specialization)) {
				specializationList.add(doctor);
			}
		}
		if (specializationList.isEmpty()) {
			logger.error("Doctor with " + specialization + " not found");
			throw new DoctorException("Doctor with " + specialization + " not found");
		}
		return specializationList;
	}

	/**
	 * creating remove method which removes doctor details based on doctorId
	 * 
	 * @param doctorId
	 * @return
	 */
	public boolean remove(long doctorId) {
		Doctor doctor = doctorRespository.findById(doctorId);
		if (doctor == null) {
			logger.error("Doctor with " + doctorId + " is not found");
			throw new DoctorException("Doctor with " + doctorId + " is not found");
		}
		doctorRespository.deleteById(doctorId);
		return true;
	}

	/**
	 * creating updateName method which updates the details of doctor based on
	 * doctorId
	 * 
	 * @param doctorId
	 * @param doctorName
	 * @return
	 */
	public Doctor updateName(long doctorId, String doctorName) {
		Doctor doctor = doctorRespository.findById(doctorId);
		if (doctor == null) {
			logger.error("Doctor with " + doctorId + " is not found");
			throw new DoctorException("Doctor with " + doctorId + " is not found");
		}
		doctor.setDoctorName(doctorName);
		doctorRespository.save(doctor);
		return doctor;
	}

	/**
	 * creating updateSpecialization method which updates the doctor details based
	 * on doctorId
	 * 
	 * @param doctorId
	 * @param doctorSpecialization
	 * @return
	 */
	public Doctor updateSpecialization(long doctorId, String doctorSpecialization) {
		Doctor doctor = doctorRespository.findById(doctorId);
		if (doctor == null) {
			logger.error("Doctor with " + doctorId + " is not found");
			throw new DoctorException("Doctor with " + doctorId + " is not found");
		}
		doctor.setDoctorSpecialization(doctorSpecialization);
		return doctorRespository.save(doctor);
	}

	/**
	 * creating updatePhoneNumber method which updates the doctor details based on
	 * doctorId
	 * 
	 * @param doctorId
	 * @param doctorPhoneNumber
	 * @return
	 */
	public Doctor updatePhoneNumber(long doctorId, String doctorPhoneNumber) {
		Doctor doctor = doctorRespository.findById(doctorId);
		if (doctor == null) {
			logger.error("Doctor with " + doctorId + " is not found");
			throw new DoctorException("Doctor with " + doctorId + " is not found");
		}
		doctor.setDoctorPhoneNumber(doctorPhoneNumber);
		return doctorRespository.save(doctor);
	}

	/**
	 * creating updateEmail method which updates doctor details based on doctorId
	 * 
	 * @param doctorId
	 * @param doctorEmail
	 * @return
	 */
	public Doctor updateEmail(long doctorId, String doctorEmail) {
		Doctor doctor = doctorRespository.findById(doctorId);
		if (doctor == null) {
			logger.error("Doctor with " + doctorId + " is not found");
			throw new DoctorException("Doctor with " + doctorId + " is not found");
		}
		doctor.setDoctorEmail(doctorEmail);
		return doctorRespository.save(doctor);
	}

	/**
	 * creating update method which updates the doctor details when given doctorId
	 * and doctorObject as parameters
	 * 
	 * @param Id
	 * @param doctor
	 * @return
	 */
	public Doctor update(long Id, Doctor doctor) {
		Doctor olddoctor = doctorRespository.findById(Id);
		if (olddoctor == null) {
			logger.error("Doctor with " + Id + " is not found");
			throw new DoctorException("Doctor with " + Id + " is not found");
		}
		olddoctor.setDoctorId(doctor.getDoctorId());
		olddoctor.setDoctorName(doctor.getDoctorName());
		olddoctor.setDoctorSpecialization(doctor.getDoctorSpecialization());
		olddoctor.setDoctorPhoneNumber(doctor.getDoctorPhoneNumber());
		olddoctor.setDoctorEmail(doctor.getDoctorEmail());
		return doctorRespository.save(olddoctor);
	}
}
