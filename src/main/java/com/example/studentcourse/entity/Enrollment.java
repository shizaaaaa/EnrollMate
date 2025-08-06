package com.example.studentcourse.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDate enrolledDate;

    private String grade;

    private Integer attendance;


    public Enrollment() {}

    public Enrollment(Student student, Course course, LocalDate enrolledDate, String grade, Integer attendance) {
        this.student = student;
        this.course = course;
        this.enrolledDate = enrolledDate;
        this.grade = grade;
        this.attendance = attendance;
    }


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }

    public void setStudent(Student student) { this.student = student; }

    public Course getCourse() { return course; }

    public void setCourse(Course course) { this.course = course; }

    public LocalDate getEnrolledDate() { return enrolledDate; }

    public void setEnrolledDate(LocalDate enrolledDate) { this.enrolledDate = enrolledDate; }

    public String getGrade() { return grade; }

    public void setGrade(String grade) { this.grade = grade; }

    public Integer getAttendance() { return attendance; }

    public void setAttendance(Integer attendance) { this.attendance = attendance; }
}
