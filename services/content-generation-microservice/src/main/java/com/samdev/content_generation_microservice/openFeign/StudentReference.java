package com.samdev.content_generation_microservice.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name="student-application",
        url="${application.config.url}"
)
public interface StudentReference {
    @GetMapping("/api/student/{id}/")
    StudentResponse findByStudentId(@PathVariable("id") Integer id);
}
