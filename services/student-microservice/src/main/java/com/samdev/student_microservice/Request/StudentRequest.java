package com.samdev.student_microservice.Request;


public class StudentRequest {
    private String id;
    private String firstName;
    private String phoneNumber;
    private String lastName;
    private Integer otp;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    private String email;

    public StudentRequest() {
    }

    public StudentRequest(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Integer getOtp() {
        return otp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
