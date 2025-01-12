package com.samdev.course_management_microservice.Mapper;

import com.samdev.course_management_microservice.Entity.Course;
import com.samdev.course_management_microservice.Request.CourseRequest;
import com.samdev.course_management_microservice.Response.CourseResponse;
import org.springframework.stereotype.Service;

@Service
public class CourseMapper {
    public Course toCourse(CourseRequest courseRequest) {

        Course course = new Course();

        course.setCourseName(course.getCourseName());

        return course;
    }

    public CourseResponse toCourseMapper(Course course) {
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourseName(course.getCourseName());

        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setCourseRequest(courseRequest);

        return courseResponse;
    }
}
