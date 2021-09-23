package com.br.restaurante.notification.service;

import com.br.restaurante.notification.domain.Email;
import com.br.restaurante.notification.domain.Notification;

public interface NotificationService {

    void save(Notification note);

    void sendMail(Email email);

}