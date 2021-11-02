package com.klasevich.itrex.lab.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasevich.itrex.lab.controller.dto.PaymentResponseDTO;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.Payment;
import com.klasevich.itrex.lab.exception.PaymentServiceException;
import com.klasevich.itrex.lab.persistance.repository.PaymentRepository;
import com.klasevich.itrex.lab.feign.UserResponseDTO;
import com.klasevich.itrex.lab.feign.UserServiceClient;
import com.klasevich.itrex.lab.service.CardService;
import com.klasevich.itrex.lab.service.PaymentService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {
    private static final String TOPIC_EXCHANGE_PAYMENT = "js.payment.notify.exchange";
    private static final String ROUTING_KEY_PAYMENT = "js.key.payment";

    private final PaymentRepository paymentRepository;
    private final UserServiceClient userServiceClient;
    private final CardService cardService;
    private final RabbitTemplate rabbitTemplate;

    public PaymentServiceImpl(PaymentRepository paymentRepository, UserServiceClient userServiceClient,
                          CardService cardService, RabbitTemplate rabbitTemplate) {
        this.paymentRepository = paymentRepository;
        this.userServiceClient = userServiceClient;
        this.cardService = cardService;
        this.rabbitTemplate = rabbitTemplate;
    }

    public PaymentResponseDTO deposit(Long userId, Long cardId, BigDecimal amount) {
        if (userId == null && cardId == null) {
            throw new PaymentServiceException("User or card doesn't exist");
        }

        if (cardId != null) {
            Card card = cardService.getCardById(cardId);
            BigDecimal newBalance = card.getBalance().add(amount);
            cardService.updateCard(card);

            UserResponseDTO userResponseDTO = userServiceClient.getUserById(card.getUserId());
            paymentRepository.save(new Payment(cardId, userResponseDTO.getEmail(), newBalance));

            return createResponse(newBalance, userResponseDTO);
        }

        Card defaultCard = getDefaultCard(userId);
        BigDecimal newBalance = defaultCard.getBalance().add(amount);
        cardService.updateCard(defaultCard);

        UserResponseDTO userResponseDTO = userServiceClient.getUserById(defaultCard.getUserId());
        paymentRepository.save(new Payment(defaultCard.getCardId(), userResponseDTO.getEmail(), newBalance));

        return createResponse(newBalance, userResponseDTO);
    }

    private PaymentResponseDTO createResponse(BigDecimal newBalance, UserResponseDTO userResponseDTO) {
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO(newBalance, userResponseDTO.getEmail());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rabbitTemplate
                    .convertAndSend(TOPIC_EXCHANGE_PAYMENT, ROUTING_KEY_PAYMENT,
                            objectMapper.writeValueAsString(paymentResponseDTO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new PaymentServiceException("Can't send message to RabbitMQ");
        }
        return paymentResponseDTO;
    }

    private Card getDefaultCard(Long userId) {
        return cardService.getCardsByUserId(userId).stream()
                .filter(Card::isDefault)
                .findAny()
                .orElseThrow(() -> new PaymentServiceException("Unable to find default card for person: " + userId));
    }
}