package com.samdev.payment_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
public class PaymentMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentMicroserviceApplication.class, args);
	}

}
