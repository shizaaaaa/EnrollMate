package com.example.studentcourse.controller;

import com.example.studentcourse.dto.EnrollmentDTO;
import com.example.studentcourse.dto.EnrollmentRequestDTO;
import com.example.studentcourse.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // enroll a student in a course
    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<EnrollmentDTO> enrollStudent(
            @PathVariable Long studentId,
            @PathVariable Long courseId,
            @RequestBody EnrollmentRequestDTO requestDTO) {
        return ResponseEntity.ok(enrollmentService.enrollStudent(studentId, courseId,
                                                                 requestDTO.getGrade(), requestDTO.getAttendance()));
    }


    // get all enrollments
    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    // get enrollments by student id
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudentId(studentId));
    }

    // get enrollments by course id
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourseId(courseId));
    }

    // update enrollment
    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> updateEnrollment(
            @PathVariable Long id,
            @RequestBody EnrollmentRequestDTO requestDTO) {
        return ResponseEntity.ok(enrollmentService.updateEnrollment(id, requestDTO));
    }

    // delete enrollment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }


}

