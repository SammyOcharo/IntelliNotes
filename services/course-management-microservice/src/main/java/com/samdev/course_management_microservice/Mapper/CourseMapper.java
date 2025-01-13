package com.samdev.course_management_microservice.Mapper;

import com.samdev.course_management_microservice.AWS.AwsUtil;
import com.samdev.course_management_microservice.Entity.Course;
import com.samdev.course_management_microservice.Entity.Unit;
import com.samdev.course_management_microservice.Request.CourseRequest;
import com.samdev.course_management_microservice.Request.UnitRequest;
import com.samdev.course_management_microservice.Response.CourseResponse;
import org.springframework.stereotype.Service;

@Service
public class CourseMapper {
    private final AwsUtil awsUtil;

    public CourseMapper(AwsUtil awsUtil) {
        this.awsUtil = awsUtil;
    }

    public Course toCourse(CourseRequest courseRequest) {

        Course course = new Course();

        course.setCourseName(courseRequest.getCourseName());

        return course;
    }

    public CourseResponse toCourseMapper(Course course) {
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourseId(course.getCourseId());
        courseRequest.setCourseName(course.getCourseName());

        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setCourseRequest(courseRequest);

        return courseResponse;
    }

    public CourseResponse toUnitMapper(Unit unit) {
        UnitRequest unitRequest = new UnitRequest();
        unitRequest.setId(unit.getUnitId());
        unitRequest.setUnitCode(unit.getUnitCode());
        unitRequest.setOutline(awsUtil.generatePresignedUrl(unit.getUnitOutlineUrl(),30).toString());
        unitRequest.setUnitName(unit.getUnitName());

        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setUnit(unitRequest);

        return courseResponse;
    }

    public Unit ToUnit(String unitCode, String unitName, Long courseId, String filePath, Course course) {
        Unit unit = new Unit();
        unit.setUnitCode(unitCode);
        unit.setUnitName(unitName);
        unit.setUnitOutlineUrl(filePath);
        unit.setCourse(course);

        return unit;
    }
}
