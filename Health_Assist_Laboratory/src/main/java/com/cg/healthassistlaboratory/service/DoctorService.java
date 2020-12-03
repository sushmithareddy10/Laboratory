package com.cg.healthassistlaboratory.service;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.healthassistlaboratory.domain.Doctor;
import com.cg.healthassistlaboratory.exception.DoctorException;
import com.cg.healthassistlaboratory.repository.DoctorRepository;

/**
 * creating DoctorService class which provides different services for doctor class
 *
 */
@Service
public class DoctorService {
	/**
	 * declaring doctorRepository instance and autowiring helps to instantiate the object
	 */
	@Autowired
	private DoctorRepository doctorRespository;
	
	/**
	 * creating saveDoctor method to save the details of doctor into the doctor table
	 * @param doctor
	 * @return
	 */
	public Doctor saveDoctor(Doctor doctor) {
		return doctorRespository.save(doctor);
		
	}
	
	public List<Doctor> findAllDoctors() {
		List<Doctor> doctorList = doctorRespository.findAll();
		if(doctorList.isEmpty()) {
			throw new DoctorException("Could not find any Doctors records");
		}
		return doctorList;
	}
	
	
	public List<Doctor> findByDoctorSpecialization(String specialization){
		List<Doctor> specializationList = new ArrayList<>();
		List<Doctor> doctorList = doctorRespository.findAll();
		for(Doctor doctor : doctorList) {
			if(doctor.getDoctorSpecialization().equalsIgnoreCase(specialization)) {
				specializationList.add(doctor);
			}
		}
		if(specializationList.isEmpty()) {
			throw new DoctorException("Doctor with "+specialization+" not found");
		}
		return specializationList;
	}
	
	public void remove(long doctorId) {
		Doctor doctor = doctorRespository.findById(doctorId);
		if(doctor==null) {
			throw new DoctorException("Doctor with "+doctorId+ " is not found");
		}
		doctorRespository.deleteById(doctorId);
		
	}
	
	public Doctor updateName(long doctorId, String doctorName) {
		Doctor doctor = doctorRespository.findById(doctorId);
		if(doctor==null) {
			throw new DoctorException("Doctor with "+doctorId+ " is not found");
		}
		doctor.setDoctorName(doctorName);
		return doctorRespository.save(doctor);
	}
	
	public Doctor updateSpecialization(long doctorId, String doctorSpecialization) {
		Doctor doctor = doctorRespository.findById(doctorId);
		if(doctor==null) {
			throw new DoctorException("Doctor with "+doctorId+ " is not found");
		}
		doctor.setDoctorSpecialization(doctorSpecialization);
		return doctorRespository.save(doctor);
	}
	
	public Doctor updatePhoneNumber(long doctorId, String doctorPhoneNumber) {
		Doctor doctor = doctorRespository.findById(doctorId);
		if(doctor==null) {
			throw new DoctorException("Doctor with "+doctorId+ " is not found");
		}
		doctor.setDoctorPhoneNumber(doctorPhoneNumber);
		return doctorRespository.save(doctor);
	}
	
	public Doctor updateEmail(long doctorId, String doctorEmail) {
		Doctor doctor = doctorRespository.findById(doctorId);
		if(doctor==null) {
			throw new DoctorException("Doctor with "+doctorId+ " is not found");
		}
		doctor.setDoctorEmail(doctorEmail);
		return doctorRespository.save(doctor);
	}
	
	public Doctor update(long Id, Doctor doctor) {
		Doctor olddoctor = doctorRespository.findById(Id);
		if(olddoctor==null) {
			throw new DoctorException("Doctor with "+Id+ " is not found");
		}
		BeanUtils.copyProperties(doctor, olddoctor, "Id");
		return doctorRespository.save(doctor);
	}
}
