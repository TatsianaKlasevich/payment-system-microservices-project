package com.klasevich.itrex.lab.controller.dto;

import com.klasevich.itrex.lab.entity.Card;
import com.klasevich.itrex.lab.entity.CardStatus;
import com.klasevich.itrex.lab.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CardResponseDTO {
    private Integer cardId;

    private BigDecimal balance;

    private Currency currency;

    private boolean isDefault;

    private Long cardNumber;

    private CardStatus cardStatus;

    private LocalDate expirationDate;

    private Integer cvvCode;

    private int personId;

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
