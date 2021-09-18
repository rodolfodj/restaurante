package com.br.restaurante.bar.amqp;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Item implements Serializable {

    private String name;
    private Integer quantity;
    private String note;

}