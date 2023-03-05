package com.ardijorganxhi.studentinformationsystem.repository;

import com.ardijorganxhi.studentinformationsystem.model.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
}
