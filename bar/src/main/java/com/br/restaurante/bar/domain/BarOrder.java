package com.br.restaurante.bar.domain;

import com.br.restaurante.bar.amqp.Item;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "bar_order")
@Getter
@Setter
public class BarOrder {

    @Id
    private String order;

    private Status status;

    private List<Item> items;

    public void addItem(Item item) {
        if (items == null) items = new ArrayList<>();

        items.add(item);
    }
}
