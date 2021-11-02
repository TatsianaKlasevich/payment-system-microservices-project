package com.klasevich.itrex.lab.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {
    private BigDecimal amount;
    private String mail;
}

