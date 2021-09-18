package com.br.restaurante.bar.config;

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

    public static final String ORDER_BAR_QUEUE = "order-bar-queue";
    public static final String BAR_ORDER_QUEUE = "bar-order-queue";

    public static final String ORDER_BAR_KEY = "order-bar";
    public static final String BAR_ORDER_KEY = "bar-order";


    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    @Bean
    public Queue declareOrderBarQueue() {
        return QueueBuilder.durable(ORDER_BAR_QUEUE)
                .build();
    }


    @Bean
    public Queue declareBarOrderQueue() {
        return QueueBuilder.durable(BAR_ORDER_QUEUE)
                .build();
    }

    @Bean
    public Binding declareBindingOrderBar(Exchange exchange) {
        return BindingBuilder.bind(declareOrderBarQueue())
                .to(exchange)
                .with(ORDER_BAR_KEY)
                .noargs();
    }

    @Bean
    public Binding declareBindingBarOrder(Exchange exchange) {
        return BindingBuilder.bind(declareBarOrderQueue())
                .to(exchange)
                .with(BAR_ORDER_KEY)
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
