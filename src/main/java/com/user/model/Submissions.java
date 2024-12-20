package com.user.model;

import java.sql.Timestamp;

public class Submissions {

    private int submissionId;
    private int assignmentId;
    private int studentId;
    private Timestamp submissionDate;
    private String grade;

    // Default constructor
    public Submissions() {
    }

    // Parameterized constructor
    public Submissions(int submissionId, int assignmentId, int studentId, Timestamp submissionDate, String grade) {
        this.submissionId = submissionId;
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.submissionDate = submissionDate;
        this.grade = grade;
    }

    // Getters and Setters
    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Timestamp getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Timestamp submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Submission [submissionId=" + submissionId + ", assignmentId=" + assignmentId + ", studentId=" + studentId
                + ", submissionDate=" + submissionDate + ", grade=" + grade + "]";
    }
}
