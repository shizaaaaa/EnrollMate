package com.example.studentcourse.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.studentcourse.dto.EnrollmentDTO;
import com.example.studentcourse.entity.Course;
import com.example.studentcourse.entity.Enrollment;
import com.example.studentcourse.entity.Student;
import com.example.studentcourse.repository.CourseRepository;
import com.example.studentcourse.repository.EnrollmentRepository;
import com.example.studentcourse.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentService.class);

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // enroll a student in course
    public EnrollmentDTO enrollStudent(Long studentId, Long courseId, String grade, Integer attendance) {
        logger.info("Enrolling student ID {} into course ID {}", studentId, courseId);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> {
                    logger.warn("Student not found with ID: {}", studentId);
                    return new EntityNotFoundException("Student not found");
                });

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> {
                    logger.warn("Course not found with ID: {}", courseId);
                    return new EntityNotFoundException("Course not found");
                });

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledDate(LocalDate.now());
        enrollment.setGrade(grade);
        enrollment.setAttendance(attendance);

        Enrollment saved = enrollmentRepository.save(enrollment);
        logger.info("Enrollment created with ID: {}", saved.getId());

        return new EnrollmentDTO(
                saved.getId(),
                student.getId(),
                student.getName(),
                course.getId(),
                course.getCourseName(),
                saved.getEnrolledDate(),
                saved.getGrade(),
                saved.getAttendance()
        );
    }

    // get all enrollments
    public List<EnrollmentDTO> getAllEnrollments() {
        logger.info("Fetching all enrollments");
        return enrollmentRepository.findAll().stream()
                .map(e -> new EnrollmentDTO(
                        e.getId(),
                        e.getStudent().getId(),
                        e.getStudent().getName(),
                        e.getCourse().getId(),
                        e.getCourse().getCourseName(),
                        e.getEnrolledDate(),
                        e.getGrade(),
                        e.getAttendance()
                ))
                .collect(Collectors.toList());
    }

    // get enrollments by student
    public List<EnrollmentDTO> getEnrollmentsByStudentId(Long studentId) {
        logger.info("Fetching enrollments for student ID: {}", studentId);
        return enrollmentRepository.findAll().stream()
                .filter(e -> e.getStudent().getId().equals(studentId))
                .map(e -> new EnrollmentDTO(
                        e.getId(),
                        e.getStudent().getId(),
                        e.getStudent().getName(),
                        e.getCourse().getId(),
                        e.getCourse().getCourseName(),
                        e.getEnrolledDate(),
                        e.getGrade(),
                        e.getAttendance()
                ))
                .collect(Collectors.toList());
    }

    // get enrollments by course
    public List<EnrollmentDTO> getEnrollmentsByCourseId(Long courseId) {
        logger.info("Fetching enrollments for course ID: {}", courseId);
        return enrollmentRepository.findAll().stream()
                .filter(e -> e.getCourse().getId().equals(courseId))
                .map(e -> new EnrollmentDTO(
                        e.getId(),
                        e.getStudent().getId(),
                        e.getStudent().getName(),
                        e.getCourse().getId(),
                        e.getCourse().getCourseName(),
                        e.getEnrolledDate(),
                        e.getGrade(),
                        e.getAttendance()
                ))
                .collect(Collectors.toList());
    }
}
