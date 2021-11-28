package com.klasevich.itrex.lab.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasevich.itrex.lab.controller.dto.DepositResponseDTO;
import com.klasevich.itrex.lab.controller.dto.PaymentResponseDTO;
import com.klasevich.itrex.lab.controller.dto.TransferResponseDTO;
import com.klasevich.itrex.lab.exception.TransactionServiceException;
import com.klasevich.itrex.lab.feign.UserResponseDTO;
import com.klasevich.itrex.lab.feign.UserServiceClient;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import com.klasevich.itrex.lab.persistance.entity.TransactionType;
import com.klasevich.itrex.lab.persistance.repository.TransactionRepository;
import com.klasevich.itrex.lab.service.CardService;
import com.klasevich.itrex.lab.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private static final String TOPIC_EXCHANGE_PAYMENT = "js.payment.notify.exchange";
    private static final String ROUTING_KEY_PAYMENT = "js.key.payment";
    private static final String TOPIC_EXCHANGE_DEPOSIT = "js.deposit.notify.exchange";
    private static final String ROUTING_KEY_DEPOSIT = "js.key.deposit";
    private static final String TOPIC_EXCHANGE_TRANSFER = "js.transfer.notify.exchange";
    private static final String ROUTING_KEY_TRANSFER = "js.key.transfer";

    private final TransactionRepository transactionRepository;
    private final UserServiceClient userServiceClient;
    private final CardService cardService;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public List<Transaction> getTransactionsByCardId(Long cardId) {
        return transactionRepository.findTransactionsByCardId(cardId);
    }

    @Override
    public Page<Transaction> getAllTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public DepositResponseDTO createDeposit(Transaction transaction) {
        if (transaction.getUserId() == null && transaction.getCard().getCardId() == null) {
            throw new TransactionServiceException("User or card doesn't exist");
        }

        if (transaction.getCard().getCardId() != null) {
            Card card = transaction.getCard();
            BigDecimal newBalance = changeCardAmount(card, transaction);

            UserResponseDTO userResponseDTO = userServiceClient.getUserById(card.getUserId());
            transaction.setEmail(userResponseDTO.getEmail());
            transaction.setAmount(newBalance);
            transactionRepository.save(transaction);

            return createDepositResponse(newBalance, userResponseDTO);
        }

        Card defaultCard = getDefaultCard(transaction.getUserId());
        BigDecimal newBalance = changeCardAmount(defaultCard, transaction);

        UserResponseDTO userResponseDTO = userServiceClient.getUserById(defaultCard.getUserId());
        transaction.setEmail(userResponseDTO.getEmail());
        transaction.setAmount(newBalance);
        transactionRepository.save(transaction);

        return createDepositResponse(newBalance, userResponseDTO);
    }


    @Override
    @Transactional
    public PaymentResponseDTO createPayment(Transaction transaction) {

        if (transaction.getUserId() == null && transaction.getCard().getCardId() == null) {
            throw new TransactionServiceException("User or card doesn't exist");
        }

        if (transaction.getCard().getCardId() != null) {
            Card card = transaction.getCard();

            if (card.getBalance().compareTo(BigDecimal.ZERO) <= 0 || transaction.getAmount().compareTo(card.getBalance()) > 0) {
                throw new TransactionServiceException("Not enough money for payment");
            }
            BigDecimal newBalance = changeCardAmount(card, transaction);

            UserResponseDTO userResponseDTO = userServiceClient.getUserById(card.getUserId());
            transaction.setEmail(userResponseDTO.getEmail());
            transaction.setAmount(newBalance);
            transactionRepository.save(transaction);

            return createPaymentResponse(newBalance, userResponseDTO, transaction);
        }

        Card defaultCard = getDefaultCard(transaction.getUserId());
        BigDecimal newBalance = changeCardAmount(defaultCard, transaction);

        UserResponseDTO userResponseDTO = userServiceClient.getUserById(defaultCard.getUserId());
        transaction.setEmail(userResponseDTO.getEmail());
        transaction.setAmount(newBalance);
        transactionRepository.save(transaction);

        return createPaymentResponse(newBalance, userResponseDTO, transaction);
    }

    @Override
    @Transactional
    public TransferResponseDTO createTransfer(Transaction transaction) {
        if (transaction.getUserId() == null && transaction.getCard().getCardId() == null) {
            throw new TransactionServiceException("User or card doesn't exist");
        }

        if (transaction.getCard().getCardId() != null) {
            Card cardRecipient = cardService.getCardById(transaction.getRecipientCardId());
            if (cardRecipient == null) {
                throw new TransactionServiceException("Recipient doesn't exist");
            }

            Card cardSender = transaction.getCard();
            if (cardSender.getBalance().compareTo(BigDecimal.ZERO) <= 0 || transaction.getAmount().compareTo(cardSender.getBalance()) > 0) {
                throw new TransactionServiceException("Not enough money for transfer");
            }
            BigDecimal newBalance = changeCardAmount(cardSender, cardRecipient, transaction);

            UserResponseDTO userResponseDTO = userServiceClient.getUserById(cardSender.getUserId());
            transaction.setEmail(userResponseDTO.getEmail());
            transaction.setAmount(newBalance);
            transactionRepository.save(transaction);

            return createTransferResponse(newBalance, userResponseDTO, transaction);
        }

        Card defaultCard = getDefaultCard(transaction.getUserId());
        Card cardRecipient = cardService.getCardById(transaction.getRecipientCardId());
        if (cardRecipient == null) {
            throw new TransactionServiceException("Recipient doesn't exist");
        }
        BigDecimal newBalance = changeCardAmount(defaultCard, cardRecipient, transaction);

        UserResponseDTO userResponseDTO = userServiceClient.getUserById(defaultCard.getUserId());
        transaction.setEmail(userResponseDTO.getEmail());
        transaction.setAmount(newBalance);
        transactionRepository.save(transaction);

        return createTransferResponse(newBalance, userResponseDTO, transaction);
    }

    private BigDecimal changeCardAmount(Card card, Transaction transaction) {
        BigDecimal newBalance;
        if (transaction.getTransactionType().equals(TransactionType.DEPOSIT)) {
            newBalance = card.getBalance().add(transaction.getAmount());
        } else if (transaction.getTransactionType().equals(TransactionType.PAYMENT)) {
            newBalance = card.getBalance().subtract(transaction.getAmount());
        } else {
            throw new TransactionServiceException("Payment type hasn't been defined");
        }
        card.setBalance(newBalance);
        cardService.updateCard(card);
        return newBalance;
    }

    private BigDecimal changeCardAmount(Card sender, Card recipient, Transaction transaction) {
        BigDecimal newSenderBalance = sender.getBalance().subtract(transaction.getAmount());
        BigDecimal newRecipientBalance = recipient.getBalance().add(transaction.getAmount());

        sender.setBalance(newSenderBalance);
        cardService.updateCard(sender);

        recipient.setBalance(newRecipientBalance);
        cardService.updateCard(recipient);
        return newSenderBalance;
    }

    private DepositResponseDTO createDepositResponse(BigDecimal newBalance, UserResponseDTO userResponseDTO) {
        DepositResponseDTO depositResponseDTO = DepositResponseDTO.builder()
                .amount(newBalance)
                .mail(userResponseDTO.getEmail())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rabbitTemplate
                    .convertAndSend(TOPIC_EXCHANGE_DEPOSIT, ROUTING_KEY_DEPOSIT,
                            objectMapper.writeValueAsString(depositResponseDTO));
        } catch (JsonProcessingException e) {
            throw new TransactionServiceException("Can't send message to RabbitMQ", e);
        }
        return depositResponseDTO;
    }


    private TransferResponseDTO createTransferResponse(BigDecimal newBalance, UserResponseDTO userResponseDTO, Transaction transaction) {
        TransferResponseDTO transferResponseDTO = TransferResponseDTO.builder()
                .amount(newBalance)
                .mail(userResponseDTO.getEmail())
                .recipientCardId(transaction.getRecipientCardId())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rabbitTemplate
                    .convertAndSend(TOPIC_EXCHANGE_TRANSFER, ROUTING_KEY_TRANSFER,
                            objectMapper.writeValueAsString(transferResponseDTO));
        } catch (JsonProcessingException e) {
            throw new TransactionServiceException("Can't send message to RabbitMQ", e);
        }
        return transferResponseDTO;
    }

    private PaymentResponseDTO createPaymentResponse(BigDecimal newBalance,
                                                     UserResponseDTO userResponseDTO, Transaction transaction) {
        PaymentResponseDTO paymentResponseDTO = PaymentResponseDTO.builder()
                .amount(newBalance)
                .mail(userResponseDTO.getEmail())
                .unp(transaction.getUnp())
                .purposeOfPayment(transaction.getPurposeOfPayment())
                .bankCode(transaction.getBankCode())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rabbitTemplate
                    .convertAndSend(TOPIC_EXCHANGE_PAYMENT, ROUTING_KEY_PAYMENT,
                            objectMapper.writeValueAsString(paymentResponseDTO));
        } catch (JsonProcessingException e) {
            throw new TransactionServiceException("Can't send message to RabbitMQ", e);
        }
        return paymentResponseDTO;
    }

    private Card getDefaultCard(Long userId) {
        return cardService.getCardsByUserId(userId).stream()
                .filter(Card::getIsDefault)
                .findAny()
                .orElseThrow(() -> new TransactionServiceException("Unable to find default card for person: " + userId));
    }
}