package com.klasevich.itrex.lab.controller.dto;

import com.klasevich.itrex.lab.persistance.entity.CardStatus;
import com.klasevich.itrex.lab.persistance.entity.Currency;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CardRequestDTO {

    private Long cardId;

    private BigDecimal balance;

    private Currency currency;

    private Boolean isDefault;

    private Long cardNumber;

    private CardStatus cardStatus;

    private LocalDate expirationDate;

    private Long cvvCode;

    private Long personId;
}
