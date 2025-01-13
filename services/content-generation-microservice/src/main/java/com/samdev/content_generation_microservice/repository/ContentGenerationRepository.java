package com.samdev.content_generation_microservice.repository;

import com.samdev.content_generation_microservice.entity.GeneratedContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentGenerationRepository extends JpaRepository<GeneratedContent, Long> {
}
