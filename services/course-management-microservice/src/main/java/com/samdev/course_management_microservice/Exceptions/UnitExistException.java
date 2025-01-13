package com.samdev.course_management_microservice.Exceptions;

public class UnitExistException extends RuntimeException {
    public UnitExistException(String message) {
        super(message);
    }
}
