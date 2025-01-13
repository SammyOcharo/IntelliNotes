package com.samdev.content_generation_microservice.service;

import com.samdev.content_generation_microservice.request.ContentGenerationRequest;
import com.samdev.content_generation_microservice.response.ContentGenerationResponse;
import jakarta.validation.Valid;

public interface ContentGenerationService {
    ContentGenerationResponse generateContent(@Valid ContentGenerationRequest contentGenerationRequest);
}
