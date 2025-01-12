package com.samdev.student_microservice.controller;


import com.samdev.student_microservice.Request.StudentRequest;
import com.samdev.student_microservice.Response.StudentResponse;
import com.samdev.student_microservice.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    //register student
    @PostMapping("/register/")
    public ResponseEntity<StudentResponse> RegisterStudent(@RequestBody @Valid StudentRequest studentRequest){
        return ResponseEntity.ok(studentService.RegisterStudent(studentRequest));
    }

    //login student
    @PostMapping("/login/")
    public ResponseEntity<StudentResponse> StudentLogin(@RequestBody @Valid StudentRequest studentRequest){
        return ResponseEntity.ok(studentService.StudentLogin(studentRequest));
    }

    //forgot password
    @PostMapping("/forget-password/")
    public ResponseEntity<StudentResponse> ForgetPassword(@RequestBody @Valid StudentRequest studentRequest){
        return ResponseEntity.ok(studentService.ForgetPassword(studentRequest));
    }

    //send password reset otp
    @PostMapping("/confirm-otp/")
    public ResponseEntity<StudentResponse> StudentRequestForgotPasswordOtp(@RequestBody @Valid StudentRequest studentRequest){
        return ResponseEntity.ok(studentService.StudentRequestForgotPasswordOtp(studentRequest));
    }

}