package com.user.model;

import java.sql.Timestamp;

public class Enrollments {

    private int enrollmentId;
    private int studentId;
    private int courseId;
    private Timestamp enrolledAt;

    // Default constructor
    public Enrollments() {
    }

    // Parameterized constructor
    public Enrollments(int enrollmentId, int studentId, int courseId, Timestamp enrolledAt) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrolledAt = enrolledAt;
    }

    // Getters and Setters
    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Timestamp getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(Timestamp enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    @Override
    public String toString() {
        return "Enrollment [enrollmentId=" + enrollmentId + ", studentId=" + studentId + ", courseId=" + courseId
                + ", enrolledAt=" + enrolledAt + "]";
    }
}
