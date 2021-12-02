package com.klasevich.itrex.lab.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class TransactionResponseDTO {

    private Long transactionId;

    private Long userId;

    private String email;

    private BigDecimal amount;

    private Long unp;

    private String purposeOfPayment;

    private TransactionType transactionType;

    private String bankCode;

    private Instant creatAt;

    private Instant updateAt;
}
