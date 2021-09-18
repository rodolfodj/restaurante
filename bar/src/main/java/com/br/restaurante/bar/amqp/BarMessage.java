package com.br.restaurante.bar.amqp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BarMessage {

    private final String orderId;

    public BarMessage(String orderId) {
        this.orderId = orderId;
    }
}
