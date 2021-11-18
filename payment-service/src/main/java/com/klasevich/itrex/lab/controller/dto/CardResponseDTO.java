package com.klasevich.itrex.lab.controller.dto;

import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.CardStatus;
import com.klasevich.itrex.lab.persistance.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardResponseDTO {
    private Long cardId;

    private BigDecimal balance;

    private Currency currency;

    private Boolean isDefault;

    private Long cardNumber;

    private CardStatus cardStatus;

    private LocalDate expirationDate;

    private Long cvvCode;

    private Long userId;

    public CardResponseDTO(Card card) {
        cardId = card.getCardId();
        balance = card.getBalance();
        currency = card.getCurrency();
        isDefault = card.getIsDefault();
        cardNumber = card.getCardNumber();
        cardStatus = card.getCardStatus();
        expirationDate = card.getExpirationDate();
        cvvCode = card.getCvvCode();
        userId = card.getUserId();
    }
}
