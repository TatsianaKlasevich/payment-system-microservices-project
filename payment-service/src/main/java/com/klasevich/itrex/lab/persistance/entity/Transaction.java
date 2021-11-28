package com.klasevich.itrex.lab.persistance.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long transactionId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email")
    private String email;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "unp")
    private Long unp;

    @Column(name = "recipient_card_id")
    private Long recipientCardId;

    @Column(name = "purpose_of_payment")
    private String purposeOfPayment;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "bank_code")
    private String bankCode;//todo

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "cardId")
    private Card card;
}
