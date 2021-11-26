package com.klasevich.itrex.lab.service;

import com.klasevich.itrex.lab.controller.dto.DepositResponseDTO;
import com.klasevich.itrex.lab.controller.dto.PaymentResponseDTO;
import com.klasevich.itrex.lab.controller.dto.TransferResponseDTO;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {

    DepositResponseDTO createDeposit(Transaction transaction);

    List<Transaction> getPaymentsByCardId(Long cardId);

    Page<Transaction> getPayments(Pageable pageable);

    PaymentResponseDTO createPayment(Transaction transaction);

    TransferResponseDTO createTransfer(Transaction transaction);
}
