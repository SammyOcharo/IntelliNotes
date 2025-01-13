package com.samdev.content_generation_microservice.controller;

import com.samdev.content_generation_microservice.request.ContentGenerationRequest;
import com.samdev.content_generation_microservice.response.ContentGenerationResponse;
import com.samdev.content_generation_microservice.service.ContentGenerationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/content-generate/")
public class ContentGeneration {

    public final ContentGenerationService contentGenerationService;

    public ContentGeneration(ContentGenerationService contentGenerationService) {
        this.contentGenerationService = contentGenerationService;
    }

    //Generate Content API
    @PostMapping("request-course-work/")
    public ResponseEntity<ContentGenerationResponse> generateContent(@RequestBody @Valid ContentGenerationRequest contentGenerationRequest){

        return ResponseEntity.ok(contentGenerationService.generateContent(contentGenerationRequest));

    }

    //generated-content pass {generationId}

    // generated-content status {generationId}/status

}
