package com.samdev.payment_microservice.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerSearchResult;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerSearchParams;
import org.springframework.stereotype.Service;

@Service
public class CustomerUtil {

    public Customer findOrCreateCustomer(String email, String fullName) throws StripeException {
        CustomerSearchParams params =
                CustomerSearchParams
                        .builder()
                        .setQuery("email:'" + email+ "'")
                        .build();

        CustomerSearchResult result = Customer.search(params);
        Customer customer;

        if(result.getData().isEmpty()){
            CustomerCreateParams customerCreateParams = CustomerCreateParams.builder()
                    .setName(fullName)
                    .setEmail(email)
                    .build();

            customer = Customer.create(customerCreateParams);
        }else {
            customer = result.getData().getFirst();
        }
        return customer;
    }
}
