package com.samdev.payment_microservice.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
    private String transactionReference;
    private Double transactionAmount;
    @CreatedDate
    private LocalDateTime transactionTime;
    @Enumerated(EnumType.STRING)
    private PaymentOption paymentOption;
    private String phoneNumber;
    private String merchantRequestID;
    private String checkoutRequestID;
    private boolean isSuccessful=false;

    public Payment() {
    }

    public Payment(
            Long id,
            String transactionReference,
            Double transactionAmount,
            LocalDateTime transactionTime,
            PaymentOption paymentOption,
            String phoneNumber,
            String merchantRequestID,
            String checkoutRequestID, Boolean isSuccessful) {
        this.id = id;
        this.transactionReference = transactionReference;
        this.transactionAmount = transactionAmount;
        this.transactionTime = transactionTime;
        this.paymentOption = paymentOption;
        this.phoneNumber = phoneNumber;
        this.merchantRequestID = merchantRequestID;
        this.checkoutRequestID = checkoutRequestID;
        this.isSuccessful = isSuccessful;
    }

    public Long getId() {
        return id;
    }

    public PaymentOption getPaymentOption() {
        return paymentOption;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMerchantRequestID() {
        return merchantRequestID;
    }

    public String getCheckoutRequestID() {
        return checkoutRequestID;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public void setMerchantRequestID(String merchantRequestID) {
        this.merchantRequestID = merchantRequestID;
    }

    public void setCheckoutRequestID(String checkoutRequestID) {
        this.checkoutRequestID = checkoutRequestID;
    }

    public void setPaymentOption(PaymentOption paymentOption) {
        this.paymentOption = paymentOption;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }
}
