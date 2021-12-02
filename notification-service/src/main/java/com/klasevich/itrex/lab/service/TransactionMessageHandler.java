package com.klasevich.itrex.lab.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasevich.itrex.lab.config.RabbitMQConfig;
import com.klasevich.itrex.lab.exception.SendMailException;
import com.klasevich.itrex.lab.service.dto.TransactionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class TransactionMessageHandler {

    private static final String MAIL_MESSAGE = "%s has been successful, %s sum - %s";
    private static final String MAIL_EXCEPTION_MESSAGE = "Some problems with sending message";
    private final JavaMailSender javaMailSender;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_PAYMENT)
    public void receive(Message message) throws JsonProcessingException, SendMailException {
        byte[] body = message.getBody();
        String jsonBody = new String(body);

        TransactionResponseDTO transactionResponseDTO = objectMapper.readValue(jsonBody, TransactionResponseDTO.class);
        String transactionType = transactionResponseDTO.getTransactionType().toString().toLowerCase(Locale.ROOT);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(transactionResponseDTO.getEmail());
        mailMessage.setFrom("mail@gmail.com");

        mailMessage.setSubject(transactionType);
        mailMessage.setText(String.format(MAIL_MESSAGE, transactionType, transactionType, transactionResponseDTO.getAmount()));

        try {
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            throw new SendMailException(MAIL_EXCEPTION_MESSAGE, e);
        }
    }
}
