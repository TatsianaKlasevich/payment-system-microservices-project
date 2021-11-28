package com.klasevich.itrex.lab.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class PaymentResponseDTO {
    private BigDecimal amount;

    private String mail;

    private Long unp;

    private String purposeOfPayment;

    private String bankCode;
}

