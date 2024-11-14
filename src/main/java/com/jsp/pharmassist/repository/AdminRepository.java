package com.jsp.pharmassist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.pharmassist.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
	
	Optional<Admin> findByEmail(String email);
}

