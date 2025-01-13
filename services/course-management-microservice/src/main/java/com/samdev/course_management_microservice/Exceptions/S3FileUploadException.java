package com.samdev.course_management_microservice.Exceptions;

public class S3FileUploadException extends RuntimeException {
    public S3FileUploadException(String message) {
        super(message);
    }
}
