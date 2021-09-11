package com.br.restaurante.order.controller;

import com.br.restaurante.order.domain.StatusEnum;
import com.br.restaurante.order.dto.OrderConfimationDto;
import com.br.restaurante.order.dto.OrderDto;
import com.br.restaurante.order.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
@Slf4j
public class OrderController {

    private OrderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderConfimationDto registerOrder(@RequestBody @Valid OrderDto body) {

        Long id = service.addNewOrder(body);
        return new OrderConfimationDto(id, StatusEnum.CONFIRMED);

    }

    @GetMapping
    public List<OrderDto> getOrders() {

        return service.getOrders();
    }

}
