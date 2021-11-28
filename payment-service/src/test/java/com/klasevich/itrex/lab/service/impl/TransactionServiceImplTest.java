package com.klasevich.itrex.lab.service.impl;

import com.klasevich.itrex.lab.controller.dto.DepositResponseDTO;
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
    void createDeposit_withCardId_emailShouldBeTheSame() {
        //given
        Card card = createCard();
        UserResponseDTO userResponseDTO = createUserResponseDTO();
        Mockito.when(userServiceClient.getUserById(ArgumentMatchers.anyLong())).thenReturn(userResponseDTO);
        Transaction transaction = Transaction.builder()
                .userId(null)
                .card(card)
                .amount(BigDecimal.valueOf(1000))
                .transactionType(TransactionType.DEPOSIT)
                .build();

        //when
        DepositResponseDTO deposit = transactionService.createDeposit(transaction);

        //then
        Assertions.assertThat(deposit.getMail()).isEqualTo("tanya@gmail.com");
    }

    @Test
    void checkPaymentServiceException() {
        //given
        String message = "User or card doesn't exist";
        Card card = createCard();
        card.setCardId(null);
        Transaction transaction = Transaction.builder().userId(null).card(card).amount(BigDecimal.valueOf(1000)).build();

        // when
        TransactionServiceException exception = assertThrows(TransactionServiceException.class,
                () -> {
                    transactionService.createDeposit(transaction);
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
}
