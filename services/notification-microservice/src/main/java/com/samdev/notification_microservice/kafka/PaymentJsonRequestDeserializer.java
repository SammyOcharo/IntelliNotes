package com.samdev.notification_microservice.kafka;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.samdev.notification_microservice.request.PaymentJsonRequest;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class PaymentJsonRequestDeserializer implements Deserializer<PaymentJsonRequest> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public PaymentJsonRequest deserialize(String s, byte[] data) {
        try {
            return objectMapper.readValue(data, PaymentJsonRequest.class);
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing payment json request, {}", e);
        }
    }
}
