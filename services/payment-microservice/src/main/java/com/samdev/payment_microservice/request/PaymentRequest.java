package com.samdev.payment_microservice.request;

public record PaymentRequest(
        String paymentOption,
        Integer transactionAmount,
        String phoneNumber,
        String studentId
) {
}
