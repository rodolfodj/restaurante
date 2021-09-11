package com.br.restaurante.order.dto;

import com.br.restaurante.order.domain.StatusEnum;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private Long id;
    private String waiter;
    private Integer table;
    private List<ItemDto> kitchenItens;
    private List<ItemDto> barItens;
    private StatusEnum statusBar;
    private StatusEnum statusKitchen;


}
