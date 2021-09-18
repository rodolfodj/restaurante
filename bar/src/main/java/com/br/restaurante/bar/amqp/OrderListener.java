package com.br.restaurante.bar.amqp;

import com.br.restaurante.bar.config.RabbitConfiguration;
import com.br.restaurante.bar.service.BarServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderListener {

    private BarServiceImpl service;

    @RabbitListener(queues = RabbitConfiguration.ORDER_BAR_QUEUE)
    public void consumeRoster(@Payload OrderMessage message) {
        service.receiveOrder(message);
    }

}