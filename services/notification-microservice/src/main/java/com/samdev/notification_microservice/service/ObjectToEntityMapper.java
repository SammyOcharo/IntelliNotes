package com.samdev.notification_microservice.service;

import com.samdev.notification_microservice.entity.NotificationEntity;
import com.samdev.notification_microservice.request.PaymentJsonRequest;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ObjectToEntityMapper {

    public NotificationEntity toEntity(PaymentJsonRequest paymentJsonRequest) {
        Logger log = Logger.getLogger(ObjectToEntityMapper.class.getName());

        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setTitle("Transaction");
        notificationEntity.setBody("Transaction of amount " + paymentJsonRequest.transactionAmount() + " through " + paymentJsonRequest.paymentOption() + "by student with ID "+ paymentJsonRequest.studentId());

        // Log the entity fields
        log.info("Mapped entity: " + notificationEntity.getTitle() + " | " + notificationEntity.getBody());

        return notificationEntity;
    }
}
