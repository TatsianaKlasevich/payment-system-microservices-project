package com.klasevich.itrex.lab.controller.dto;

import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.CardStatus;
import com.klasevich.itrex.lab.persistance.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CardResponseDTO {
    private Long cardId;

    private BigDecimal balance;

    private Currency currency;

    private Boolean isDefault;

    private Long cardNumber;

    private CardStatus cardStatus;

    private LocalDate expirationDate;

    private Long cvvCode;

    private Long personId;

    public CardResponseDTO(Card card) {
        cardId = card.getCardId();
        balance = card.getBalance();
        currency = card.getCurrency();
        isDefault = card.isDefault();
        cardNumber = card.getCardNumber();
        cardStatus = card.getCardStatus();
        expirationDate = card.getExpirationDate();
        cvvCode = card.getCvvCode();
        personId = card.getUserId();
    }
}
