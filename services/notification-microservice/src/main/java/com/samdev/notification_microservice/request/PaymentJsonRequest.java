package com.samdev.notification_microservice.request;

public record PaymentJsonRequest(
        String paymentOption,
        Integer transactionAmount,
        String phoneNumber,
        String studentId,
        String email
) {
}
