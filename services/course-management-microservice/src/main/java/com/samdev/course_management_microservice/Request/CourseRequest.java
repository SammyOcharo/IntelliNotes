package com.samdev.course_management_microservice.Request;

public class CourseRequest {
    private String courseName;
    private String unitCode;
    private String unitName;
    private Integer courseId;
    private byte[] courseOutline;


    public CourseRequest() {
    }

    public CourseRequest(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public byte[] getCourseOutline() {
        return courseOutline;
    }

    public void setCourseOutline(byte[] courseOutline) {
        this.courseOutline = courseOutline;
    }
}
