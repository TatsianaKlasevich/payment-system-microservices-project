package com.klasevich.itrex.lab.controller.dto;

import com.klasevich.itrex.lab.entity.CardStatus;
import com.klasevich.itrex.lab.entity.Currency;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class CardRequestDTO {

    private Integer id;

    private BigDecimal balance;

    private Currency currency;

    private Long cardNumber;

    private CardStatus cardStatus;

    private LocalDate expirationDate;

    private Integer cvvCode;

    private Integer userId;
}
