package com.samdev.course_management_microservice.Controller;

import com.samdev.course_management_microservice.Request.CourseRequest;
import com.samdev.course_management_microservice.Response.CourseResponse;
import com.samdev.course_management_microservice.Service.CourseManagementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<CourseResponse> registerUnit(
            @RequestParam("unitCode") String unitCode,
            @RequestParam("unitName") String unitName,
            @RequestParam("courseId") Long courseId,
            @RequestParam("courseOutline") MultipartFile courseOutline) {
        return ResponseEntity.ok(courseManagementService.registerUnit(unitCode, unitName, courseId, courseOutline));
    }

    //List All registered units
    @GetMapping("/get-all-units/")
    public ResponseEntity<List<CourseResponse>> listAllUnits(){
        return ResponseEntity.ok(courseManagementService.listAllUnits());
    }

    //List unit per id
    @GetMapping("/get-unit/{id}/")
    public ResponseEntity<CourseResponse> getUnit(@PathVariable("id") Long id){
        return ResponseEntity.ok(courseManagementService.getUnit(id));
    }

    //Delete a unit
    @DeleteMapping("/delete-unit/{id}/")
    public ResponseEntity<CourseResponse> deleteUnit(@PathVariable("id") Long id){
        return ResponseEntity.ok(courseManagementService.deleteUnit(id));
    }

    //Delete multiple units
    @DeleteMapping("/multiple-delete/")
    public ResponseEntity<CourseResponse> deleteMultipleUnits(@RequestBody @Valid Map<Integer, Integer> unitInfo){
        return ResponseEntity.ok(courseManagementService.deleteMultipleUnits(unitInfo));
    }
    //Delete course
    @DeleteMapping("/get-course/{id}/")
    public ResponseEntity<CourseResponse> deleteCourse(@PathVariable("id") Long id){
        return ResponseEntity.ok(courseManagementService.deleteCourse(id));
    }
}
