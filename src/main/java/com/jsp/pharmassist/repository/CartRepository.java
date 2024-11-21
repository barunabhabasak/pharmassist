package com.jsp.pharmassist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.pharmassist.entity.Cart;
import com.jsp.pharmassist.entity.Patient;

public interface CartRepository extends JpaRepository<Cart, String>{

	Optional<Cart> findByPatientId(String patientId);

}
