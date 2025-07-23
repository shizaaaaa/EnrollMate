package com.example.studentcourse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class StudentRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    // Constructors
    public StudentRequestDTO() {}

    public StudentRequestDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters & Setters
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}
