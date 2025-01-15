package com.samdev.content_generation_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
public class ContentGenerationMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentGenerationMicroserviceApplication.class, args);
	}

}
