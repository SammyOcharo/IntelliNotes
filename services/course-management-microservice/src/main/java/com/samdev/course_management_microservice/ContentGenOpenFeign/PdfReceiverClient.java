package com.samdev.course_management_microservice.ContentGenOpenFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(
        name = "pdf-receiver",
        url = "${application.config.content-generation}"
)
public interface PdfReceiverClient {

    @PostMapping(value = "api/content-generate/process-pdf/", consumes = "multipart/form-data")
    String uploadPdf(@RequestPart("file") MultipartFile file);
}
