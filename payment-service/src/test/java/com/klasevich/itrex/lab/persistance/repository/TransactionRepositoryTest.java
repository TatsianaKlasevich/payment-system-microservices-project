package com.klasevich.itrex.lab.persistance.repository;

import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.CardStatus;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import com.klasevich.itrex.lab.persistance.entity.TransactionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void findAll_sizeShouldBeValid() {
        //given
        Transaction transaction = createNewTransaction();
        int expectedSize = transactionRepository.findAll().size() + 1;

        //when
        transactionRepository.save(transaction);
        int actualSize = transactionRepository.findAll().size();

        //then
        assertEquals(expectedSize, actualSize);
        transactionRepository.delete(transaction);
    }

    private Transaction createNewTransaction() {
        return Transaction.builder()
                .amount(BigDecimal.valueOf(50))
                .transactionType(TransactionType.DEPOSIT)
                .card(createNewCard())
                .build();
    }

    private Card createNewCard() {
        return Card.builder()
                .balance(BigDecimal.valueOf(200))
                .isDefault(true)
                .cardNumber("2380993469345345")
                .cardStatus(CardStatus.ENABLED)
                .expirationDate(LocalDate.of(2025, 10, 01))
                .userId(1L)
                .build();
    }
}