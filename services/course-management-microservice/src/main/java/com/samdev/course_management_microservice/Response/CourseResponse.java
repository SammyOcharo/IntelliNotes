package com.samdev.course_management_microservice.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.samdev.course_management_microservice.Request.CourseRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("unused")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseResponse {
    private CourseRequest courseRequest;
    private Integer statusCode;
    private String statusMessage;



    public CourseResponse() {
    }

    public CourseResponse(CourseRequest courseRequest, Integer statusCode, String statusMessage) {
        this.courseRequest = courseRequest;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public CourseRequest getCourseRequest() {
        return courseRequest;
    }

    public void setCourseRequest(CourseRequest courseRequest) {
        this.courseRequest = courseRequest;
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
