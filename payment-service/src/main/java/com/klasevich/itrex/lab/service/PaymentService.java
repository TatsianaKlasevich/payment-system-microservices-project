package com.klasevich.itrex.lab.service;

import com.klasevich.itrex.lab.controller.dto.PaymentResponseDTO;

import java.math.BigDecimal;

public interface PaymentService {

    PaymentResponseDTO deposit(Long userId, Long cardId, BigDecimal amount);
}
