package com.samdev.content_generation_microservice.serviceImpl;

import com.samdev.content_generation_microservice.repository.ContentGenerationRepository;
import com.samdev.content_generation_microservice.request.ContentGenerationRequest;
import com.samdev.content_generation_microservice.response.ContentGenerationResponse;
import com.samdev.content_generation_microservice.service.ContentGenerationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ContentGenerationServiceImpl implements ContentGenerationService {

    private static final Logger log = LoggerFactory.getLogger(ContentGenerationServiceImpl.class);
    private final ContentGenerationRepository contentGenerationRepository;
    private final ContentGenerationMapper contentGenerationMapper;

    public ContentGenerationServiceImpl(ContentGenerationRepository contentGenerationRepository, ContentGenerationMapper contentGenerationMapper) {
        this.contentGenerationRepository = contentGenerationRepository;
        this.contentGenerationMapper = contentGenerationMapper;
    }

    ContentGenerationResponse contentGenerationResponse = new ContentGenerationResponse();

    @Override
    public ContentGenerationResponse generatedContent(Integer unitId) {

        return contentGenerationMapper.toContentResponse(contentGenerationRepository.findById(Long.valueOf(unitId)).get());
    }

    @Override
    public String generatedContentStatus(Integer id) {

        return "";
    }

    @Override
    public String processPdf(MultipartFile file) {
        //todo call to course-management microservice to get the image.
        log.info("Image reached at content generation...");

        //todo call openAI microservice to do the processing.

        //todo save the processed information to the database.

        // todo add a counter if more than 5 searches send request to payments

        return "";
    }
}
