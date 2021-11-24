package com.klasevich.itrex.lab.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {
    public static final String QUEUE_DEPOSIT = "js.deposit.notify";
    public static final String QUEUE_PAYMENT = "js.payment.notify";
    private static final String TOPIC_EXCHANGE_PAYMENT = "js.payment.notify.exchange";
    private static final String ROUTING_KEY_PAYMENT = "js.key.payment";
    private static final String TOPIC_EXCHANGE_DEPOSIT = "js.deposit.notify.exchange";
    private static final String ROUTING_KEY_DEPOSIT = "js.key.deposit";

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Bean
    public TopicExchange paymentExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_PAYMENT);
    }

    @Bean
    public TopicExchange depositExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_DEPOSIT);
    }

    @Bean
    public Queue queuePayment() {
        return new Queue(QUEUE_PAYMENT);
    }

    @Bean
    public Queue queueDeposit() {
        return new Queue(QUEUE_DEPOSIT);
    }

    @Bean
    public Binding paymentBinding() {
        return BindingBuilder
                .bind(queuePayment())
                .to(paymentExchange())
                .with(ROUTING_KEY_PAYMENT);
    }

    @Bean
    public Binding depositBinding() {
        return BindingBuilder
                .bind(queueDeposit())
                .to(depositExchange())
                .with(ROUTING_KEY_DEPOSIT);
    }
}
