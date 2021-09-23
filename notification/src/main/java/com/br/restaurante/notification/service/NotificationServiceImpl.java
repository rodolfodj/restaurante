package com.br.restaurante.notification.service;

import com.br.restaurante.notification.domain.Email;
import com.br.restaurante.notification.domain.Notification;
import com.br.restaurante.notification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository repository;
    private JavaMailSender javaMailSender;

    @Override
    public void save(Notification note) {
        log.info("Salvando e enviando notificação");
        this.sendMail(new Email(note));
        repository.save(note);
    }

    @Override
    public void sendMail(Email email) {
        String content = email.getContent();
        String destination = email.getTo();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destination);
        message.setText(content);
        message.setSubject(Email.SUBJECT);
        message.setFrom("restaurant@mesttra.com");
        log.info("Enviando email: {}", message);
        javaMailSender.send(message);
    }
}