package com.klasevich.itrex.lab.util;

import com.klasevich.itrex.lab.controller.dto.CardRequestDTO;
import com.klasevich.itrex.lab.controller.dto.CardResponseDTO;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.CardStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestData {
    public static Card createNewCard() {
        return Card.builder()
                .cardId(1L)
                .userId(1L)
                .balance(BigDecimal.valueOf(1000))
                .cardNumber("1934674323464675")
                .cardStatus(CardStatus.ENABLED)
                .expirationDate(LocalDate.of(2025, 10, 01))
                .isDefault(true)
                .build();
    }

    public static Card createSecondCard() {
        return Card.builder()
                .cardId(2L)
                .userId(1L)
                .balance(BigDecimal.valueOf(500))
                .cardNumber("1934674111164675")
                .cardStatus(CardStatus.ENABLED)
                .expirationDate(LocalDate.of(2023, 10, 01))
                .isDefault(true)
                .build();
    }

    public static CardRequestDTO createCardRequestDTO() {
        return CardRequestDTO.builder()
                .userId(1L)
                .balance(BigDecimal.valueOf(1000))
                .cardNumber("1934674323464675")
                .cardStatus(CardStatus.ENABLED)
                .expirationDate(LocalDate.of(2025, 10, 01))
                .isDefault(true)
                .build();
    }

    public static CardResponseDTO createCardResponseDTO() {
        return CardResponseDTO.builder()
                .cardId(1L)
                .userId(1L)
                .balance(BigDecimal.valueOf(1000))
                .cardNumber("1934674323464675")
                .cardStatus(CardStatus.ENABLED)
                .expirationDate(LocalDate.of(2025, 10, 01))
                .isDefault(true)
                .build();
    }
}

