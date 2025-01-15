package com.samdev.content_generation_microservice.request;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public record ContentGenerationRequest(
        @RequestParam("courseOutline") MultipartFile courseOutline
) {
}
