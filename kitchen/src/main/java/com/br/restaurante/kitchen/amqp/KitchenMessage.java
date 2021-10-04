package com.br.restaurante.kitchen.amqp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitchenMessage {

    private final String orderId;

    public KitchenMessage(String orderId) {
        this.orderId = orderId;
    }
}