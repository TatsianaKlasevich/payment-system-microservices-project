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
    private Integer id;

    private BigDecimal balance;

    private Currency currency;

    private Long cardNumber;

    private CardStatus cardStatus;

    private LocalDate expirationDate;

    private Integer cvvCode;

    private Integer userId;

    public CardResponseDTO(Card card) {
        id = card.getId();
        balance = card.getBalance();
        currency = card.getCurrency();
        cardNumber = card.getCardNumber();
        cardStatus = card.getCardStatus();
        expirationDate = card.getExpirationDate();
        cvvCode = card.getCvvCode();
        userId = card.getUserId();
    }
}
