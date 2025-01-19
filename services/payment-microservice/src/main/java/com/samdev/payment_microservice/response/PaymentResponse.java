package com.samdev.payment_microservice.response;

public record PaymentResponse(
        String responseMessage,
        String responseStatusCode,
        String paymentUrl
) {
    // Factory method for 2 fields
    public static PaymentResponse of(String responseMessage, String responseStatusCode) {
        return new PaymentResponse(responseMessage, responseStatusCode, null);
    }

    // Factory method for 3 fields
    public static PaymentResponse of(String responseMessage, String responseStatusCode, String paymentUrl) {
        return new PaymentResponse(responseMessage, responseStatusCode, paymentUrl);
    }

    // Helper method to check if the payment URL is present
    public boolean hasPaymentUrl() {
        return paymentUrl != null && !paymentUrl.isBlank();
    }

}
