package com.javabhakt.ps.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javabhakt.ps.api.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	Payment findByOrderId(int orderId);

}
