package com.samdev.content_generation_microservice.serviceImpl;

import com.samdev.content_generation_microservice.entity.GeneratedContent;
import com.samdev.content_generation_microservice.response.ContentGenerationResponse;
import org.springframework.stereotype.Service;

@Service
public class ContentGenerationMapper {
    ContentGenerationResponse contentGenerationResponse = new ContentGenerationResponse();
    public ContentGenerationResponse toContentResponse(GeneratedContent generatedContent) {

        contentGenerationResponse.setGeneratedNotes(generatedContent.getGeneratedNotes());
        contentGenerationResponse.setQuestions(generatedContent.getRevisionQuestions());
        contentGenerationResponse.setSummary(generatedContent.getSummary());
        contentGenerationResponse.setGeneratedDate(generatedContent.getGeneratedDate());

        return contentGenerationResponse;
    }
}
