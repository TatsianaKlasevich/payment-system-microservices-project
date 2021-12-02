package com.klasevich.itrex.lab.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class DepositResponseDTO {

    private BigDecimal balance;

    private String mail;
}

