package com.samdev.content_generation_microservice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.samdev.content_generation_microservice.entity.Question;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("unused")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentGenerationResponse {
    private String generatedNotes;
    private String summary;
    private List<Question> questions;
    private LocalDate generatedDate;

    public ContentGenerationResponse() {
    }

    public ContentGenerationResponse(String generatedNotes, String summary, List<Question> questions, LocalDate generatedDate) {
        this.generatedNotes = generatedNotes;
        this.summary = summary;
        this.questions = questions;
        this.generatedDate = generatedDate;
    }

    public String getGeneratedNotes() {
        return generatedNotes;
    }

    public void setGeneratedNotes(String generatedNotes) {
        this.generatedNotes = generatedNotes;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public LocalDate getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(LocalDate generatedDate) {
        this.generatedDate = generatedDate;
    }
}
