package com.example.studentcourse.dto;

public class EnrollmentRequestDTO {
    private String grade;
    private Integer attendance;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }
}
