package com.br.restaurante.bar.controller;

import com.br.restaurante.bar.service.BarServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bar-orders")
@AllArgsConstructor
public class BarController {

    private BarServiceImpl service;

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void dldl(@PathVariable(name = "id") String orderId) {
        service.orderDone(orderId);
    }
}