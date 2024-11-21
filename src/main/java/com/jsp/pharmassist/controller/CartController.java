package com.jsp.pharmassist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.pharmassist.entity.Cart;
import com.jsp.pharmassist.service.CartService;
import com.jsp.pharmassist.util.AppResponseBuilder;
import com.jsp.pharmassist.util.ResponseStructure;

@RestController
public class CartController {

	private final CartService cartService;
	private final AppResponseBuilder appResponseBuilder;
	public CartController(CartService cartService, AppResponseBuilder appResponseBuilder) {
		super();
		this.cartService = cartService;
		this.appResponseBuilder = appResponseBuilder;
	}


	@PostMapping("/cart/add")
	public ResponseEntity<ResponseStructure<Cart>> addMedicineToCart(@RequestParam String patientId,
            @RequestParam String medicineId,@RequestParam int quantity){
		
		Cart cart = cartService.addMedicineToCart(patientId,medicineId,quantity);
		return appResponseBuilder.success(HttpStatus.OK, "Added To The Cart", cart);
	}
}
