package com.klasevich.itrex.lab.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasevich.itrex.lab.config.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class DepositMessageHandler {
    private final JavaMailSender javaMailSender;

    @Autowired
    public DepositMessageHandler(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_DEPOSIT) //from where come messages
    public void receive(Message message) throws JsonProcessingException {
        System.out.println("message " + message); //todo
        byte[] body = message.getBody();
        String jsonBody = new String(body);
        ObjectMapper objectMapper = new ObjectMapper();
        DepositResponseDTO depositResponseDTO = objectMapper.readValue(jsonBody, DepositResponseDTO.class);
        System.out.println("depositResponseDTO " + depositResponseDTO); //todo

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(depositResponseDTO.getMail());
        mailMessage.setFrom("mail@gmail.com");

        mailMessage.setSubject("Deposit");
        mailMessage.setText("Deposit was successful, sum: " + depositResponseDTO.getAmount()); //todo change text

        try {
            javaMailSender.send(mailMessage);
        } catch (Exception exception) {
            System.out.println(exception); //todo
//           throw new AmqpRejectAndDontRequeueException(exception); //todo
        }
    }
}
