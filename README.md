# EnrollMate 

A backend Student Course Management System (SCMS) built using Spring Boot and MySQL.

## Features
- Manage students and courses (CRUD)
- Enroll students into multiple courses
- Track enrollment details like grade, attendance, and date
- Uses layered architecture: Controller → Service → Repository
- Handles exceptions with a global handler
- Includes manual DTO mapping and logging (SLF4J)

## Tech Stack
- Spring Boot + JPA + Hibernate
- MySQL (H2 for testing)
- Java + Maven

## API Endpoints (sample)
POST /api/students
POST /api/courses
POST /api/enrollments/student/{studentId}/course/{courseId}
...

## Setup
Clone repo & open in IDE

Create student_course_db in MySQL

Run the app using mvn spring-boot:run

Test using Postman