package com.jsp.pharmassist.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jsp.pharmassist.entity.Bill;
import com.jsp.pharmassist.entity.Cart;
import com.jsp.pharmassist.entity.CartItem;
import com.jsp.pharmassist.entity.Patient;
import com.jsp.pharmassist.exception.PatientNotFoundById;
import com.jsp.pharmassist.mapper.BillMapper;
import com.jsp.pharmassist.repository.BillRepository;
import com.jsp.pharmassist.repository.CartItemRepository;
import com.jsp.pharmassist.repository.CartRepository;
import com.jsp.pharmassist.repository.PatientRespository;
import com.jsp.pharmassist.responsedtos.BillResponse;

import jakarta.transaction.Transactional;

@Service
public class BillService {

	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final BillRepository billRepository;
	private final PatientRespository patientRespository;
	private final BillMapper billMapper;

	public BillService(CartRepository cartRepository, CartItemRepository cartItemRepository,
			BillRepository billRepository, PatientRespository patientRespository, BillMapper billMapper) {
		super();
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.billRepository = billRepository;
		this.patientRespository = patientRespository;
		this.billMapper = billMapper;
	}


	@Transactional
	public BillResponse generateBill(String cartId) {

		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(()-> new RuntimeException("Cart not found by id"));

		Patient patient = patientRespository.findById(cart.getPatientId())
				.orElseThrow(()-> new PatientNotFoundById("Patient is not found"));

		double totalAmount = cart.getTotalItemsPrice();
		double gstInPercentage = 18.0;
		double gstAmount = (totalAmount * gstInPercentage) / 100;
		double totalPayableAmount = totalAmount + gstAmount;

		Bill bill = new Bill();
		bill.setTotalAmount(totalAmount);
		bill.setTotalPayableAmount(totalPayableAmount);
		bill.setGstInPercentage(gstInPercentage);
		bill.setCart(cart);
		bill.setDateTime(LocalDateTime.now());
		bill.setPatient(patient);

		billRepository.save(bill);

		List<CartItem> cartItems = cart.getCartItems();

		clearCart(cart);

		return billMapper.mapToBillResponse(bill,cartItems);
	}


	private void clearCart(Cart cart) {

		cartItemRepository.deleteAll(cart.getCartItems());  

		cart.setCartItems(Collections.emptyList());  
		cart.setTotalItemsPrice(0.0);  

		cart.setPatientId(null);

		cartRepository.save(cart);
	}


	public List<BillResponse> findPatientBills(String patientId) {

		List<Bill> bills = billRepository.findBillByPatientId(patientId);

		return bills.stream()
				.map(bill -> billMapper.mapToBillResponse(bill, bill.getCart().getCartItems()))
				.collect(Collectors.toList());
	}

}
