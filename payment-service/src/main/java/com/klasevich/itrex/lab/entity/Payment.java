package com.klasevich.itrex.lab.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int paymentId;

    private int sender;

    private int recipient;

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

    public Payment(int recipient, String email, BigDecimal amount) {
        this.recipient = recipient;
        this.email = email;
        this.amount = amount;
    }
}
