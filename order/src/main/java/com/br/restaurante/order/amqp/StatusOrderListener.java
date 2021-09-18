package com.br.restaurante.order.amqp;

import com.br.restaurante.order.config.RabbitConfiguration;
import com.br.restaurante.order.domain.TipoItemEnum;
import com.br.restaurante.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StatusOrderListener {

    private OrderService service;

    @RabbitListener(queues = RabbitConfiguration.BAR_ORDER_QUEUE)
    public void consumeBar(@Payload StatusOrderMessage message) {
        service.updateOrderStatus(message.getOrderId(), TipoItemEnum.BAR);

    }

    @RabbitListener(queues = RabbitConfiguration.KITCHEN_ORDER_QUEUE)
    public void consumeKitchen(@Payload StatusOrderMessage message) {
        service.updateOrderStatus(message.getOrderId(), TipoItemEnum.KITCHEN);
    }
}
