package com.klasevich.itrex.lab.service.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PaymentResponseDTO {
    private BigDecimal amount;

    private String mail;

    private Long unp;

    private String purposeOfPayment;

    private String bankCode;
}
