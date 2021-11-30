package com.klasevich.itrex.lab.controller.dto;

import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.CardStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
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

    public CardResponseDTO(Card card) {
        cardId = card.getCardId();
        balance = card.getBalance();
        isDefault = card.getIsDefault();
        cardNumber = card.getCardNumber();
        cardStatus = card.getCardStatus();
        expirationDate = card.getExpirationDate();
        userId = card.getUserId();
    }


}
