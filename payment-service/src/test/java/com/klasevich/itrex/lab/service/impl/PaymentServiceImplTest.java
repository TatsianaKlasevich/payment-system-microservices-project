package com.klasevich.itrex.lab.service.impl;

import com.klasevich.itrex.lab.controller.dto.PaymentResponseDTO;
import com.klasevich.itrex.lab.exception.PaymentServiceException;
import com.klasevich.itrex.lab.feign.UserResponseDTO;
import com.klasevich.itrex.lab.feign.UserServiceClient;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.repository.PaymentRepository;
import com.klasevich.itrex.lab.service.CardService;
import org.junit.jupiter.api.Assertions;
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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private UserServiceClient userServiceClient;

    @Mock
    private CardService cardService;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private PaymentServiceImpl paymentService;


    @Test
    void checkDepositService_withCardId_emailShouldBeTheSame() {
        //given
        Card card = createCard();
        UserResponseDTO userResponseDTO = createUserResponseDTO();
        Mockito.when(cardService.getCardById(ArgumentMatchers.anyLong())).thenReturn(card);
        Mockito.when(userServiceClient.getUserById(ArgumentMatchers.anyLong())).thenReturn(userResponseDTO);

        //when
        PaymentResponseDTO deposit = paymentService.deposit(null, 1L, BigDecimal.valueOf(1000));
        String expected = deposit.getMail();

        //then
        assertEquals(expected, "tanya@gmail.com");
    }

    @Test
    void checkPaymentServiceException() {
        //given
        String message = "User or card doesn't exist";

        // when
        PaymentServiceException exception = assertThrows(PaymentServiceException.class,
                () -> {
                    paymentService.deposit(null, null, BigDecimal.valueOf(1000));
                });

        // then
        Assertions.assertEquals(message, exception.getMessage());
    }

    private UserResponseDTO createUserResponseDTO() {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(1L);
        userResponseDTO.setCards(List.of(1L, 2L, 3L));
        userResponseDTO.setEmail("tanya@gmail.com");
        userResponseDTO.setName("Tanya");
        userResponseDTO.setSecondName("Vladimirovna");
        userResponseDTO.setSurname("Klasevich");
        userResponseDTO.setDateOfBirth(LocalDate.of(1980, 10, 22));
        userResponseDTO.setIdentityPassportNumber("13NKL03498");
        userResponseDTO.setPhoneNumber("+375448904949");
        return userResponseDTO;
    }

    private Card createCard() {
        Card card = new Card();
        card.setUserId(1L);
        card.setBalance(BigDecimal.valueOf(1000));
        card.setCardId(1L);
        card.setIsDefault(true);
        return card;
    }
}