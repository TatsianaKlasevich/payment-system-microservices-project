package com.klasevich.itrex.lab.service;

import com.klasevich.itrex.lab.controller.dto.DepositResponseDTO;
import com.klasevich.itrex.lab.controller.dto.PaymentResponseDTO;
import com.klasevich.itrex.lab.persistance.entity.Payment;

import java.util.List;

public interface PaymentService {

    DepositResponseDTO deposit(Payment payment);

    List<Payment> getPaymentsByCardId(Long cardId);

    PaymentResponseDTO createPayment(Payment payment);
}
