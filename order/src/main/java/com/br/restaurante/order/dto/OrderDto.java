package com.br.restaurante.order.dto;

import com.br.restaurante.order.domain.StatusEnum;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
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

    @AssertTrue(message = "no items for this order")
    public boolean hasValidQuantityItems() {
        return (kitchenItens != null && !kitchenItens.isEmpty())
                || (barItens != null && !barItens.isEmpty());
    }


}
