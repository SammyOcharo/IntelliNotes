package com.samdev.content_generation_microservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "revision_question")
public class Question {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    @Lob
    @Column(name="solution", columnDefinition = "TEXT")
    private String solution;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    private String relatedTopic;
    @ManyToOne
    @JoinColumn(name = "generated_content_id", nullable = false)
    private GeneratedContent generatedContent;

    public Question() {
    }

    public Question(Long id, String content, String solution, Difficulty difficulty, String relatedTopic, GeneratedContent generatedContent) {
        this.id = id;
        this.content = content;
        this.solution = solution;
        this.difficulty = difficulty;
        this.relatedTopic = relatedTopic;
        this.generatedContent = generatedContent;
    }

    public GeneratedContent getGeneratedContent() {
        return generatedContent;
    }

    public void setGeneratedContent(GeneratedContent generatedContent) {
        this.generatedContent = generatedContent;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getSolution() {
        return solution;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getRelatedTopic() {
        return relatedTopic;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setRelatedTopic(String relatedTopic) {
        this.relatedTopic = relatedTopic;
    }

}
