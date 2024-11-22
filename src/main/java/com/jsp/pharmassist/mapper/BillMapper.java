package com.jsp.pharmassist.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.jsp.pharmassist.entity.Bill;
import com.jsp.pharmassist.entity.Cart;
import com.jsp.pharmassist.entity.CartItem;
import com.jsp.pharmassist.entity.Patient;
import com.jsp.pharmassist.responsedtos.BillResponse;
import com.jsp.pharmassist.responsedtos.CartItemResponse;
import com.jsp.pharmassist.responsedtos.CartResponse;
import com.jsp.pharmassist.responsedtos.PatientResponse;

@Component
public class BillMapper {
	
//	public BillResponse mapToBillResponse(Bill bill) {
//		
//		BillResponse billResponse = new BillResponse();
//		billResponse.setBillId(bill.getBillId());
//		billResponse.setTotalAmount(bill.getTotalAmount());
//		billResponse.setGstInPercentage(bill.getGstInPercentage());
//		billResponse.setTotalPayableAmount(bill.getTotalPayableAmount());
//		billResponse.setDateTime(LocalDateTime.now());
//		
//		
//		// Map to cart
//		
//		Cart cart = bill.getCart();
//		CartResponse cartResponse = new CartResponse();
//		cartResponse.setCartId(cart.getCartId());
//		cartResponse.setTotalItemsPrice(cart.getTotalItemsPrice());
//		cartResponse.setCartItems(cart.getCartItems().stream().map(item ->{
//			CartItemResponse  cartItemResponse = new CartItemResponse();
//			cartItemResponse.setCartItemId(item.getCartItemId());
//			cartItemResponse.setPrice(item.getPrice());
//			cartItemResponse.setQuantity(item.getQuantity());
//			cartItemResponse.setMedicineId(item.getMedicineId());
//			return cartItemResponse;
//		}).collect(Collectors.toList()));
//		billResponse.setCart(cartResponse);
//		
//		
//		 // Map To Patient
//        Patient patient = bill.getPatient();
//        PatientResponse patientResponse = new PatientResponse();
//        patientResponse.setPatientId(patient.getPatientId());
//        patientResponse.setName(patient.getName());
//        patientResponse.setPhoneNo(patient.getPhoneNo());
//        patientResponse.setEmail(patient.getEmail());
//        patientResponse.setGender(patient.getGender());
//        patientResponse.setDateOfBirth(patient.getDateOfBirth());
//        billResponse.setPatient(patientResponse);
//		
//		return billResponse;
//	}
	
	public BillResponse mapToBillResponse(Bill bill, List<CartItem> cartItems) {
		
        BillResponse billResponse = new BillResponse();
        billResponse.setBillId(bill.getBillId());
        billResponse.setTotalAmount(bill.getTotalAmount());
        billResponse.setGstInPercentage(bill.getGstInPercentage());
        billResponse.setTotalPayableAmount(bill.getTotalPayableAmount());
        billResponse.setDateTime(bill.getDateTime());

        // Map the Cart to CartResponse
        CartResponse cartResponse = mapToCartResponse(bill.getCart());
        billResponse.setCart(cartResponse);

        // Map CartItems
        List<CartItemResponse> cartItemResponses = mapToCartItemResponses(cartItems);
        billResponse.setCartItems(cartItemResponses);

        // Map the Patient to PatientResponse
        PatientResponse patientResponse = mapToPatientResponse(bill.getPatient());
        billResponse.setPatient(patientResponse);

        return billResponse;
    }

    public CartResponse mapToCartResponse(Cart cart) {
        CartResponse cartResponse = new CartResponse();
        cartResponse.setCartId(cart.getCartId());
        cartResponse.setTotalItemsPrice(cart.getTotalItemsPrice());
        return cartResponse;
    }

    public List<CartItemResponse> mapToCartItemResponses(List<CartItem> cartItems) {
        return cartItems.stream().map(item -> {
            CartItemResponse response = new CartItemResponse();
            response.setCartItemId(item.getCartItemId());
            response.setMedicineId(item.getMedicineId());
            response.setQuantity(item.getQuantity());
            response.setPrice(item.getPrice());
            return response;
        }).collect(Collectors.toList());
    }

    public PatientResponse mapToPatientResponse(Patient patient) {
        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setPatientId(patient.getPatientId());
        patientResponse.setName(patient.getName());
        patientResponse.setPhoneNo(patient.getPhoneNo());
        patientResponse.setEmail(patient.getEmail());
        patientResponse.setGender(patient.getGender());
        patientResponse.setDateOfBirth(patient.getDateOfBirth());
        return patientResponse;
    }

}
