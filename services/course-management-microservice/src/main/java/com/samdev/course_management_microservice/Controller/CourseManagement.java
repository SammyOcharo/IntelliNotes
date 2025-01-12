package com.samdev.course_management_microservice.Controller;

import com.samdev.course_management_microservice.Request.CourseRequest;
import com.samdev.course_management_microservice.Response.CourseResponse;
import com.samdev.course_management_microservice.Service.CourseManagementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-management")
public class CourseManagement {

    private final CourseManagementService courseManagementService;

    public CourseManagement(CourseManagementService courseManagementService) {
        this.courseManagementService = courseManagementService;
    }


    //Register Course
    @PostMapping("/register-course/")
    public ResponseEntity<CourseResponse> registerCourse(@RequestBody @Valid CourseRequest courseRequest){
        return ResponseEntity.ok(courseManagementService.registerCourse(courseRequest));
    }


    //List all registered courses
    @GetMapping("/get-all-courses/")
    public ResponseEntity<List<CourseResponse>> AllCourse(){
        return ResponseEntity.ok(courseManagementService.AllCourse());
    }

    //Register unit
    @PostMapping("/register-unit/")
    public ResponseEntity<CourseResponse> registerUnit(@RequestBody @Valid CourseRequest courseRequest){
        return ResponseEntity.ok(courseManagementService.registerCourse(courseRequest));
    }

    //List All registered units

    //List unit per Id

    //Delete a unit

    //Delete multiple units

    //Delete course
}
