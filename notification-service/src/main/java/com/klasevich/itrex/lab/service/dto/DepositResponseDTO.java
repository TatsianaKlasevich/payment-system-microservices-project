package com.klasevich.itrex.lab.service.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DepositResponseDTO {
    private BigDecimal amount;
    private String mail;
}
