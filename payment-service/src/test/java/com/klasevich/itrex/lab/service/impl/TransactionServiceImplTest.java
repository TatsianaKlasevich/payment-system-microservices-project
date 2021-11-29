package com.klasevich.itrex.lab.service.impl;

import com.klasevich.itrex.lab.controller.dto.DepositResponseDTO;
import com.klasevich.itrex.lab.controller.dto.PaymentResponseDTO;
import com.klasevich.itrex.lab.controller.dto.TransferResponseDTO;
import com.klasevich.itrex.lab.exception.TransactionServiceException;
import com.klasevich.itrex.lab.feign.UserResponseDTO;
import com.klasevich.itrex.lab.feign.UserServiceClient;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.CardStatus;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import com.klasevich.itrex.lab.persistance.entity.TransactionType;
import com.klasevich.itrex.lab.persistance.repository.TransactionRepository;
import com.klasevich.itrex.lab.service.CardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @InjectMocks
    private TransactionServiceImpl transactionService;


    @Test
    void createDeposit_emailShouldBeTheSame() {
        //given
        Card card = createCard();
        UserResponseDTO userResponseDTO = createUserResponseDTO();
        Mockito.when(userServiceClient.getUserById(ArgumentMatchers.anyLong())).thenReturn(userResponseDTO);
        Transaction transaction = createDepositTransaction(card);

        //when
        DepositResponseDTO deposit = transactionService.createDeposit(transaction);

        //then
        Assertions.assertThat(deposit.getMail()).isEqualTo("tanya@gmail.com");
    }

    @Test
    void createPayment_emailShouldBeTheSame() {
        //given
        Card card = createCard();
        UserResponseDTO userResponseDTO = createUserResponseDTO();
        Mockito.when(userServiceClient.getUserById(ArgumentMatchers.anyLong())).thenReturn(userResponseDTO);
        Transaction transaction = createPaymentTransaction(card);

        //when
        PaymentResponseDTO payment = transactionService.createPayment(transaction);

        //then
        Assertions.assertThat(payment.getMail()).isEqualTo("tanya@gmail.com");
    }

    @Test
    void createTransfer_emailShouldBeTheSame() {
        //given
        Card card = createCard();
        Card recipientCard = createCardRecipient();
        UserResponseDTO userResponseDTO = createUserResponseDTO();
        Mockito.when(userServiceClient.getUserById(ArgumentMatchers.anyLong())).thenReturn(userResponseDTO);
        Mockito.when(cardService.getCardById(ArgumentMatchers.anyLong())).thenReturn(recipientCard);
        Transaction transaction = Transaction.builder()
                .userId(null)
                .card(card)
                .amount(BigDecimal.valueOf(50))
                .transactionType(TransactionType.TRANSFER)
                .recipientCardId(2L)
                .build();

        //when
        TransferResponseDTO transfer = transactionService.createTransfer(transaction);

        //then
        Assertions.assertThat(transfer.getMail()).isEqualTo("tanya@gmail.com");
    }

    @Test
    void checkPaymentServiceException_whenUserOrCardNotExist() {
        //given
        String message = "User or card doesn't exist";
        Card card = createCard();
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
        Card card = createCard();
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

    private UserResponseDTO createUserResponseDTO() {
        return UserResponseDTO.builder()
                .userId(1L)
                .email("tanya@gmail.com")
                .name("Tanya")
                .secondName("Vladimirovna")
                .surname("Klasevich")
                .dateOfBirth(LocalDate.of(1980, 10, 22))
                .identityPassportNumber("13NKL03498EK4678")
                .phoneNumber("+375448904949")
                .build();
    }

    private Card createCard() {
        return Card.builder()
                .cardId(1L)
                .userId(1L)
                .balance(BigDecimal.valueOf(1000))
                .cardNumber("1934674323464675")
                .cardStatus(CardStatus.ENABLED)
                .expirationDate(LocalDate.of(2025, 10, 01))
                .isDefault(true)
                .build();
    }

    private Card createCardRecipient() {
        return Card.builder()
                .cardId(2L)
                .userId(1L)
                .balance(BigDecimal.valueOf(500))
                .cardNumber("1934674111164675")
                .cardStatus(CardStatus.ENABLED)
                .expirationDate(LocalDate.of(2023, 10, 01))
                .isDefault(true)
                .build();
    }

    private Transaction createDepositTransaction(Card card) {
        return Transaction.builder()
                .userId(null)
                .card(card)
                .amount(BigDecimal.valueOf(1000))
                .transactionType(TransactionType.DEPOSIT)
                .build();
    }

    private Transaction createPaymentTransaction(Card card) {
        return Transaction.builder()
                .userId(null)
                .card(card)
                .amount(BigDecimal.valueOf(100))
                .transactionType(TransactionType.PAYMENT)
                .unp(135465644L)
                .purposeOfPayment("for chess")
                .bankCode("3L4KJSKJH4556665LKSJDF909809")
                .build();
    }
}
