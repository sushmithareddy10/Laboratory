package com.cg.healthassistlaboratory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.healthassistlaboratory.domain.AppointmentMedicalTest;

@Repository
public interface AppointmentMedicalTestRepository extends JpaRepository<AppointmentMedicalTest, Long> {
	public AppointmentMedicalTest findById(long id);
}
