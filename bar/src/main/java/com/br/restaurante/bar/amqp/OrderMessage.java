package com.br.restaurante.bar.amqp;

import lombok.Data;

import java.util.List;

@Data
public class OrderMessage {

    private List<Item> barItems;
    private String orderId;

}
