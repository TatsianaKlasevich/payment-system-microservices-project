package com.klasevich.itrex.lab.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@EnableRabbit
public class RabbitMQConfig {
    public static final String QUEUE_DEPOSIT = "js.deposit.notify";
    public static final String QUEUE_PAYMENT = "js.payment.notify";
    public static final String QUEUE_TRANSFER = "js.transfer.notify";
    private static final String TOPIC_EXCHANGE_PAYMENT = "js.payment.notify.exchange";
    private static final String ROUTING_KEY_PAYMENT = "js.key.payment";
    private static final String TOPIC_EXCHANGE_DEPOSIT = "js.deposit.notify.exchange";
    private static final String ROUTING_KEY_DEPOSIT = "js.key.deposit";
    private static final String TOPIC_EXCHANGE_TRANSFER = "js.transfer.notify.exchange";
    private static final String ROUTING_KEY_TRANSFER = "js.key.transfer";

    private final AmqpAdmin amqpAdmin;

    @Bean
    public TopicExchange paymentExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_PAYMENT);
    }

    @Bean
    public TopicExchange depositExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_DEPOSIT);
    }

    @Bean
    public TopicExchange transferExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_TRANSFER);
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
    public Queue queueTransfer() {
        return new Queue(QUEUE_TRANSFER);
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

    @Bean
    public Binding transferBinding() {
        return BindingBuilder
                .bind(queueTransfer())
                .to(transferExchange())
                .with(ROUTING_KEY_TRANSFER);
    }
}
