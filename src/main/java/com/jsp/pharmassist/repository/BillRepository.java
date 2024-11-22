package com.jsp.pharmassist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsp.pharmassist.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, String> {

	@Query("SELECT b FROM Bill b WHERE b.patient.patientId = :patientId")
	List<Bill> findBillByPatientId(@Param("patientId") String patientId);

}
