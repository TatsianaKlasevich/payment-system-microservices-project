package com.klasevich.itrex.lab.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasevich.itrex.lab.controller.dto.DepositResponseDTO;
import com.klasevich.itrex.lab.exception.PaymentServiceException;
import com.klasevich.itrex.lab.feign.UserResponseDTO;
import com.klasevich.itrex.lab.feign.UserServiceClient;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.Payment;
import com.klasevich.itrex.lab.persistance.repository.PaymentRepository;
import com.klasevich.itrex.lab.service.CardService;
import com.klasevich.itrex.lab.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private static final String TOPIC_EXCHANGE_PAYMENT = "js.payment.notify.exchange";
    private static final String ROUTING_KEY_PAYMENT = "js.key.payment";

    private final PaymentRepository paymentRepository;
    private final UserServiceClient userServiceClient;
    private final CardService cardService;
    private final RabbitTemplate rabbitTemplate;

    public DepositResponseDTO deposit(Payment payment) {
        if (payment.getUserId() == null && payment.getCardId() == null) {
            throw new PaymentServiceException("User or card doesn't exist");
        }

        if (payment.getCardId() != null) {
            Card card = cardService.getCardById(payment.getCardId());
            BigDecimal newBalance = card.getBalance().add(payment.getAmount());
            cardService.updateCard(card);

            UserResponseDTO userResponseDTO = userServiceClient.getUserById(card.getUserId());
            payment.setEmail(userResponseDTO.getEmail());
            payment.setAmount(newBalance);
            paymentRepository.save(payment);

            return createResponse(newBalance, userResponseDTO);
        }

        Card defaultCard = getDefaultCard(payment.getUserId());
        BigDecimal newBalance = defaultCard.getBalance().add(payment.getAmount());
        cardService.updateCard(defaultCard);

        UserResponseDTO userResponseDTO = userServiceClient.getUserById(defaultCard.getUserId());
        payment.setEmail(userResponseDTO.getEmail());
        payment.setAmount(newBalance);
        paymentRepository.save(payment);

        return createResponse(newBalance, userResponseDTO);
    }

    private DepositResponseDTO createResponse(BigDecimal newBalance, UserResponseDTO userResponseDTO) {
        DepositResponseDTO depositResponseDTO = new DepositResponseDTO(newBalance, userResponseDTO.getEmail());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rabbitTemplate
                    .convertAndSend(TOPIC_EXCHANGE_PAYMENT, ROUTING_KEY_PAYMENT,
                            objectMapper.writeValueAsString(depositResponseDTO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new PaymentServiceException("Can't send message to RabbitMQ");
        }
        return depositResponseDTO;
    }

    private Card getDefaultCard(Long userId) {
        return cardService.getCardsByUserId(userId).stream()
                .filter(Card::getIsDefault)
                .findAny()
                .orElseThrow(() -> new PaymentServiceException("Unable to find default card for person: " + userId));
    }
}