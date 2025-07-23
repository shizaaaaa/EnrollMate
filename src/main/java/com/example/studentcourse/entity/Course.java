package com.example.studentcourse.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false, unique = true)
    private String courseCode;

    private String description;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Enrollment> enrollments = new HashSet<>();

    // Constructors
    public Course() {}

    public Course(String courseName, String description, String courseCode) {
        this.courseName = courseName;
        this.description = description;
        this.courseCode = courseCode;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    public Set<Enrollment> getEnrollments() { return enrollments; }

    public void setEnrollments(Set<Enrollment> enrollments) { this.enrollments = enrollments; }
}

