package com.javabhakt.ps.api.service;

import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javabhakt.ps.api.entity.Payment;
import com.javabhakt.ps.api.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	private Logger log = LoggerFactory.getLogger(PaymentService.class);
	
	public Payment doPayment (Payment payment) throws JsonProcessingException {
		
		payment.setTransactionId(UUID.randomUUID().toString());
		payment.setPaymentStatus(paymentProcessing());
		log.info("PaymentService request : {}",new ObjectMapper().writeValueAsString(payment));
		return paymentRepository.save(payment);
	}

	public Payment findPaymentHistoryByOrderId(int orderId) throws JsonProcessingException {
		Payment payment = paymentRepository.findByOrderId(orderId);
		log.info("PaymentService findPaymentHistoryByOrderId : {}",new ObjectMapper().writeValueAsString(payment));
		return payment;
	}
	
	private String paymentProcessing() {
		//return "Third Party call from here";
		return new Random().nextBoolean() ? "Success" : "Failed";
	}
}
