package com.samdev.course_management_microservice.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.samdev.course_management_microservice.Request.CourseRequest;
import com.samdev.course_management_microservice.Request.UnitRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("unused")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseResponse {
    private CourseRequest course;
    private Integer statusCode;
    private String statusMessage;
    private UnitRequest unit;



    public CourseResponse() {
    }

    public CourseResponse(CourseRequest courseRequest, Integer statusCode, String statusMessage, UnitRequest unit) {
        this.course = courseRequest;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.unit = unit;
    }

    public UnitRequest getUnit() {
        return unit;
    }

    public void setUnit(UnitRequest unit) {
        this.unit = unit;
    }

    public CourseRequest getCourseRequest() {
        return course;
    }

    public void setCourseRequest(CourseRequest courseRequest) {
        this.course = courseRequest;
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
