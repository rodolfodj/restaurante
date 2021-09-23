package com.br.restaurante.order.amqp;

import com.br.restaurante.order.domain.Order;
import lombok.Data;

@Data
public class NotificationMessage {

    private Long id;
    private String waiter;
    private Integer table;

    public NotificationMessage(Order o) {
        this.id = o.getId();
        this.waiter = o.getWaiter();
        this.table = o.getTableNo();
    }
}