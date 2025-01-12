package com.samdev.course_management_microservice.ServiceImpl;

import com.samdev.course_management_microservice.Entity.Course;
import com.samdev.course_management_microservice.Exceptions.InvalidCourseEntryException;
import com.samdev.course_management_microservice.Mapper.CourseMapper;
import com.samdev.course_management_microservice.Repository.CourseManagementRepository;
import com.samdev.course_management_microservice.Request.CourseRequest;
import com.samdev.course_management_microservice.Response.CourseResponse;
import com.samdev.course_management_microservice.Service.CourseManagementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseManagementServiceImpl implements CourseManagementService {

    private final CourseManagementRepository courseManagementRepository;
    private final CourseMapper mapper;

    public CourseManagementServiceImpl(CourseManagementRepository courseManagementRepository, CourseMapper mapper) {
        this.courseManagementRepository = courseManagementRepository;
        this.mapper = mapper;
    }

    CourseResponse courseResponse = new CourseResponse();

    @Override
    public CourseResponse registerCourse(CourseRequest courseRequest) {
        if(courseRequest.getCourseName() == null)
            throw new InvalidCourseEntryException("Invalid course entered");

        Course course = courseManagementRepository.save(mapper.toCourse(courseRequest));
        courseResponse.setStatusCode(200);
        courseResponse.setStatusMessage("Course registered successfully!");
        courseResponse.setCourseRequest(courseRequest);


        return courseResponse;
    }

    @Override
    public List<CourseResponse> AllCourse() {
        return courseManagementRepository.findAll()
                .stream()
                .map(mapper::toCourseMapper)
                .toList();
    }
}
