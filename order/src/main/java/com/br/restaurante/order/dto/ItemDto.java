package com.br.restaurante.order.dto;

import com.br.restaurante.order.domain.Item;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class ItemDto {

    public ItemDto(Item i) {
        BeanUtils.copyProperties(i, this);
    }

    private String name;
    private Integer quantity;
    private String note;
}
