package com.samdev.course_management_microservice.Service;

import com.samdev.course_management_microservice.Request.CourseRequest;
import com.samdev.course_management_microservice.Request.UnitRequest;
import com.samdev.course_management_microservice.Response.CourseResponse;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface CourseManagementService {
    CourseResponse registerCourse(@Valid CourseRequest courseRequest);

    List<CourseResponse> AllCourse();

    List<CourseResponse> listAllUnits();

    CourseResponse registerUnit(String unitCode, String unitName, Long courseId, MultipartFile courseOutline);

    CourseResponse getUnit(Long id);

    CourseResponse deleteUnit(Long id);

    CourseResponse deleteMultipleUnits(Map<Integer, Integer> unitInfo);

    CourseResponse deleteCourse(Long id);
}
