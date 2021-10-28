package com.klasevich.itrex.lab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int cardId;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "card_number")
    private Long cardNumber;

    @Column(name = "is_default")
    private boolean isDefault;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_status")
    private CardStatus cardStatus;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "cvv_code")
    private Integer cvvCode;

    @Column(name = "user_id")
    private Integer userId;

    public Card(BigDecimal balance, Currency currency, Long cardNumber, boolean isDefault,
                CardStatus cardStatus, LocalDate expirationDate, Integer cvvCode, Integer userId) {
        this.balance = balance;
        this.currency = currency;
        this.cardNumber = cardNumber;
        this.isDefault = isDefault;
        this.cardStatus = cardStatus;
        this.expirationDate = expirationDate;
        this.cvvCode = cvvCode;
        this.userId = userId;
    }
}
