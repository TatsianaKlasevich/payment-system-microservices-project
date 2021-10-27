package com.klasevich.itrex.lab.repository;

import com.klasevich.itrex.lab.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
