package com.klasevich.itrex.lab.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasevich.itrex.lab.controller.dto.DepositResponseDTO;
import com.klasevich.itrex.lab.controller.dto.PaymentResponseDTO;
import com.klasevich.itrex.lab.controller.dto.TransactionResponseDTO;
import com.klasevich.itrex.lab.controller.dto.TransferResponseDTO;
import com.klasevich.itrex.lab.exception.TransactionServiceException;
import com.klasevich.itrex.lab.feign.UserServiceClient;
import com.klasevich.itrex.lab.feign.dto.UserResponseDTO;
import com.klasevich.itrex.lab.mapper.TransactionToTransactionResponseDTOMapper;
import com.klasevich.itrex.lab.persistance.entity.Card;
import com.klasevich.itrex.lab.persistance.entity.Transaction;
import com.klasevich.itrex.lab.persistance.repository.TransactionRepository;
import com.klasevich.itrex.lab.service.CardService;
import com.klasevich.itrex.lab.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.klasevich.itrex.lab.util.ServiceData.DEFAULT_CARD_EXCEPTION_MESSAGE;
import static com.klasevich.itrex.lab.util.ServiceData.MONEY_EXCEPTION_MESSAGE;
import static com.klasevich.itrex.lab.util.ServiceData.RABBIT_MQ_EXCEPTION_MESSAGE;
import static com.klasevich.itrex.lab.util.ServiceData.ROUTING_KEY_PAYMENT;
import static com.klasevich.itrex.lab.util.ServiceData.TOPIC_EXCHANGE_PAYMENT;
import static com.klasevich.itrex.lab.util.ServiceData.TRANSACTION_EXCEPTION_MESSAGE;


@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserServiceClient userServiceClient;
    private final CardService cardService;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final TransactionToTransactionResponseDTOMapper transactionToTransactionResponseDTOMapper;

    @Override
    public List<Transaction> getTransactionsByCardId(Long cardId) {
        return transactionRepository.findTransactionsByCard_CardId(cardId);
    }

    @Override
    public Page<Transaction> getAllTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public DepositResponseDTO createDeposit(Transaction transaction) {
        checkUserAndCardExist(transaction);

        Card card;

        if (transaction.getCard().getCardId() != null) {
            card = transaction.getCard();
        } else {
            card = getDefaultCard(transaction.getUserId());
        }

        BigDecimal newBalance = increaseCardBalance(card, transaction);
        saveTransaction(card, transaction);

        TransactionResponseDTO transactionResponseDTO = transactionToTransactionResponseDTOMapper.convert(transaction);
        sendMessage(transactionResponseDTO);

        return DepositResponseDTO.builder()
                .balance(newBalance)
                .mail(transaction.getEmail())
                .build();
    }

    @Override
    @Transactional
    public PaymentResponseDTO createPayment(Transaction transaction) {
        checkUserAndCardExist(transaction);

        Card card;

        if (transaction.getCard().getCardId() != null) {
            card = transaction.getCard();
        } else {
            card = getDefaultCard(transaction.getUserId());
        }

        checkEnoughMoneyOnCard(card, transaction);

        BigDecimal newBalance = reduceCardBalance(card, transaction);
        saveTransaction(card, transaction);

        TransactionResponseDTO transactionResponseDTO = transactionToTransactionResponseDTOMapper.convert(transaction);
        sendMessage(transactionResponseDTO);

        return PaymentResponseDTO.builder()
                .balance(newBalance)
                .mail(transaction.getEmail())
                .bankCode(transaction.getBankCode())
                .purposeOfPayment(transaction.getPurposeOfPayment())
                .unp(transaction.getUnp())
                .build();
    }

    @Override
    @Transactional
    public TransferResponseDTO createTransfer(Transaction transaction) {
        checkUserAndCardExist(transaction);

        Card recipientCard = cardService.getCardById(transaction.getRecipientCardId());
        if (recipientCard == null) {
            throw new TransactionServiceException("Recipient doesn't exist");
        }

        Card senderCard;

        if (transaction.getCard().getCardId() != null) {
            senderCard = transaction.getCard();
        } else {
            senderCard = getDefaultCard(transaction.getUserId());
        }

        checkEnoughMoneyOnCard(senderCard, transaction);

        BigDecimal recipientBalance = increaseCardBalance(recipientCard, transaction);
        BigDecimal senderBalance = reduceCardBalance(senderCard, transaction);
        saveTransaction(senderCard, transaction);

        TransactionResponseDTO transactionResponseDTO = transactionToTransactionResponseDTOMapper.convert(transaction);
        sendMessage(transactionResponseDTO);

        return TransferResponseDTO.builder()
                .balance(senderBalance)
                .mail(transaction.getEmail())
                .recipientCardId(transaction.getRecipientCardId())
                .build();
    }

    private void checkEnoughMoneyOnCard(Card card, Transaction transaction) {
        if (card.getBalance().compareTo(BigDecimal.ZERO) < 0
                || transaction.getAmount().compareTo(card.getBalance()) > 0) {
            throw new TransactionServiceException(MONEY_EXCEPTION_MESSAGE);
        }
    }

    private void checkUserAndCardExist(Transaction transaction) {
        if (transaction.getUserId() == null && transaction.getCard().getCardId() == null) {
            throw new TransactionServiceException(TRANSACTION_EXCEPTION_MESSAGE);
        }
    }

    private void saveTransaction(Card card, Transaction transaction) {
        UserResponseDTO userResponseDTO = userServiceClient.getUserById(card.getUserId());
        transaction.setEmail(userResponseDTO.getEmail());
        transactionRepository.save(transaction);
    }

    private BigDecimal increaseCardBalance(Card card, Transaction transaction) {
        BigDecimal newBalance = card.getBalance().add(transaction.getAmount());
        card.setBalance(newBalance);
        cardService.updateCard(card);
        return newBalance;
    }

    private BigDecimal reduceCardBalance(Card card, Transaction transaction) {
        BigDecimal newBalance = card.getBalance().subtract(transaction.getAmount());
        card.setBalance(newBalance);
        cardService.updateCard(card);
        return newBalance;
    }

    private void sendMessage(TransactionResponseDTO transactionResponseDTO) {
        try {
            rabbitTemplate
                    .convertAndSend(TOPIC_EXCHANGE_PAYMENT, ROUTING_KEY_PAYMENT,
                            objectMapper.writeValueAsString(transactionResponseDTO));
        } catch (JsonProcessingException e) {
            throw new TransactionServiceException(RABBIT_MQ_EXCEPTION_MESSAGE, e);
        }
    }

    private Card getDefaultCard(Long userId) {
        return cardService.getCardsByUserId(userId).stream()
                .filter(Card::getIsDefault)
                .findAny()
                .orElseThrow(() -> new TransactionServiceException(String.format(DEFAULT_CARD_EXCEPTION_MESSAGE, userId)));
    }
}