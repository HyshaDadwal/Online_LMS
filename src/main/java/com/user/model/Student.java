package com.user.model;

import java.sql.Date;

public class Student {

    private int studentId;
    private String fullName;
    private String enrollmentNumber;
    private Date dateOfBirth;
    private String phoneNumber;
    private int userId; // Foreign key to User table

    public Student() {
        super();
    }

    public Student(int studentId, String fullName, String enrollmentNumber, Date dateOfBirth, String phoneNumber, int userId) {
        super();
        this.studentId = studentId;
        this.fullName = fullName;
        this.enrollmentNumber = enrollmentNumber;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", fullName=" + fullName + ", enrollmentNumber=" + enrollmentNumber
                + ", dateOfBirth=" + dateOfBirth + ", phoneNumber=" + phoneNumber + ", userId=" + userId + "]";
    }
}
