package com.samdev.payment_microservice.ContentGenOpenFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "student-url",
        url = "${application.config.studentUrl}"
)
public interface ConfirmStudentInfo {
    @GetMapping("/api/v1/accounts/{id}/")
    StudentOpenFeignResponse findStudentById(@PathVariable("id") String id);
}
