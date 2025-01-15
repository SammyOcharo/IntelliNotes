package com.samdev.content_generation_microservice.service;

import com.samdev.content_generation_microservice.request.ContentGenerationRequest;
import com.samdev.content_generation_microservice.response.ContentGenerationResponse;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

public interface ContentGenerationService {
    

    ContentGenerationResponse generatedContent(Integer unitId);

    String generatedContentStatus(Integer id);

    String processPdf(MultipartFile file);
}
