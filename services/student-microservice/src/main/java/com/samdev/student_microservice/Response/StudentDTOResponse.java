package com.samdev.student_microservice.Response;

public record StudentDTOResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
