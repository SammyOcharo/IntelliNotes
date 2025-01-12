package com.samdev.student_microservice.Mappers;

import com.samdev.student_microservice.Request.StudentRequest;
import com.samdev.student_microservice.Response.StudentResponse;
import com.samdev.student_microservice.Entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentRequestMapper {
    public Student toStudent(StudentRequest courseRequest) {
        Student student = new Student();
        student.setEmail(courseRequest.getEmail());
        student.setFirstName(courseRequest.getFirstName());
        student.setLastName(courseRequest.getLastName());
        return student;
    }
}
