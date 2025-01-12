package com.samdev.student_microservice.ServiceImpl;

import com.samdev.student_microservice.Exceptions.InvalidOtpException;
import com.samdev.student_microservice.Exceptions.StudentDoesNotExistException;
import com.samdev.student_microservice.Exceptions.StudentExistsException;
import com.samdev.student_microservice.Mappers.StudentRequestMapper;
import com.samdev.student_microservice.Request.StudentRequest;
import com.samdev.student_microservice.Response.StudentResponse;
import com.samdev.student_microservice.Entity.Student;
import com.samdev.student_microservice.Utils.StudentUtil;
import com.samdev.student_microservice.repository.StudentRepository;
import com.samdev.student_microservice.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRequestMapper studentRequestMapper;
    private final StudentRepository studentRepository;
    private final StudentUtil studentUlis;

    StudentResponse studentResponse = new StudentResponse();

    public StudentServiceImpl(StudentRequestMapper studentRequestMapper, StudentRepository studentRepository, StudentUtil studentUlis) {
        this.studentRequestMapper = studentRequestMapper;
        this.studentRepository = studentRepository;
        this.studentUlis = studentUlis;
    }

    @Override
    public StudentResponse RegisterStudent(StudentRequest studentRequest) {

        if(studentRepository.existsByEmail(studentRequest.getEmail())){
            throw new StudentExistsException("Student with this email exists");
        }

        Student student = studentRepository.save(studentRequestMapper.toStudent(studentRequest));

        StudentResponse studentResponse = new StudentResponse();
        studentRequest.setId(student.getId());
        studentResponse.setStudentRequest(studentRequest);
        studentResponse.setStatusCode(200);
        studentResponse.setStatusMessage("Student information saved successfully!");

        return studentResponse;
    }

    @Override
    public StudentResponse StudentLogin(StudentRequest courseRequest) {

        if(!studentRepository.existsByEmail(courseRequest.getEmail()))
            throw new StudentDoesNotExistException("Student with email does not exist!");

        StudentResponse studentResponse = new StudentResponse();

        studentResponse.setStatusMessage("Login successful");
        studentResponse.setStatusCode(200);


        return studentResponse;
    }

    @Override
    public StudentResponse ForgetPassword(StudentRequest courseRequest) {

        if(!studentRepository.existsByEmail(courseRequest.getEmail()))
            throw new StudentDoesNotExistException("Student with email does not exist!");

        Integer otp = studentUlis.generateOtp();

        //todo send an email to the given email with otp. Cache the otp with redis

        studentResponse.setStatusCode(200);
        studentResponse.setStatusMessage("forgot password otp sent to your email");

        return studentResponse;
    }

    @Override
    public StudentResponse StudentRequestForgotPasswordOtp(StudentRequest courseRequest) {
        if(!studentRepository.existsByEmail(courseRequest.getEmail()))
            throw new StudentDoesNotExistException("Student with email does not exist!");
        if(courseRequest.getOtp() == null)
            throw new InvalidOtpException("Invalid otp given!");

        //todo check otp from redis and verify if its correct then allow for password change


        studentResponse.setStatusCode(200);
        studentResponse.setStatusMessage("Password changed successfully");

        return studentResponse;
    }

}
