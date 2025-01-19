package com.samdev.notification_microservice.repository;

import com.samdev.notification_microservice.entity.NotificationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<NotificationEntity, String> {
}
