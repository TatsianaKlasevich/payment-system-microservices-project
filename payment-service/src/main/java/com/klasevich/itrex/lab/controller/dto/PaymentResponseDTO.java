package com.klasevich.itrex.lab.controller.dto;

import com.klasevich.itrex.lab.persistance.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDTO {
    private BigDecimal amount;

    private String mail;

    private Long unp;

    private String purposeOfPayment;

    private String bankCode;

    public PaymentResponseDTO(Payment payment) {
        amount = payment.getAmount();
        mail = payment.getEmail();
        unp = payment.getUnp();
        purposeOfPayment = payment.getPurposeOfPayment();
        bankCode = payment.getBankCode();
    }
}

