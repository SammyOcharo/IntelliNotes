package com.samdev.course_management_microservice.Service;

import com.samdev.course_management_microservice.Request.CourseRequest;
import com.samdev.course_management_microservice.Response.CourseResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface CourseManagementService {
    CourseResponse registerCourse(@Valid CourseRequest courseRequest);

    List<CourseResponse> AllCourse();
}
