package com.samdev.payment_microservice.stripe;

import com.samdev.payment_microservice.entity.Subscription;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InitiatePayment {

    @Value("${stripe.stripeApiKey}")
    private String stripeApiKey;

    @Value("${system.clientBaseURL}")
    private String clientBaseURL;

    public String initiateToStripe(Customer customer, Subscription subscription) throws StripeException {
        SessionCreateParams.Builder paramsBuilder = SessionCreateParams
                .builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCustomer(customer.getId())
                .setSuccessUrl(clientBaseURL + "/success?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl(clientBaseURL + "/failure");

        paramsBuilder.addLineItem(
                SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setProductData(
                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                        .putMetadata("app_id", String.valueOf(subscription.getId()))
                                                        .setName(subscription.getSubscriptionName())
                                                        .build()
                                        )
                                        .setCurrency("USD")
                                        .setUnitAmountDecimal(subscription.getSubscriptionAmount())
                                        .build()
                        )
                        .build()
        );

        Session session = Session.create(paramsBuilder.build());

        return session.getUrl();
    }



}
