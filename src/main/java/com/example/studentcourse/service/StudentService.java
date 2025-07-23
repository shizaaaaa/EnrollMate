package com.example.studentcourse.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.studentcourse.dto.StudentDTO;
import com.example.studentcourse.dto.StudentRequestDTO;
import com.example.studentcourse.entity.Student;
import com.example.studentcourse.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // creating new student
    public StudentDTO createStudent(StudentRequestDTO requestDTO) {
        logger.info("Creating new student: {}", requestDTO.getName());
        Student student = new Student();
        student.setName(requestDTO.getName());
        student.setEmail(requestDTO.getEmail());

        Student saved = studentRepository.save(student);
        logger.info("Student created with ID: {}", saved.getId());
        return new StudentDTO(saved.getId(), saved.getName(), saved.getEmail());
    }

    // get all students
    public List<StudentDTO> getAllStudents() {
        logger.info("Fetching all students");
        return studentRepository.findAll().stream()
                .map(s -> new StudentDTO(s.getId(), s.getName(), s.getEmail()))
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long id) {
        logger.info("Fetching student by ID: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Student not found with ID: {}", id);
                    return new EntityNotFoundException("Student not found");
                });
        return new StudentDTO(student.getId(), student.getName(), student.getEmail());
    }

    // using jpql
    public List<StudentDTO> searchStudentsByName(String keyword) {
        logger.info("Searching students by name containing: {}", keyword);
        return studentRepository.searchByName("%" + keyword + "%").stream()
                .map(s -> new StudentDTO(s.getId(), s.getName(), s.getEmail()))
                .collect(Collectors.toList());
    }

    public StudentDTO updateStudent(Long id, StudentRequestDTO requestDTO) {
        logger.info("Updating student with ID: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Student not found with ID: {}", id);
                    return new EntityNotFoundException("Student not found");
                });

        student.setName(requestDTO.getName());
        student.setEmail(requestDTO.getEmail());

        Student updated = studentRepository.save(student);
        logger.info("Student updated with ID: {}", updated.getId());
        return new StudentDTO(updated.getId(), updated.getName(), updated.getEmail());
    }

    public void deleteStudent(Long id) {
        logger.info("Deleting student with ID: {}", id);
        studentRepository.deleteById(id);
    }
}
