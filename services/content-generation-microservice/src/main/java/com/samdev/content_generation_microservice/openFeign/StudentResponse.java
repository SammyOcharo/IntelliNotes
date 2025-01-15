package com.samdev.content_generation_microservice.openFeign;

public record StudentResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
