package com.br.restaurante.order.service;

import com.br.restaurante.order.amqp.NotificationMessage;
import com.br.restaurante.order.config.RabbitConfiguration;
import com.br.restaurante.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationService {

    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Order order) {
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME,
                RabbitConfiguration.NOTIFICATION_QUEUE,
                new NotificationMessage(order));

    }
}
