package com.example.studentcourse.repository;

import com.example.studentcourse.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> { }
