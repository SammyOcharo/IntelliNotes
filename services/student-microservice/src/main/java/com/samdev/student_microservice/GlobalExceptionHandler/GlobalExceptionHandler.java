package com.samdev.student_microservice.GlobalExceptionHandler;

import com.samdev.student_microservice.Exceptions.InvalidOtpException;
import com.samdev.student_microservice.Exceptions.StudentDoesNotExistException;
import com.samdev.student_microservice.Exceptions.StudentExistsException;
import com.samdev.student_microservice.Response.StudentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.samdev.student_microservice")
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentExistsException.class)
    public ResponseEntity<StudentResponse> StudentAlreadyExistsException(StudentExistsException exception){

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStatusMessage(exception.getMessage());
        studentResponse.setStatusCode(400);

        return new ResponseEntity<>(studentResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudentDoesNotExistException.class)
    public ResponseEntity<StudentResponse> StudentDoesExistException(StudentDoesNotExistException studentDoesNotExistException){
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStatusMessage(studentDoesNotExistException.getMessage());
        studentResponse.setStatusCode(404);

        return new ResponseEntity<>(studentResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<StudentResponse> InvalidOtpException(InvalidOtpException invalidOtpException){
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStatusMessage(invalidOtpException.getMessage());
        studentResponse.setStatusCode(400);

        return new ResponseEntity<>(studentResponse, HttpStatus.NOT_FOUND);
    }
}
