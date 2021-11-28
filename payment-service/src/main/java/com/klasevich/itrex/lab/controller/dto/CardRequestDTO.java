package com.klasevich.itrex.lab.controller.dto;

import com.klasevich.itrex.lab.persistance.entity.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardRequestDTO {

    @PositiveOrZero(message = "Balance should be positive number or 0")
    private BigDecimal balance;

    @AssertTrue
    private Boolean isDefault;

    @NotEmpty(message = "Card number should not be empty")
    @Pattern(regexp = "\\d{16}", message = "Card number should be valid")
    private String cardNumber;

    @NotEmpty(message = "Card number should not be empty")
    @Size(min = 3, max = 15, message = "Card status should be between 3 and 15 characters")
    private CardStatus cardStatus;

    @NotEmpty(message = "Date of birth should not be empty")
    @Future(message = "Date of birth should be after current")
    private LocalDate expirationDate;

    @NotEmpty(message = "User id should not be empty")
    @Positive(message = "User id should be positive number")
    private Long userId;
}


