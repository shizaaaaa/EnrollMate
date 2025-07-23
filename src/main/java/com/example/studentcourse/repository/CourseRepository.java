package com.example.studentcourse.repository;

import com.example.studentcourse.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    //native queries
    @Query(value = "SELECT * FROM course WHERE course_code = :code", nativeQuery = true)
    Course findByCourseCode(@Param("code") String code);

    @Query(value = "SELECT * FROM course WHERE description LIKE %:desc%", nativeQuery = true)
    List<Course> findByDescriptionLike(@Param("desc") String desc);


}

