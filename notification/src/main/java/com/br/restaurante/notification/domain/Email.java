package com.br.restaurante.notification.domain;

import lombok.Data;

@Data
public class Email {

    public static final String SUBJECT = "Good news! Your order is done";
    private static final String TEMPLATE_MESSAGE = "Your order %d is done.\nThe dishes are waiting for you on table %d.\nIf you've any questions, call the waiter %s";

    private final Long orderId;
    private final String waiter;
    private final Integer tableNo;
    private String to;
    private String content;

    public Email(Notification note) {
        orderId = note.getOrderId();
        waiter = note.getWaiter();
        tableNo = note.getTableNo();
        content = String.format(TEMPLATE_MESSAGE, orderId, tableNo, waiter);
        String[] splitted = waiter.split(" ");
        to = splitted[0] + "@" + splitted[1] + ".com";
    }
}