package com.samdev.notification_microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samdev.notification_microservice.entity.NotificationEntity;
import com.samdev.notification_microservice.repository.NotificationRepository;
import com.samdev.notification_microservice.request.PaymentJsonRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class NotificationService {

    Logger log = Logger.getLogger(NotificationService.class.getName());
    private final NotificationRepository notificationRepository;
    private final ObjectToEntityMapper objectMapper;

    public NotificationService(NotificationRepository notificationRepository, ObjectToEntityMapper objectMapper) {
        this.notificationRepository = notificationRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "payments", groupId = "payment-group")
    public void consumeNotification(PaymentJsonRequest paymentJsonRequest) {
        System.out.println("Received notification: " + paymentJsonRequest);

        //todo send an email to the student email.

        NotificationEntity notification = notificationRepository.save(objectMapper.toEntity(paymentJsonRequest));

        log.info("Notification saved: " + notification.getBody());

        log.info("Notification saved to database");



    }
}
