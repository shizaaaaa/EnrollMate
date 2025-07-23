package com.example.studentcourse.dto;

import java.time.LocalDate;

public class EnrollmentDTO {

    private Long id;

    private Long studentId;
    private String studentName;

    private Long courseId;
    private String courseName;

    private LocalDate enrolledDate;

    private String grade;

    private Integer attendance;

    // Constructors
    public EnrollmentDTO() {}

    public EnrollmentDTO(Long id, Long studentId, String studentName, Long courseId, String courseName,
                         LocalDate enrolledDate, String grade, Integer attendance) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.enrolledDate = enrolledDate;
        this.grade = grade;
        this.attendance = attendance;
    }

    // Getters & Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }

    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }

    public void setStudentName(String studentName) { this.studentName = studentName; }

    public Long getCourseId() { return courseId; }

    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public LocalDate getEnrolledDate() { return enrolledDate; }

    public void setEnrolledDate(LocalDate enrolledDate) { this.enrolledDate = enrolledDate; }

    public String getGrade() { return grade; }

    public void setGrade(String grade) { this.grade = grade; }

    public Integer getAttendance() { return attendance; }

    public void setAttendance(Integer attendance) { this.attendance = attendance; }
}
