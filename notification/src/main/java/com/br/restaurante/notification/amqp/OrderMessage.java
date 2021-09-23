package com.br.restaurante.notification.amqp;

import lombok.Data;

@Data
public class OrderMessage {

    private Long id;
    private String waiter;
    private Integer table;

}