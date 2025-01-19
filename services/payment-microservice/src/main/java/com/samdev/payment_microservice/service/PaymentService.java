package com.samdev.payment_microservice.service;

import com.samdev.payment_microservice.request.PaymentRequest;
import com.samdev.payment_microservice.response.PaymentResponse;
import com.stripe.exception.StripeException;
import jakarta.validation.Valid;

import java.io.IOException;
import java.net.URISyntaxException;

public interface PaymentService {
    PaymentResponse initiatePayment(@Valid PaymentRequest paymentRequest) throws IOException, URISyntaxException, InterruptedException, StripeException;
}
