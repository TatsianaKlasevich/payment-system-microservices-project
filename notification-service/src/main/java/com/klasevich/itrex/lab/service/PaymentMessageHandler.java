package com.klasevich.itrex.lab.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasevich.itrex.lab.config.RabbitMQConfig;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class PaymentMessageHandler {
    private final JavaMailSender javaMailSender;

    @Autowired
    public PaymentMessageHandler(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_PAYMENT) //from where come messages
    public void receive(Message message) throws JsonProcessingException {
        System.out.println("message " + message); //todo
        byte[] body = message.getBody();
        String jsonBody = new String(body);
        ObjectMapper objectMapper = new ObjectMapper();
        PaymentResponseDTO paymentResponseDTO = objectMapper.readValue(jsonBody, PaymentResponseDTO.class);
        System.out.println("paymentResponseDTO " + paymentResponseDTO); //todo

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(paymentResponseDTO.getMail());
        mailMessage.setFrom("tanya@gmail.com");

        mailMessage.setSubject("Payment");
        mailMessage.setText("Make payment, sum: " + paymentResponseDTO.getAmount()); //todo change text

        try {
            javaMailSender.send(mailMessage);
        } catch (Exception exception) {
            System.out.println(exception); //todo
//           throw new AmqpRejectAndDontRequeueException(exception); //todo
        }
    }
}
