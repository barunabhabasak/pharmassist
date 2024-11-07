package com.jsp.pharmassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.pharmassist.entity.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, String> {
	

}
