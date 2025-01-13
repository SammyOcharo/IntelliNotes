package com.samdev.course_management_microservice.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.samdev.course_management_microservice.Entity.Course;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("unused")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnitRequest {
    private Long id;
    private String unitCode;
    private String unitName;
    private Course course;
    private Long courseId;
    private byte[] courseOutline;
    private String outline;


    public UnitRequest() {
    }

    public UnitRequest(Long id, String unitCode, String unitName, Course course, Long courseId, byte[] courseOutline, String outline) {
        this.id = id;
        this.unitCode = unitCode;
        this.unitName = unitName;
        this.course = course;
        this.courseId = courseId;
        this.courseOutline = courseOutline;
        this.outline = outline;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public byte[] getCourseOutline() {
        return courseOutline;
    }

    public void setCourseOutline(byte[] courseOutline) {
        this.courseOutline = courseOutline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
