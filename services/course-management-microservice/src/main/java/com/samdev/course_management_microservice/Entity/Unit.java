package com.samdev.course_management_microservice.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="Unit")
public class Unit {
    @Id
    @GeneratedValue
    private Long unitId;
    @Column(nullable = false)
    private String unitCode;
    @Column(nullable=false)
    private String unitName;
    @ManyToOne
    @JoinColumn(name="course_id", nullable=false)
    private Course course;
    private String unitOutlineUrl;

    public Unit() {
    }

    public Unit(Long unitId, String unitCode, String unitName, Course course, String unitOutlineUrl) {
        this.unitId = unitId;
        this.unitCode = unitCode;
        this.unitName = unitName;
        this.course = course;
        this.unitOutlineUrl = unitOutlineUrl;
    }

    public Long getUnitId() {
        return unitId;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public Course getCourse() {
        return course;
    }

    public String getUnitOutlineUrl() {
        return unitOutlineUrl;
    }

    public void setUnitOutlineUrl(String unitOutlineUrl) {
        this.unitOutlineUrl = unitOutlineUrl;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
