package com.br.restaurante.order.amqp;

import com.br.restaurante.order.domain.BarItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BarRequestMessage {

    private List<BarItem> barItems;

    private String orderId;
}
