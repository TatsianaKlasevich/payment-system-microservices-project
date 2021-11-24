package com.klasevich.itrex.lab.controller.dto;

import com.klasevich.itrex.lab.persistance.entity.Payment;
import com.klasevich.itrex.lab.persistance.entity.PaymentStatus;
import com.klasevich.itrex.lab.persistance.entity.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionResponseDTO {
    private Long paymentId;

    private Long userId;

    private String email;

    private BigDecimal amount;

    private PaymentStatus paymentStatus;

    private Long unp;

    private String purposeOfPayment;

    private PaymentType paymentType;

    private String bankCode;

    private Instant creatAt;

    private Instant updateAt;

    public TransactionResponseDTO(Payment payment) {
        paymentId = payment.getPaymentId();
        userId = payment.getUserId();
        email = payment.getEmail();
        amount = payment.getAmount();
        paymentStatus = payment.getPaymentStatus();
        unp = payment.getUnp();
        purposeOfPayment = payment.getPurposeOfPayment();
        paymentType = payment.getPaymentType();
        bankCode = payment.getBankCode();
        creatAt = payment.getCreatAt();
        updateAt = payment.getUpdateAt();
    }
}
