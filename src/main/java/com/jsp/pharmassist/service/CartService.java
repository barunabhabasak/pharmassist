package com.jsp.pharmassist.service;

import org.springframework.stereotype.Service;

import com.jsp.pharmassist.entity.Cart;
import com.jsp.pharmassist.entity.CartItem;
import com.jsp.pharmassist.entity.Medicine;
import com.jsp.pharmassist.entity.Patient;
import com.jsp.pharmassist.exception.InsufficientStockException;
import com.jsp.pharmassist.exception.MedicineNotFoundByIdException;
import com.jsp.pharmassist.exception.PatientNotFoundById;
import com.jsp.pharmassist.repository.CartItemRepository;
import com.jsp.pharmassist.repository.CartRepository;
import com.jsp.pharmassist.repository.MedicineRepository;
import com.jsp.pharmassist.repository.PatientRespository;

@Service
public class CartService {

	private final CartRepository cartRepository;
	private final MedicineRepository medicineRepository;
	private final CartItemRepository cartItemRepository;
	private final PatientRespository patientRespository;


	public CartService(CartRepository cartRepository, MedicineRepository medicineRepository,
			CartItemRepository cartItemRepository,PatientRespository patientRespository) {
		super();
		this.cartRepository = cartRepository;
		this.medicineRepository = medicineRepository;
		this.cartItemRepository = cartItemRepository;
		this.patientRespository = patientRespository;
	}

	public Cart addMedicineToCart(String patientId, String medicineId, int quantity) {

		Patient patient = patientRespository.findById(patientId)
				.orElseThrow(() -> new PatientNotFoundById("Patient not found"));

		Medicine medicine = medicineRepository.findById(medicineId)
				.orElseThrow(()-> new MedicineNotFoundByIdException("Medicine is not founf by Id"));

		if (medicine.getStockQuantity() < quantity) {
			throw new InsufficientStockException("Insufficient stock");
		}

		Cart cart = cartRepository.findByPatientId(patientId)
				.orElseGet(()->{
					Cart newCart = new Cart();
					newCart.setPatientId(patientId);
					return cartRepository.save(newCart);
				});
		
		CartItem cartItem = new CartItem();
		cartItem.setMedicineId(medicineId);
		cartItem.setQuantity(quantity);
		cartItem.setPrice(medicine.getPrice() * quantity);
		cartItem.setCart(cart);
		
		medicine.setStockQuantity(medicine.getStockQuantity() - quantity);
		medicineRepository.save(medicine);
		
		cart.getCartItems().add(cartItem);
		cart.setTotalItemsPrice(cart.getTotalItemsPrice() + cartItem.getPrice());
		
		cartItemRepository.save(cartItem);
		
		return cartRepository.save(cart);
		
	}

}
