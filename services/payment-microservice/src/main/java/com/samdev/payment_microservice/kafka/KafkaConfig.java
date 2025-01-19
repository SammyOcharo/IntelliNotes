package com.samdev.payment_microservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public static final String PAYMENT_TOPIC = "payments";

    @Bean
    public NewTopic paymentTopic() {
        return TopicBuilder
                .name(PAYMENT_TOPIC)
                .build();
    }
}
