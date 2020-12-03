package com.cg.healthassistlaboratory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.healthassistlaboratory.domain.Doctor;
/**
 * creating DoctorRespository Interface which extends JpaRespository which provides CRUD operations
 *
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	public List<Doctor> findByDoctorSpecialization(String specialization);
	public Doctor findById(long doctorId);
	

}
