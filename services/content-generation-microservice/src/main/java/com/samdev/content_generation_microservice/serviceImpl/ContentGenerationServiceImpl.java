package com.samdev.content_generation_microservice.serviceImpl;

import com.samdev.content_generation_microservice.repository.ContentGenerationRepository;
import com.samdev.content_generation_microservice.request.ContentGenerationRequest;
import com.samdev.content_generation_microservice.response.ContentGenerationResponse;
import com.samdev.content_generation_microservice.service.ContentGenerationService;
import org.springframework.stereotype.Service;

@Service
public class ContentGenerationServiceImpl implements ContentGenerationService {

    private final ContentGenerationRepository contentGenerationRepository;

    public ContentGenerationServiceImpl(ContentGenerationRepository contentGenerationRepository) {
        this.contentGenerationRepository = contentGenerationRepository;
    }

    @Override
    public ContentGenerationResponse generateContent(ContentGenerationRequest contentGenerationRequest) {
        return null;
    }
}
