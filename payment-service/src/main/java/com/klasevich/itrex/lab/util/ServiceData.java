package com.klasevich.itrex.lab.util;


public class ServiceData {

    public static final String TOPIC_EXCHANGE_PAYMENT = "js.payment.notify.exchange";
    public static final String ROUTING_KEY_PAYMENT = "js.key.payment";

    public static final String MONEY_EXCEPTION_MESSAGE = "Not enough money for payment";
    public static final String TRANSACTION_EXCEPTION_MESSAGE = "User or card doesn't exist";
    public static final String RABBIT_MQ_EXCEPTION_MESSAGE = "Can't send message to RabbitMQ";
    public static final String DEFAULT_CARD_EXCEPTION_MESSAGE = "Unable to find default card for userId: %d";
    public static final String CARD_EXCEPTION_MESSAGE = "Unable to find card with cardId: %d";

    private ServiceData() {
    }
}
