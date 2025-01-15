package com.samdev.content_generation_microservice.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "generated_content")
public class GeneratedContent {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="course_outline_id")
    private  Long courseOutlineId;
    @Lob
    @Column(name="generated_notes", columnDefinition = "TEXT")
    private String generatedNotes;
    @Column(name="summary_notes", columnDefinition = "TEXT")
    private String summary;
    @OneToMany(mappedBy = "generatedContent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> revisionQuestions;
    private String status;
    @CreatedDate
    private LocalDate generatedDate;
    private Integer studentId;

    public GeneratedContent() {
    }

    public GeneratedContent(Long id, Long courseOutlineId, String generatedNotes, String summary, List<Question> revisionQuestions, String status, LocalDate generatedDate, Integer studentId) {
        this.id = id;
        this.courseOutlineId = courseOutlineId;
        this.generatedNotes = generatedNotes;
        this.summary = summary;
        this.revisionQuestions = revisionQuestions;
        this.status = status;
        this.generatedDate = generatedDate;
        this.studentId = studentId;
    }



    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public Long getCourseOutlineId() {
        return courseOutlineId;
    }

    public String getGeneratedNotes() {
        return generatedNotes;
    }

    public String getSummary() {
        return summary;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getGeneratedDate() {
        return generatedDate;
    }

    public List<Question> getRevisionQuestions() {
        return revisionQuestions;
    }

    public void setRevisionQuestions(List<Question> revisionQuestions) {
        this.revisionQuestions = revisionQuestions;
    }

    public void setCourseOutlineId(Long courseOutlineId) {
        this.courseOutlineId = courseOutlineId;
    }

    public void setGeneratedNotes(String generatedNotes) {
        this.generatedNotes = generatedNotes;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setGeneratedDate(LocalDate generatedDate) {
        this.generatedDate = generatedDate;
    }
}
