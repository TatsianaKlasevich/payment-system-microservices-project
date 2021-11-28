package com.klasevich.itrex.lab.persistance.repository;

import com.klasevich.itrex.lab.persistance.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT p.* FROM TRANSACTIONS p LEFT JOIN CARDS c ON c.id = p.card_id WHERE c.id = :cardId",
            nativeQuery = true)
    List<Transaction> findTransactionsByCardId(@Param("cardId") Long cardId);
}