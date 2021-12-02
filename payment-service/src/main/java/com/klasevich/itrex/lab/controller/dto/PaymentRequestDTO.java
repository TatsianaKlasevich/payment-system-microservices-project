package com.klasevich.itrex.lab.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class PaymentRequestDTO {

    @Positive(message = "User id should be positive number")
    private Long userId;

    @Positive(message = "Card id should be positive number")
    private Long cardId;

    @NotEmpty(message = "Amount should not be empty")
    @PositiveOrZero(message = "Balance should be positive number or 0")
    private BigDecimal amount;

    @NotEmpty(message = "Unp should not be empty")
    @Pattern(regexp = "\\d{9}", message = "Unp must have 9 digits ")
    private Long unp;

    @NotEmpty(message = "Card number should not be empty")
    @Size(min = 2, max = 125, message = "Purpose of payment should be between 2 and 125 characters")
    private String purposeOfPayment;

    @NotEmpty(message = "Card number should not be empty")
    @Size(min = 28, max = 28, message = "Bank code should have 28 characters")
    private String bankCode;
}