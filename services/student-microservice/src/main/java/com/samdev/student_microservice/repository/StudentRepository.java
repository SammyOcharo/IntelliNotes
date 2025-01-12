package com.samdev.student_microservice.repository;

import com.samdev.student_microservice.Entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    boolean existsByEmail(String email);
}
