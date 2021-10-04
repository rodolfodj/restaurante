package com.br.restaurante.kitchen.amqp;

import com.br.restaurante.kitchen.config.RabbitConfiguration;
import com.br.restaurante.kitchen.service.KitchenServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderListener {

    private KitchenServiceImpl service;

    @RabbitListener(queues = RabbitConfiguration.ORDER_KITCHEN_QUEUE)
    public void consumeRoster(@Payload OrderMessage message) {
        service.receiveOrder(message);
    }

}