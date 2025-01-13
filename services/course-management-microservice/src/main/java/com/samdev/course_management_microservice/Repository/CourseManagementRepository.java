package com.samdev.course_management_microservice.Repository;

import com.samdev.course_management_microservice.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseManagementRepository extends JpaRepository<Course, Long> {
    boolean existsByCourseName(String courseName);
}
