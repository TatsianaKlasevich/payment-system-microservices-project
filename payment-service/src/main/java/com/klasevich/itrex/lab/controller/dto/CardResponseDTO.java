package com.klasevich.itrex.lab.controller.dto;

import com.klasevich.itrex.lab.persistance.entity.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CardResponseDTO {
    private Long cardId;

    private BigDecimal balance;

    private Boolean isDefault;

    private String cardNumber;

    private CardStatus cardStatus;

    private LocalDate expirationDate;

    private Long userId;
}
