package com.klasevich.itrex.lab.persistance.repository;

import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.klasevich.itrex.lab.util.TestData.createDepositTransaction;
import static com.klasevich.itrex.lab.util.TestData.createNewCard;
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
        Card card = createNewCard();
        Transaction transaction = createDepositTransaction(card);
        int expectedSize = transactionRepository.findAll().size() + 1;

        //when
        transactionRepository.save(transaction);
        int actualSize = transactionRepository.findAll().size();

        //then
        assertEquals(expectedSize, actualSize);
        transactionRepository.delete(transaction);
    }
}