package com.br.restaurante.kitchen.amqp;

import lombok.Data;

import java.util.List;

@Data
public class OrderMessage {

    private List<Item> items;
    private String orderId;

}