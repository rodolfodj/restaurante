package com.br.restaurante.kitchen.service;

import com.br.restaurante.kitchen.amqp.OrderMessage;

public interface KitchenService {
    void receiveOrder(OrderMessage message);

    void orderDone(String orderId);
}