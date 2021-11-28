package com.klasevich.itrex.lab.persistance.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
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

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_status")
    private CardStatus cardStatus;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "card")
    private Set<Transaction> transactions;
}
