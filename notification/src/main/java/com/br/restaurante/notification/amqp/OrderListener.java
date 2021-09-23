package com.br.restaurante.notification.amqp;


import com.br.restaurante.notification.config.RabbitConfiguration;
import com.br.restaurante.notification.domain.Notification;
import com.br.restaurante.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class OrderListener {

    private NotificationService service;

    @RabbitListener(queues = RabbitConfiguration.NOTIFICATION_QUEUE)
    public void consumeRoster(@Payload OrderMessage message) {
        log.info("Mensagem recebida!!!!");
        Notification notification = new Notification(message.getId(), message.getTable(), message.getWaiter());
        service.save(notification);
    }

}