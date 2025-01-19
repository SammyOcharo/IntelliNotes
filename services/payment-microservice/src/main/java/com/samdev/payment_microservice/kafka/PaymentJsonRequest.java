package com.samdev.payment_microservice.kafka;

public record PaymentJsonRequest(
        String paymentOption,
        Integer transactionAmount,
        String phoneNumber,
        String studentId, String email) {
}
