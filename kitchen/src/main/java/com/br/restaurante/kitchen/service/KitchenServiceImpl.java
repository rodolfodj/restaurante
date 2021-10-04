package com.br.restaurante.kitchen.service;

import com.br.restaurante.kitchen.amqp.KitchenMessage;
import com.br.restaurante.kitchen.amqp.OrderMessage;
import com.br.restaurante.kitchen.config.RabbitConfiguration;
import com.br.restaurante.kitchen.domain.KitchenOrder;
import com.br.restaurante.kitchen.domain.Status;
import com.br.restaurante.kitchen.exception.PedidoNaoEncontradoException;
import com.br.restaurante.kitchen.repository.KitchenRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KitchenServiceImpl implements KitchenService {

    private KitchenRepository repository;
    private RabbitTemplate rabbitTemplate;


    @Override
    public void receiveOrder(OrderMessage message) {
        var order = new KitchenOrder();
        order.setOrder(message.getOrderId());
        order.setStatus(Status.RECEIVED);
        message.getItems().forEach(order::addItem);

        repository.save(order);
    }


    @Override
    public void orderDone(String orderId) {
        KitchenOrder order = repository.findById(orderId).orElseThrow(PedidoNaoEncontradoException::new);
        order.setStatus(Status.DONE);
        repository.save(order);
        sendKitchenOrderToOrder(orderId);
    }

    private void sendKitchenOrderToOrder(String orderId) {
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME,
                RabbitConfiguration.KITCHEN_ORDER_KEY,
                new KitchenMessage(orderId));
    }
}