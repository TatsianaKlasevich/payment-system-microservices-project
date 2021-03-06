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

    public static final String QUEUE_PAYMENT = "js.payment.notify";
    private static final String TOPIC_EXCHANGE_PAYMENT = "js.payment.notify.exchange";
    private static final String ROUTING_KEY_PAYMENT = "js.key.payment";

    private final AmqpAdmin amqpAdmin;

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
