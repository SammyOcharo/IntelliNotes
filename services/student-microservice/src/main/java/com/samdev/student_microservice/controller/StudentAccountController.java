package com.samdev.student_microservice.controller;

import com.samdev.student_microservice.Response.StudentDTOResponse;
import com.samdev.student_microservice.Response.StudentResponse;
import com.samdev.student_microservice.service.StudentAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts/")
public class StudentAccountController {

    private final StudentAccountService studentAccountService;

    public StudentAccountController(StudentAccountService studentAccountService) {
        this.studentAccountService = studentAccountService;
    }

    //Get student information
    @GetMapping("{id}/")
    public ResponseEntity<StudentDTOResponse> StudentInformation(@PathVariable("id") String id){
        return ResponseEntity.ok(studentAccountService.StudentInformation(id));
    }
}
