package com.jsp.pharmassist.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.pharmassist.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, String> {

//	Optional<CartItem> findByCartIdAndMedicineId(String cartId, String medicineId);

}
