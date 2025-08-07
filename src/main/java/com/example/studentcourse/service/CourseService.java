package com.example.studentcourse.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.studentcourse.dto.CourseDTO;
import com.example.studentcourse.dto.CourseRequestDTO;
import com.example.studentcourse.entity.Course;
import com.example.studentcourse.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseDTO createCourse(CourseRequestDTO requestDTO) {
        logger.info("Creating new course: {}", requestDTO.getCourseName());
        Course course = new Course();
        course.setCourseName(requestDTO.getCourseName());
        course.setDescription(requestDTO.getDescription());
        course.setCourseCode(requestDTO.getCourseCode());

        Course saved = courseRepository.save(course);
        logger.info("Course created with ID: {}", saved.getId());
        return new CourseDTO(saved.getId(), saved.getCourseName(), saved.getCourseCode(), saved.getDescription());
    }


    public List<CourseDTO> getAllCourses() {
        logger.info("Fetching all courses");
        return courseRepository.findAll().stream()
                .map(c -> new CourseDTO(c.getId(), c.getCourseName(), c.getCourseCode(), c.getDescription()))
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Long id) {
        logger.info("Fetching course by ID: {}", id);
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Course not found with ID: {}", id);
                    return new EntityNotFoundException("Course not found");
                });
        return new CourseDTO(course.getId(), course.getCourseName(), course.getCourseCode(), course.getDescription());
    }

    // using native
    public CourseDTO getCourseByCode(String code) {
        logger.info("Fetching course by code: {}", code);
        Course course = courseRepository.findByCourseCode(code);
        return new CourseDTO(course.getId(), course.getCourseName(), course.getCourseCode(), course.getDescription());
    }

    public List<CourseDTO> searchCoursesByDescription(String keyword) {
        logger.info("Searching courses with description like: {}", keyword);
        List<Course> courses = courseRepository.findByDescriptionLike(keyword);

        if (courses.isEmpty()) {
            throw new EntityNotFoundException("No courses found with description: " + keyword);
        }

        return courses.stream()
                .map(c -> new CourseDTO(c.getId(), c.getCourseName(), c.getCourseCode(), c.getDescription()))
                .collect(Collectors.toList());
    }

    public CourseDTO updateCourse(Long id, CourseRequestDTO requestDTO) {
        logger.info("Updating course with ID: {}", id);
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Course not found with ID: {}", id);
                    return new EntityNotFoundException("Course not found");
                });

        course.setCourseName(requestDTO.getCourseName());
        course.setDescription(requestDTO.getDescription());
        course.setCourseCode(requestDTO.getCourseCode());

        Course updated = courseRepository.save(course);
        logger.info("Course updated with ID: {}", updated.getId());
        return new CourseDTO(updated.getId(), updated.getCourseName(), updated.getCourseCode(), updated.getDescription());
    }

    public String deleteCourse(Long id) {
            Course course = courseRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        String courseName = course.getCourseName();
        logger.info("Deleting course with ID: {}", id);
        courseRepository.delete(course);

        return "Deleted course \"" + courseName + "\" (ID: " + id + ")";
    }
}
