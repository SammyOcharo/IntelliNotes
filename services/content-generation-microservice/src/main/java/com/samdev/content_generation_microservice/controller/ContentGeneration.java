package com.samdev.content_generation_microservice.controller;

import com.samdev.content_generation_microservice.request.ContentGenerationRequest;
import com.samdev.content_generation_microservice.response.ContentGenerationResponse;
import com.samdev.content_generation_microservice.service.ContentGenerationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/content-generate/")
public class ContentGeneration {

    public final ContentGenerationService contentGenerationService;

    public ContentGeneration(ContentGenerationService contentGenerationService) {
        this.contentGenerationService = contentGenerationService;
    }

    //Generate Content API
    @PostMapping("process-pdf/")
    public ResponseEntity<String> generateContent(@RequestPart("file") MultipartFile file) {
        // Process the file
        return ResponseEntity.ok(contentGenerationService.processPdf(file));

    }

    //generated-content pass {generationId}
    @GetMapping("generated-content/{unit-id}")
    public ResponseEntity<ContentGenerationResponse> generatedContent(@PathVariable("unit-id") Integer unitId){

        return ResponseEntity.ok(contentGenerationService.generatedContent(unitId));

    }

    // generated-content status {generationId}/status
    @GetMapping("generated-content-status/{id}")
    public ResponseEntity<String> generatedContentStatus(@PathVariable("id") Integer id){

        return ResponseEntity.ok(contentGenerationService.generatedContentStatus(id));

    }
}
