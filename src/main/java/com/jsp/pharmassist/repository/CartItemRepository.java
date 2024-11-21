package com.jsp.pharmassist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.pharmassist.entity.CartItem;
import com.jsp.pharmassist.entity.Patient;

public interface CartItemRepository extends JpaRepository<CartItem, String> {

//	Optional<CartItem> findByCartIdAndMedicineId(String cartId, String medicineId);

}
