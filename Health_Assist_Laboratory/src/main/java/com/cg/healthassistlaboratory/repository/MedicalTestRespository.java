package com.cg.healthassistlaboratory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.healthassistlaboratory.domain.MedicalTest;

@Repository
public interface MedicalTestRespository extends JpaRepository<MedicalTest, Long>{
	public MedicalTest findById(long medicalTestId);
}
