package com.samdev.student_microservice.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.samdev.student_microservice.Request.StudentRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("unused")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponse {
    private StudentRequest studentRequest;
    private List<StudentResponse> allStudents;
    private Integer statusCode;
    private String statusMessage;

    public StudentResponse() {
    }

    public List<StudentResponse> getAllStudents() {
        return allStudents;
    }

    public void setAllStudents(List<StudentResponse> allStudents) {
        this.allStudents = allStudents;
    }

    public StudentResponse(StudentRequest studentRequest, Integer statusCode, String statusMessage) {
        this.studentRequest = studentRequest;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public StudentRequest getStudentRequest() {
        return studentRequest;
    }

    public void setStudentRequest(StudentRequest studentRequest) {
        this.studentRequest = studentRequest;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
