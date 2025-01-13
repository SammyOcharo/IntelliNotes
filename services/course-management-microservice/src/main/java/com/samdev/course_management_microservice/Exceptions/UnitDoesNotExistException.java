package com.samdev.course_management_microservice.Exceptions;

public class UnitDoesNotExistException extends RuntimeException {
    public UnitDoesNotExistException(String message) {
        super(message);
    }
}
