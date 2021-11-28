package com.klasevich.itrex.lab.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasevich.itrex.lab.config.RabbitMQConfig;
import com.klasevich.itrex.lab.exception.SendMailException;
import com.klasevich.itrex.lab.service.dto.DepositResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DepositMessageHandler {
    private final JavaMailSender javaMailSender;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_DEPOSIT)
    public void receive(Message message) throws JsonProcessingException, SendMailException {
        byte[] body = message.getBody();
        String jsonBody = new String(body);
        ObjectMapper objectMapper = new ObjectMapper();
        DepositResponseDTO depositResponseDTO = objectMapper.readValue(jsonBody, DepositResponseDTO.class);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(depositResponseDTO.getMail());
        mailMessage.setFrom("mail@gmail.com");

        mailMessage.setSubject("Deposit");
        mailMessage.setText("Deposit was successful, your balance on card is " + depositResponseDTO.getAmount());

        try {
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            throw new SendMailException("Some problems with sending message", e);
        }
    }
}
