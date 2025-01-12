package com.samdev.student_microservice.service;

import com.samdev.student_microservice.Request.StudentRequest;
import com.samdev.student_microservice.Response.StudentResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface StudentService {
    StudentResponse RegisterStudent(@Valid StudentRequest courseRequest);

    StudentResponse StudentLogin(@Valid StudentRequest courseRequest);

    StudentResponse ForgetPassword(@Valid StudentRequest courseRequest);

    StudentResponse StudentRequestForgotPasswordOtp(@Valid StudentRequest courseRequest);

}
