package com.samdev.course_management_microservice.Exceptions;

public class CourseDoesNotExistException extends RuntimeException {
    public CourseDoesNotExistException(String message) {
        super(message);
    }
}
