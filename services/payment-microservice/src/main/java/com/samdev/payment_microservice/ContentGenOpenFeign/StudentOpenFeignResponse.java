package com.samdev.payment_microservice.ContentGenOpenFeign;

public record StudentOpenFeignResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
}
