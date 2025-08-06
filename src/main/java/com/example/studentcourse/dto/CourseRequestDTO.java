package com.example.studentcourse.dto;

import jakarta.validation.constraints.NotBlank;

public class CourseRequestDTO {

    @NotBlank(message = "Course name is required")
    private String courseName;

    private String courseCode;

    private String description;

    public CourseRequestDTO() {}

    public CourseRequestDTO(String courseName, String description, String courseCode) {
        this.courseName = courseName;
        this.description = description;
        this.courseCode = courseCode;
    }


    public String getCourseName() { return courseName; }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
