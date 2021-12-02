package com.klasevich.itrex.lab.persistance.repository;

import com.klasevich.itrex.lab.persistance.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionsByCard_CardId(Long cardId);
}
