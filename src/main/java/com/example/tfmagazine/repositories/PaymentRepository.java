package com.example.tfmagazine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tfmagazine.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	
}
