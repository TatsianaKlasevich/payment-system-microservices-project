package com.klasevich.itrex.lab.util;

import com.klasevich.itrex.lab.controller.dto.CardRequestDTO;
import com.klasevich.itrex.lab.controller.dto.CardResponseDTO;
import com.klasevich.itrex.lab.feign.dto.UserResponseDTO;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.CardStatus;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import com.klasevich.itrex.lab.persistance.entity.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestData {
    private TestData() {
    }

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

    public static UserResponseDTO createUserResponseDTO() {
        return UserResponseDTO.builder()
                .userId(1L)
                .email("tanya@gmail.com")
                .name("Tanya")
                .secondName("Vladimirovna")
                .surname("Klasevich")
                .dateOfBirth(LocalDate.of(1980, 10, 22))
                .identityPassportNumber("13NKL03498EK4678")
                .phoneNumber("+375448904949")
                .build();
    }

    public static Card createCard() {
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

    public static Card createCardRecipient() {
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

    public static Transaction createDepositTransaction(Card card) {
        return Transaction.builder()
                .userId(null)
                .card(card)
                .amount(BigDecimal.valueOf(1000))
                .transactionType(TransactionType.DEPOSIT)
                .build();
    }

    public static Transaction createTransferTransaction(Card card) {
        return Transaction.builder()
                .userId(null)
                .card(card)
                .amount(BigDecimal.valueOf(50))
                .transactionType(TransactionType.TRANSFER)
                .recipientCardId(2L)
                .build();
    }

    public static Transaction createPaymentTransaction(Card card) {
        return Transaction.builder()
                .userId(null)
                .card(card)
                .amount(BigDecimal.valueOf(100))
                .transactionType(TransactionType.PAYMENT)
                .unp(135465644L)
                .purposeOfPayment("for chess")
                .bankCode("3L4KJSKJH4556665LKSJDF909809")
                .build();
    }
}

