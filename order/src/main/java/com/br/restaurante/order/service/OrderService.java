package com.br.restaurante.order.service;

import com.br.restaurante.order.amqp.BarRequestMessage;
import com.br.restaurante.order.amqp.KitchenRequestMessage;
import com.br.restaurante.order.config.RabbitConfiguration;
import com.br.restaurante.order.domain.BarItem;
import com.br.restaurante.order.domain.KitchenItem;
import com.br.restaurante.order.domain.Order;
import com.br.restaurante.order.domain.TipoItemEnum;
import com.br.restaurante.order.dto.ItemDto;
import com.br.restaurante.order.dto.OrderDto;
import com.br.restaurante.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.br.restaurante.order.domain.StatusEnum.DONE;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository repository;
    private RabbitTemplate rabbitTemplate;
    private NotificationService notificationService;

    public Long addNewOrder(OrderDto orderDto) {

        Order order = new Order();
        BeanUtils.copyProperties(orderDto, order);
        order.setTableNo(orderDto.getTable());

        orderDto.getBarItens().stream().map(it -> {
            BarItem item = new BarItem();
            BeanUtils.copyProperties(it, item);
            return item;
        }).forEach(order::adicionarNovoBarItem);
        orderDto.getKitchenItens().stream().map(it -> {
            KitchenItem item = new KitchenItem();
            BeanUtils.copyProperties(it, item);
            return item;
        }).forEach(order::adicionarNovoKitchenItem);

        repository.save(order);
        sendOrderToBar(order);
        sendOrderToKitchen(order);

        return order.getId();
    }

    @Cacheable(cacheNames = "orders", key = "#root.method.name", unless = "#result.size < 1")
    public List<OrderDto> getOrders() {
        List<Order> all = repository.findAll();
        return all.stream().map(order -> {
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(order, orderDto);
            orderDto.setTable(order.getTableNo());
            orderDto.setBarItens(order.getBarItems().stream().filter(o -> o.getType().equals(TipoItemEnum.BAR)).map(ItemDto::new).collect(Collectors.toList()));
            orderDto.setKitchenItens(order.getKitchenItems().stream().filter(o -> o.getType().equals(TipoItemEnum.KITCHEN)).map(ItemDto::new).collect(Collectors.toList()));
            return orderDto;
        }).collect(Collectors.toList());
    }

    public void updateOrderStatus(Long orderNumber, TipoItemEnum type) {

        repository.findById(orderNumber).ifPresent(o -> {
            o.done(type);
            boolean statusBar = DONE.equals(o.getStatusBar());
            boolean kitchen = DONE.equals(o.getStatusKitchen());

            repository.save(o);

            if (statusBar && kitchen) this.notificationService.sendMessage(o);

        });

    }

    private void sendOrderToBar(Order order) {
        Long orderId = order.getId();
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME,
                RabbitConfiguration.ORDER_BAR_KEY,
                new BarRequestMessage(order.getBarItems(), orderId.toString()));
    }

    private void sendOrderToKitchen(Order order) {
        Long orderId = order.getId();
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME,
                RabbitConfiguration.ORDER_KITCHEN_KEY,
                new KitchenRequestMessage(order.getKitchenItems(), orderId.toString()));
    }
}