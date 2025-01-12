package com.samdev.course_management_microservice.Exceptions;

public class InvalidCourseEntryException extends RuntimeException {
    public InvalidCourseEntryException(String message) {
        super(message);
    }
}
