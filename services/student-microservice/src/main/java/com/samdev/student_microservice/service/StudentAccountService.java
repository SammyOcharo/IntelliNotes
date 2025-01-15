package com.samdev.student_microservice.service;

import com.samdev.student_microservice.Response.StudentDTOResponse;

public interface StudentAccountService {

    StudentDTOResponse StudentInformation(String id);
}
