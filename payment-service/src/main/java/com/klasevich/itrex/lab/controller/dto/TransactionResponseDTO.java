package com.klasevich.itrex.lab.controller.dto;

import com.klasevich.itrex.lab.persistance.entity.Transaction;
import com.klasevich.itrex.lab.persistance.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionResponseDTO {
    private Long paymentId;

    private Long userId;

    private String email;

    private BigDecimal amount;

    private Long unp;

    private String purposeOfPayment;

    private TransactionType transactionType;

    private String bankCode;

    private Instant creatAt;

    private Instant updateAt;

    public TransactionResponseDTO(Transaction transaction) {
        paymentId = transaction.getPaymentId();
        userId = transaction.getUserId();
        email = transaction.getEmail();
        amount = transaction.getAmount();
        unp = transaction.getUnp();
        purposeOfPayment = transaction.getPurposeOfPayment();
        transactionType = transaction.getTransactionType();
        bankCode = transaction.getBankCode();
        creatAt = transaction.getCreatAt();
        updateAt = transaction.getUpdateAt();
    }
}
