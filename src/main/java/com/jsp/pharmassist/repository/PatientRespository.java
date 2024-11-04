package com.jsp.pharmassist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.pharmassist.entity.Patient;

public interface PatientRespository extends JpaRepository<Patient, String> {

	List<Patient> findAllByPharmacy_PharmacyId(String pharmacyId);

}
