package com.br.restaurante.notification.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;
    private Long orderId;
    private Integer tableNo;
    private String waiter;
    private LocalDateTime sent;
    private String from;
    private String destination;
    private String content;

    public Notification(Long orderId, Integer tableNo, String waiter) {
        this.orderId = orderId;
        this.tableNo = tableNo;
        this.waiter = waiter;
    }
}