package com.jsp.pharmassist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.pharmassist.entity.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, String> {

	List<Medicine>	findByNameLikeIgnoreCaseOrIngredientsLikeIgnoreCase(String name, String ingredients);

}
