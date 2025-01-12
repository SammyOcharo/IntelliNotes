package com.samdev.course_management_microservice.GlobalExceptionHandlers;

import com.samdev.course_management_microservice.Exceptions.InvalidCourseEntryException;
import com.samdev.course_management_microservice.Response.CourseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.samdev.course_management_microservice")
public class GlobalExceptionHandlers {

    @ExceptionHandler(InvalidCourseEntryException.class)
    public ResponseEntity<CourseResponse> InvalidCourseEntryException(InvalidCourseEntryException invalidCourseEntryException){
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setStatusCode(400);
        courseResponse.setStatusMessage(invalidCourseEntryException.getMessage());

        return new ResponseEntity<>(courseResponse, HttpStatus.BAD_REQUEST);
    }
}
