package com.klasevich.itrex.lab.service;

import com.klasevich.itrex.lab.controller.dto.DepositResponseDTO;
import com.klasevich.itrex.lab.persistance.entity.Payment;

public interface PaymentService {

    DepositResponseDTO deposit(Payment payment);
}
