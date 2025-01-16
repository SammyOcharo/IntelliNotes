package com.samdev.payment_microservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Subscription {

    @Id
    @GeneratedValue
    private Long id;
    private String subscriptionName;
    private BigDecimal subscriptionAmount;
    private String subscriptionPeriod;
    private LocalDate subscriptionStartDate;
    private LocalDate subscriptionEndDate;

    public Subscription() {
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public BigDecimal getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public String getSubscriptionPeriod() {
        return subscriptionPeriod;
    }

    public LocalDate getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public LocalDate getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public Long getId() {
        return id;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public void setSubscriptionAmount(BigDecimal subscriptionAmount) {
        this.subscriptionAmount = subscriptionAmount;
    }

    public void setSubscriptionPeriod(String subscriptionPeriod) {
        this.subscriptionPeriod = subscriptionPeriod;
    }

    public void setSubscriptionStartDate(LocalDate subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public void setSubscriptionEndDate(LocalDate subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }
}
