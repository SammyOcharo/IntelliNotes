package com.samdev.course_management_microservice.Exceptions;

public class InvalidUnitIdException extends RuntimeException {
    public InvalidUnitIdException(String message) {
        super(message);
    }
}
