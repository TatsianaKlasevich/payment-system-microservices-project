package com.klasevich.itrex.lab.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
public class DepositRequestDTO {

    @NotEmpty(message = "User id should not be empty")
    @Positive(message = "User id should be positive number")
    private Long userId;

    @NotEmpty(message = "Card id should not be empty")
    @Positive(message = "Card id should be positive number")
    private Long cardId;

    @PositiveOrZero(message = "Balance should be positive number or 0")
    @Pattern(regexp = "\\d,\\d{2}", message = "Balance should look like 0,00")
    private BigDecimal amount;
}

