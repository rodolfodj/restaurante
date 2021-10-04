package com.br.restaurante.kitchen.controller;

import com.br.restaurante.kitchen.service.KitchenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kitchen-orders")
@AllArgsConstructor
public class KitchenController {

    private KitchenService service;

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void dldl(@PathVariable(name = "id") String orderId) {
        service.orderDone(orderId);
    }
}