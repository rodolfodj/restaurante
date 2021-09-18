package com.br.restaurante.bar.service;

import com.br.restaurante.bar.amqp.BarMessage;
import com.br.restaurante.bar.amqp.OrderMessage;
import com.br.restaurante.bar.config.RabbitConfiguration;
import com.br.restaurante.bar.domain.BarOrder;
import com.br.restaurante.bar.domain.Status;
import com.br.restaurante.bar.exception.PedidoNaoEncontradoException;
import com.br.restaurante.bar.repository.BarRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BarServiceImpl implements BarService {

    private BarRepository repository;
    private RabbitTemplate rabbitTemplate;

    @Override
    public void receiveOrder(OrderMessage message) {
        BarOrder order = new BarOrder();
        order.setOrder(message.getOrderId());
        order.setStatus(Status.RECEIVED);
        message.getBarItems().forEach(order::addItem);

        repository.save(order);
    }


    @Override
    public void orderDone(String orderId) {
        BarOrder barOrder = repository.findById(orderId).orElseThrow(PedidoNaoEncontradoException::new);
        barOrder.setStatus(Status.DONE);
        repository.save(barOrder);
        sendBarOrderToOrder(orderId);
    }

    private void sendBarOrderToOrder(String orderId) {
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME,
                RabbitConfiguration.BAR_ORDER_KEY,
                new BarMessage(orderId));
    }
}