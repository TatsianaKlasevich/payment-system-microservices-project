package com.klasevich.itrex.lab.controller.dto;

import com.klasevich.itrex.lab.entity.CardStatus;
import com.klasevich.itrex.lab.entity.Currency;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CardRequestDTO {

    private Integer cardId;

    private BigDecimal balance;

    private Currency currency;

    private boolean isDefault;

    private Long cardNumber;

    private CardStatus cardStatus;

    private LocalDate expirationDate;

    private Integer cvvCode;

    private int personId;
}
