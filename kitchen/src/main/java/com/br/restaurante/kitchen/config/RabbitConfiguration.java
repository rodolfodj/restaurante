package com.br.restaurante.kitchen.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    public static final String EXCHANGE_NAME = "order-exchange";

    public static final String ORDER_KITCHEN_QUEUE = "order-kitchen-queue";
    public static final String KITCHEN_ORDER_QUEUE = "kitchen-order-queue";

    public static final String ORDER_KITCHEN_KEY = "order-kitchen";
    public static final String KITCHEN_ORDER_KEY = "kitchen-order";


    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    @Bean
    public Queue declareOrderKitchenQueue() {
        return QueueBuilder.durable(ORDER_KITCHEN_QUEUE)
                .build();
    }


    @Bean
    public Queue declareKitchenOrderQueue() {
        return QueueBuilder.durable(KITCHEN_ORDER_QUEUE)
                .build();
    }

    @Bean
    public Binding declareBindingOrderKitchen(Exchange exchange) {
        return BindingBuilder.bind(declareOrderKitchenQueue())
                .to(exchange)
                .with(ORDER_KITCHEN_KEY)
                .noargs();
    }

    @Bean
    public Binding declareBindingKitchenOrder(Exchange exchange) {
        return BindingBuilder.bind(declareKitchenOrderQueue())
                .to(exchange)
                .with(KITCHEN_ORDER_KEY)
                .noargs();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(RabbitConfiguration.EXCHANGE_NAME);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}