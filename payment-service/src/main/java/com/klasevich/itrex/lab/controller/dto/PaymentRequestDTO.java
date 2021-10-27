package com.klasevich.itrex.lab.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequestDTO {

    private int userId;

    private int cardId;

    private BigDecimal amount;
}
