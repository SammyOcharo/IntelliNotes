package com.samdev.course_management_microservice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;

import java.util.List;

@Entity
@Table(name="course_tbl")
public class Course {
    @Id
    @GeneratedValue
    private Long courseId;
    @NotNull(message="Course name is required")
    private String courseName;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private String createdAt;
    @OneToMany(mappedBy="course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Unit> units;

    public Course() {
    }

    public Course(String courseName, Long courseId, String createdAt, List<Unit> units) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.createdAt = createdAt;
        this.units = units;
    }

    public String getCourseName() {
        return courseName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }
}
