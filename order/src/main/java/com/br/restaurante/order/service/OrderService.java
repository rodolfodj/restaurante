package com.br.restaurante.order.service;

import com.br.restaurante.order.domain.Item;
import com.br.restaurante.order.domain.Order;
import com.br.restaurante.order.domain.TipoItemEnum;
import com.br.restaurante.order.dto.ItemDto;
import com.br.restaurante.order.dto.OrderDto;
import com.br.restaurante.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository repository;

    public Long addNewOrder(OrderDto body) {

        Order target = new Order();
        BeanUtils.copyProperties(body, target);

        body.getBarItens().stream().map(it -> {
            Item item = new Item();
            BeanUtils.copyProperties(it, item);
            item.setType(TipoItemEnum.BAR);
            return item;
        }).forEach(target::addItem);
        body.getKitchenItens().stream().map(it -> {
            Item item = new Item();
            BeanUtils.copyProperties(it, item);
            item.setType(TipoItemEnum.KITCHEN);
            return item;
        }).forEach(target::addItem);

        repository.save(target);
        return target.getId();

    }

    public List<OrderDto> getOrders() {
        List<Order> all = repository.findAll();
        return all.stream().map(order -> {
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(order, orderDto);
            orderDto.setBarItens(order.getItens().stream().filter(o -> o.getType().equals(TipoItemEnum.BAR)).map(ItemDto::new).collect(Collectors.toList()));
            orderDto.setKitchenItens(order.getItens().stream().filter(o -> o.getType().equals(TipoItemEnum.KITCHEN)).map(ItemDto::new).collect(Collectors.toList()));
            return orderDto;
        }).collect(Collectors.toList());
    }
}
