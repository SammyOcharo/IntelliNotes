package com.samdev.student_microservice.ServiceImpl;

import com.samdev.student_microservice.Mappers.StudentRequestMapper;
import com.samdev.student_microservice.Response.StudentDTOResponse;
import com.samdev.student_microservice.repository.StudentRepository;
import com.samdev.student_microservice.service.StudentAccountService;
import org.springframework.stereotype.Service;

@Service
public class StudentAccountServiceImpl implements StudentAccountService {
    final StudentRepository studentRepository;
    final StudentRequestMapper studentRequestMapper;

    public StudentAccountServiceImpl(StudentRepository studentRepository, StudentRequestMapper studentRequestMapper) {
        this.studentRepository = studentRepository;
        this.studentRequestMapper = studentRequestMapper;
    }


    @Override
    public StudentDTOResponse StudentInformation(String id) {
        return studentRequestMapper.toStudentDTO(studentRepository.findById(id).get());
    }
}
