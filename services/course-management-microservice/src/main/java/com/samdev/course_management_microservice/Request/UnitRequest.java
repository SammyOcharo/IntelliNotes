package com.samdev.course_management_microservice.Request;

import com.samdev.course_management_microservice.Entity.Course;

public class UnitRequest {
    private String unitCode;
    private String unitName;
    private Course course;
    private Long courseId;
    private byte[] courseOutline;
    private String outline;


    public UnitRequest() {
    }

    public UnitRequest(String unitCode, String unitName, Course course, byte[] courseOutline, Long courseId, String outline) {
        this.unitCode = unitCode;
        this.unitName = unitName;
        this.course = course;
        this.courseOutline = courseOutline;
        this.courseId = courseId;
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
}
