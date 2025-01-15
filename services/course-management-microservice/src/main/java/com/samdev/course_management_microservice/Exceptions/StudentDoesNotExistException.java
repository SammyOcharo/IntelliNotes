package com.samdev.course_management_microservice.Exceptions;

public class StudentDoesNotExistException extends RuntimeException {
    public StudentDoesNotExistException(String message) {
        super(message);
    }
}
