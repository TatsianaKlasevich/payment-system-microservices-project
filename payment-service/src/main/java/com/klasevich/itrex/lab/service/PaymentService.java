package com.klasevich.itrex.lab.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasevich.itrex.lab.controller.dto.PaymentResponseDTO;
import com.klasevich.itrex.lab.entity.Card;
import com.klasevich.itrex.lab.entity.Payment;
import com.klasevich.itrex.lab.exception.PaymentServiceException;
import com.klasevich.itrex.lab.repository.PaymentRepository;
import com.klasevich.itrex.lab.rest.UserResponseDTO;
import com.klasevich.itrex.lab.rest.UserServiceClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {
    private static final String TOPIC_EXCHANGE_PAYMENT = "js.payment.notify.exchange";
    private static final String ROUTING_KEY_PAYMENT = "js.key.payment";

    private final PaymentRepository paymentRepository;
    private final UserServiceClient userServiceClient;
    private final CardService cardService;
    private final RabbitTemplate rabbitTemplate;

    public PaymentService(PaymentRepository paymentRepository, UserServiceClient userServiceClient,
                          CardService cardService, RabbitTemplate rabbitTemplate) {
        this.paymentRepository = paymentRepository;
        this.userServiceClient = userServiceClient;
        this.cardService = cardService;
        this.rabbitTemplate = rabbitTemplate;
    }

    public PaymentResponseDTO deposit(int userId, int cardId, BigDecimal amount) {
        if (userId == 0 && cardId == 0) {
            throw new PaymentServiceException("Person or card doesn't exist");
        }

        if (cardId != 0) {
            Card card = cardService.getCardById(cardId);
            BigDecimal newBalance = card.getBalance().add(amount);
            cardService.updateCard(cardId, newBalance, card.getCurrency(), card.getCardNumber(), card.isDefault(),
                    card.getCardStatus(), card.getExpirationDate(), card.getCvvCode(), card.getUserId());

            UserResponseDTO userResponseDTO = userServiceClient.getUserById(card.getUserId());
            paymentRepository.save(new Payment(cardId, userResponseDTO.getEmail(), newBalance)); //todo add more

            return createResponse(newBalance, userResponseDTO);
        }

        Card defaultCard = getDefaultCard(userId);
        BigDecimal newBalance = defaultCard.getBalance().add(amount);
        cardService.updateCard(defaultCard.getCardId(), newBalance, defaultCard.getCurrency(),
                defaultCard.getCardNumber(), defaultCard.isDefault(), defaultCard.getCardStatus(),
                defaultCard.getExpirationDate(), defaultCard.getCvvCode(), defaultCard.getUserId());

        UserResponseDTO personResponseDTO = userServiceClient.getUserById(defaultCard.getUserId());
        paymentRepository.save(new Payment(defaultCard.getCardId(), personResponseDTO.getEmail(), newBalance));

        return createResponse(newBalance, personResponseDTO);
    }

    private PaymentResponseDTO createResponse(BigDecimal newBalance, UserResponseDTO personResponseDTO) {
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO(newBalance, personResponseDTO.getEmail());

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

    private Card getDefaultCard(int personId) {
        return cardService.getCardsByUserId(personId).stream()
                .filter(Card::isDefault)
                .findAny()
                .orElseThrow(() -> new PaymentServiceException("Unable to find default card for person: " + personId));
    }
}
