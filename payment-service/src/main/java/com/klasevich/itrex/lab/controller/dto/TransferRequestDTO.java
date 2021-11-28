package com.klasevich.itrex.lab.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
public class TransferRequestDTO {

    @Positive(message = "User id should be positive number")
    private Long userId;

    @Positive(message = "Card id should be positive number")
    private Long cardId;

    @NotEmpty(message = "Amount should not be empty")
    @PositiveOrZero(message = "Balance should be positive number or 0")
    private BigDecimal amount;

    @NotEmpty(message = "Recipient card id should not be empty")
    @Positive(message = "Recipient card id should be positive number")
    private Long recipientCardId;
}
