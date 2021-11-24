package com.klasevich.itrex.lab.service;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentResponseDTO {
    private BigDecimal amount;

    private String mail;

    private Long unp;

    private String purposeOfPayment;

    private String bankCode;
}
