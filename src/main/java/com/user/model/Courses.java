package com.user.model;

public class Courses {

    private int courseId;
    private String courseName;
    private String description;
    private int createdBy; // Foreign key to Instructors table

    // Default constructor
    public Courses() {
    }

    // Parameterized constructor
    public Courses(int courseId, String courseName, String description, int createdBy) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.createdBy = createdBy;
    }

    // Getters and Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Course [courseId=" + courseId + ", courseName=" + courseName + ", description=" + description
                + ", createdBy=" + createdBy + "]";
    }
}
