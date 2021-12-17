package com.klasevich.itrex.lab.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasevich.itrex.lab.controller.dto.DepositResponseDTO;
import com.klasevich.itrex.lab.controller.dto.PaymentResponseDTO;
import com.klasevich.itrex.lab.controller.dto.TransferResponseDTO;
import com.klasevich.itrex.lab.exception.TransactionServiceException;
import com.klasevich.itrex.lab.feign.UserServiceClient;
import com.klasevich.itrex.lab.feign.dto.UserResponseDTO;
import com.klasevich.itrex.lab.mapper.TransactionToTransactionResponseDTOMapper;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import com.klasevich.itrex.lab.persistance.repository.TransactionRepository;
import com.klasevich.itrex.lab.service.CardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.klasevich.itrex.lab.util.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UserServiceClient userServiceClient;

    @Mock
    private CardService cardService;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private TransactionToTransactionResponseDTOMapper transactionToTransactionResponseDTOMapper;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private TransactionServiceImpl transactionService;


    @Test
    void createDeposit_emailShouldBeTheSame() {
        //given
        Card card = createNewCardWithCardId();
        UserResponseDTO userResponseDTO = createUserResponseDTO();
        Transaction transaction = createDepositTransaction(card);

        //when
        when(userServiceClient.getUserById(ArgumentMatchers.anyLong())).thenReturn(userResponseDTO);
        DepositResponseDTO deposit = transactionService.createDeposit(transaction);

        //then
        Assertions.assertThat(deposit.getMail()).isEqualTo(transaction.getEmail());
    }

    @Test
    void createPayment_emailShouldBeTheSame() {
        //given
        Card card = createNewCardWithCardId();
        UserResponseDTO userResponseDTO = createUserResponseDTO();
        Transaction transaction = createPaymentTransaction(card);

        //when
        when(userServiceClient.getUserById(ArgumentMatchers.anyLong())).thenReturn(userResponseDTO);
        PaymentResponseDTO payment = transactionService.createPayment(transaction);

        //then
        Assertions.assertThat(payment.getMail()).isEqualTo(transaction.getEmail());
    }

    @Test
    void createTransfer_emailShouldBeTheSame() {
        //given
        Card card = createNewCardWithCardId();
        Card recipientCard = createSecondCardWithCardId();
        UserResponseDTO userResponseDTO = createUserResponseDTO();
        Transaction transaction = createTransferTransaction(card);

        //when
        when(userServiceClient.getUserById(ArgumentMatchers.anyLong())).thenReturn(userResponseDTO);
        when(cardService.getCardById(ArgumentMatchers.anyLong())).thenReturn(recipientCard);
        TransferResponseDTO transfer = transactionService.createTransfer(transaction);

        //then
        Assertions.assertThat(transfer.getMail()).isEqualTo(transaction.getEmail());
    }

    @Test
    void checkPaymentServiceException_whenUserOrCardNotExist() {
        //given
        String message = "User or card doesn't exist";
        Card card = createNewCard();
        card.setCardId(null);
        Transaction transaction = createDepositTransaction(card);

        // when
        TransactionServiceException exception = assertThrows(TransactionServiceException.class,
                () -> {
                    transactionService.createDeposit(transaction);
                });

        // then
        Assertions.assertThat(message).isEqualTo(exception.getMessage());
    }

    @Test
    void checkPaymentServiceException_whenNotEnoughMoneyForPayment() {
        //given
        String message = "Not enough money for payment";
        Card card = createNewCardWithCardId();
        Transaction transaction = createPaymentTransaction(card);
        transaction.setAmount(BigDecimal.valueOf(1200));

        // when
        TransactionServiceException exception = assertThrows(TransactionServiceException.class,
                () -> {
                    transactionService.createPayment(transaction);
                });

        // then
        Assertions.assertThat(message).isEqualTo(exception.getMessage());
    }

    @Test
    void getTransactionsByCardId_transactionsSizeShouldBeTheSame() {
        // given
        Card card = createNewCard();
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(createDepositTransaction(card));
        transactions.add(createPaymentTransaction(card));
        Long cardId = 1L;

        // when
        when(transactionRepository.findTransactionsByCard_CardId(ArgumentMatchers.anyLong())).thenReturn(transactions);
        List<Transaction> resultList = transactionService.getTransactionsByCardId(cardId);

        // then
        assertThat(resultList.size()).isEqualTo(transactions.size());
    }

    @Test
    void findTransactionsByPage_shouldReturnValidNumberOfTransactions() {
        //given
        Card card = createNewCard();
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(createDepositTransaction(card));
        transactions.add(createPaymentTransaction(card));
        Page<Transaction> page = new PageImpl<>((transactions));
        Pageable pageable = PageRequest.of(1, transactions.size());

        // when
        when(transactionRepository.findAll(pageable)).thenReturn(page);
        List<Transaction> resultList = transactionService.getAllTransactions(pageable).getContent();

        //then
        assertThat(resultList.size()).isEqualTo(transactions.size());
    }
}
