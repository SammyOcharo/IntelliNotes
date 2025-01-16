package com.samdev.payment_microservice.controller;

import com.samdev.payment_microservice.request.PaymentRequest;
import com.samdev.payment_microservice.response.PaymentResponse;
import com.samdev.payment_microservice.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/payment")
public class Payment {

    private final PaymentService paymentService;

    public Payment(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initiate/")
    public ResponseEntity<PaymentResponse> initiatePayment(@RequestBody @Valid PaymentRequest paymentRequest) throws IOException, URISyntaxException, InterruptedException {
        return ResponseEntity.ok(paymentService.initiatePayment(paymentRequest));
    }
}
