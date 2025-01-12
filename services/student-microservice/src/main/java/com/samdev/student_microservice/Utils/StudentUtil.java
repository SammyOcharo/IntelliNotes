package com.samdev.student_microservice.Utils;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StudentUtil {

    public Integer generateOtp(){

        Random number = new Random();

        return 1000 + number.nextInt();
    }
}
