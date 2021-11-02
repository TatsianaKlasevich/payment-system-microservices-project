package com.klasevich.itrex.lab.persistance.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long paymentId;

    private int sender;

    private Long recipient;//todo cardId

    private String email;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    private int unp;

    @Column(name = "purpose_of_payment")
    private String purposeOfPayment;

//    @Column(name = "payment_date")
//    private Timestamp paymentDate; //OffSetDateTime

    @Column(name = "bank_code")
    private String bankCode;

    public Payment(Long recipient, String email, BigDecimal amount) {
        this.recipient = recipient;
        this.email = email;
        this.amount = amount;
    }
}
