package com.samdev.course_management_microservice.GlobalExceptionHandlers;

import com.samdev.course_management_microservice.Exceptions.*;
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

    @ExceptionHandler(CourseDoesNotExistException.class)
    public ResponseEntity<CourseResponse> CourseSelectedDoesNotExistException(CourseDoesNotExistException courseDoesNotExistException){
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setStatusCode(404);
        courseResponse.setStatusMessage(courseDoesNotExistException.getMessage());

        return new ResponseEntity<>(courseResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InvalidUnitIdException.class)
    public ResponseEntity<CourseResponse> InvalidUnitIdException(InvalidUnitIdException invalidUnitIdException){
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setStatusCode(404);
        courseResponse.setStatusMessage(invalidUnitIdException.getMessage());

        return new ResponseEntity<>(courseResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MapInformationException.class)
    public ResponseEntity<CourseResponse> MapInformationException(MapInformationException mapInformationException){
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setStatusCode(400);
        courseResponse.setStatusMessage(mapInformationException.getMessage());

        return new ResponseEntity<>(courseResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnitDeletionException.class)
    public ResponseEntity<CourseResponse> UnitDeletionException(UnitDeletionException unitDeletionException){
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setStatusCode(400);
        courseResponse.setStatusMessage(unitDeletionException.getMessage());

        return new ResponseEntity<>(courseResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CourseAlreadyExistsException.class)
    public ResponseEntity<CourseResponse> CourseAlreadyExistsException(CourseAlreadyExistsException courseAlreadyExistsException){
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setStatusCode(400);
        courseResponse.setStatusMessage(courseAlreadyExistsException.getMessage());

        return new ResponseEntity<>(courseResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnitExistException.class)
    public ResponseEntity<CourseResponse> UnitExistException(UnitExistException unitExistException){
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setStatusCode(400);
        courseResponse.setStatusMessage(unitExistException.getMessage());

        return new ResponseEntity<>(courseResponse, HttpStatus.BAD_REQUEST);
    }

}
