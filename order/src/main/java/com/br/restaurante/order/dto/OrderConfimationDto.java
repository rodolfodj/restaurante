package com.br.restaurante.order.dto;

import com.br.restaurante.order.domain.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderConfimationDto {

    private Long id;
    private StatusEnum statusEnum;

}
