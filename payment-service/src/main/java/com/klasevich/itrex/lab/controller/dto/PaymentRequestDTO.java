package com.klasevich.itrex.lab.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequestDTO {

    private Long userId;

    private Long cardId;

    private BigDecimal amount;
}
