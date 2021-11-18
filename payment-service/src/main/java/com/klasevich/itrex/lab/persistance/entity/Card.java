package com.klasevich.itrex.lab.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cards")
public class Card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long cardId;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "card_number")
    private Long cardNumber;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_status")
    private CardStatus cardStatus;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "cvv_code")
    private Long cvvCode;

    @Column(name = "user_id")
    private Long userId;

    public Card(BigDecimal balance, Currency currency, Long cardNumber, Boolean isDefault,
                CardStatus cardStatus, LocalDate expirationDate, Long cvvCode, Long userId) {
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
