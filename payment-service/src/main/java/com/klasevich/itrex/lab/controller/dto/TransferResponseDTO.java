package com.klasevich.itrex.lab.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Builder
@Getter
public class TransferResponseDTO {

    private BigDecimal balance;

    private String mail;

    private Long recipientCardId;
}
