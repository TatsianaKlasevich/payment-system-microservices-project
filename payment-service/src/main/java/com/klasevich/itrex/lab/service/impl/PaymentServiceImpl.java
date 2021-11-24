package com.klasevich.itrex.lab.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasevich.itrex.lab.controller.dto.DepositResponseDTO;
import com.klasevich.itrex.lab.controller.dto.PaymentResponseDTO;
import com.klasevich.itrex.lab.exception.PaymentServiceException;
import com.klasevich.itrex.lab.feign.UserResponseDTO;
import com.klasevich.itrex.lab.feign.UserServiceClient;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.Payment;
import com.klasevich.itrex.lab.persistance.entity.PaymentType;
import com.klasevich.itrex.lab.persistance.repository.PaymentRepository;
import com.klasevich.itrex.lab.service.CardService;
import com.klasevich.itrex.lab.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private static final String TOPIC_EXCHANGE_PAYMENT = "js.payment.notify.exchange";
    private static final String ROUTING_KEY_PAYMENT = "js.key.payment";
    private static final String TOPIC_EXCHANGE_DEPOSIT = "js.deposit.notify.exchange";
    private static final String ROUTING_KEY_DEPOSIT = "js.key.deposit";

    private final PaymentRepository paymentRepository;
    private final UserServiceClient userServiceClient;
    private final CardService cardService;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public DepositResponseDTO deposit(Payment payment) {
        if (payment.getUserId() == null && payment.getCard().getCardId() == null) {
            throw new PaymentServiceException("User or card doesn't exist");
        }

        if (payment.getCard().getCardId() != null) {
            Card card = payment.getCard();
            BigDecimal newBalance = changeCardAmount(card, payment);

            UserResponseDTO userResponseDTO = userServiceClient.getUserById(card.getUserId());
            payment.setEmail(userResponseDTO.getEmail());
            payment.setAmount(newBalance);
            paymentRepository.save(payment);

            return createDepositResponse(newBalance, userResponseDTO);
        }

        Card defaultCard = getDefaultCard(payment.getUserId());
        BigDecimal newBalance = changeCardAmount(defaultCard, payment);

        UserResponseDTO userResponseDTO = userServiceClient.getUserById(defaultCard.getUserId());
        payment.setEmail(userResponseDTO.getEmail());
        payment.setAmount(newBalance);
        paymentRepository.save(payment);

        return createDepositResponse(newBalance, userResponseDTO);
    }

    @Override
    public List<Payment> getPaymentsByCardId(Long cardId) {
        return paymentRepository.findPaymentsByCardId(cardId);
    }

    @Override
    public PaymentResponseDTO createPayment(Payment payment) {

        if (payment.getUserId() == null && payment.getCard().getCardId() == null) {
            throw new PaymentServiceException("User or card doesn't exist");
        }

        if (payment.getCard().getCardId() != null) {
            Card card = payment.getCard();
//            if(card.getBalance()<=0||payment.getAmount().compareTo(card.getBalance())=-1){
//                throw new PaymentServiceException("User or card doesn't exist");
//            }
            BigDecimal newBalance = changeCardAmount(card, payment);

            UserResponseDTO userResponseDTO = userServiceClient.getUserById(card.getUserId());
            payment.setEmail(userResponseDTO.getEmail());
            payment.setAmount(newBalance);
            paymentRepository.save(payment);

            return createPaymentResponse(newBalance, userResponseDTO, payment);
        }

        Card defaultCard = getDefaultCard(payment.getUserId());
        BigDecimal newBalance = changeCardAmount(defaultCard, payment);

        UserResponseDTO userResponseDTO = userServiceClient.getUserById(defaultCard.getUserId());
        payment.setEmail(userResponseDTO.getEmail());
        payment.setAmount(newBalance);
        paymentRepository.save(payment);

        return createPaymentResponse(newBalance, userResponseDTO, payment);
    }

    private BigDecimal changeCardAmount(Card card, Payment payment) {
        BigDecimal newBalance;
        if (payment.getPaymentType().equals(PaymentType.DEPOSIT)) {
            newBalance = card.getBalance().add(payment.getAmount());
        } else if (payment.getPaymentType().equals(PaymentType.PAYMENT)) {
            newBalance = card.getBalance().subtract(payment.getAmount());
        } else {
            throw new PaymentServiceException("Payment type hasn't been defined");
        }
        card.setBalance(newBalance);
        cardService.updateCard(card);
        return newBalance;
    }

    private DepositResponseDTO createDepositResponse(BigDecimal newBalance, UserResponseDTO userResponseDTO) {
        DepositResponseDTO depositResponseDTO = new DepositResponseDTO(newBalance, userResponseDTO.getEmail());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rabbitTemplate
                    .convertAndSend(TOPIC_EXCHANGE_DEPOSIT, ROUTING_KEY_DEPOSIT,
                            objectMapper.writeValueAsString(depositResponseDTO));
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException {}", e);
            throw new PaymentServiceException("Can't send message to RabbitMQ");
        }
        return depositResponseDTO;
    }

    private PaymentResponseDTO createPaymentResponse(BigDecimal newBalance,
                                                     UserResponseDTO userResponseDTO, Payment payment) {
        PaymentResponseDTO paymentResponseDTO = PaymentResponseDTO.builder()
                .amount(newBalance)
                .mail(userResponseDTO.getEmail())
                .unp(payment.getUnp())
                .purposeOfPayment(payment.getPurposeOfPayment())
                .bankCode(payment.getBankCode())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rabbitTemplate
                    .convertAndSend(TOPIC_EXCHANGE_PAYMENT, ROUTING_KEY_PAYMENT,
                            objectMapper.writeValueAsString(paymentResponseDTO));
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException {}", e);
            throw new PaymentServiceException("Can't send message to RabbitMQ");
        }
        return paymentResponseDTO;
    }

    private Card getDefaultCard(Long userId) {
        return cardService.getCardsByUserId(userId).stream()
                .filter(Card::getIsDefault)
                .findAny()
                .orElseThrow(() -> new PaymentServiceException("Unable to find default card for person: " + userId));
    }
}