package com.br.restaurante.notification.domain;

import lombok.Data;

@Data
public class Order {

    private Long id;
    private String waiter;
    private Integer table;
}