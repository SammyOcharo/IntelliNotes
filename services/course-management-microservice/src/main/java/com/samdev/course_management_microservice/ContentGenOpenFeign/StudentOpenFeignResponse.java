package com.samdev.course_management_microservice.ContentGenOpenFeign;

public record StudentOpenFeignResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
