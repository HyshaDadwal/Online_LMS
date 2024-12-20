package com.user.model;

public class Instructor {
    private int instructorId;
    private String fullName;
    private String qualification;
    private String phoneNumber;

    // Constructor
    public Instructor(int instructorId, String fullName, String qualification, String phoneNumber) {
        this.instructorId = instructorId;
        this.fullName = fullName;
        this.qualification = qualification;
        this.phoneNumber = phoneNumber;
    }

    // Default Constructor
    public Instructor() {
    }

    // Getters and Setters
    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorId=" + instructorId +
                ", fullName='" + fullName + '\'' +
                ", qualification='" + qualification + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
