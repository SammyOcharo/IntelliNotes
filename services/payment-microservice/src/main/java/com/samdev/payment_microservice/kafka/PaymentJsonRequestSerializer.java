package com.samdev.payment_microservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class PaymentJsonRequestSerializer implements Serializer<PaymentJsonRequest> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public byte[] serialize(String topic, PaymentJsonRequest paymentJsonRequest) {
        try{
            return objectMapper.writeValueAsBytes(paymentJsonRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing payment request", e);
        }
    }
}
