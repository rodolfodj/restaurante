package com.br.restaurante.order.amqp;

import lombok.Data;

@Data
public class StatusOrderMessage {

    private Long orderId;
}
