package com.ardijorganxhi.studentinformationsystem.repository;

import com.ardijorganxhi.studentinformationsystem.model.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    @Query("SELECT sc from StudentCourse sc WHERE sc.student.id = ?1 AND sc.course.id = ?2")
    StudentCourse findByStudentIdAndCourseId(Long studentId, Long courseId);

    @Modifying
    @Transactional
    @Query("DELETE FROM StudentCourse sc WHERE sc.student.id = ?1 AND sc.course.id = ?2")
    void unEnrollFromCourse(Long studentId, Long courseId);
}
