package com.klasevich.itrex.lab.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {
    public static final String QUEUE_PAYMENT = "js.payment.notify";
    private static final String TOPIC_EXCHANGE_PAYMENT = "js.payment.notify.exchange";
    private static final String ROUTING_KEY_PAYMENT = "js.key.payment";

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Bean
    public TopicExchange paymentExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_PAYMENT);
    }

    @Bean
    public Queue queuePayment() {
        return new Queue(QUEUE_PAYMENT);
    }

    @Bean
    public Binding paymentBinding() {
        return BindingBuilder
                .bind(queuePayment())
                .to(paymentExchange())
                .with(ROUTING_KEY_PAYMENT);
    }
}