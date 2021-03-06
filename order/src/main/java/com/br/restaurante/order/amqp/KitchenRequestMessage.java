package com.br.restaurante.order.amqp;

import com.br.restaurante.order.domain.KitchenItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class KitchenRequestMessage {

    private List<KitchenItem> items;

    private String orderId;

}