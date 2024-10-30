package com.jsp.pharmassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.pharmassist.entity.Pharmacy;

@Repository
public interface PharmcyRepository extends JpaRepository<Pharmacy, String>{

}
