package com.br.restaurante.bar.service;

import com.br.restaurante.bar.amqp.OrderMessage;

public interface BarService {
    void receiveOrder(OrderMessage message);

    void orderDone(String orderId);
}