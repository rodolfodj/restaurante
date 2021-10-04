package com.br.restaurante.kitchen.domain;

import com.br.restaurante.kitchen.amqp.Item;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "kitchen_order")
@Getter
@Setter
public class KitchenOrder {

    @Id
    private String order;

    private Status status;

    private List<Item> items;

    public void addItem(Item item) {
        if (items == null) items = new ArrayList<>();

        items.add(item);
    }
}