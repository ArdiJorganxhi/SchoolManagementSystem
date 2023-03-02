package com.ardijorganxhi.studentinformationsystem.repository;

import com.ardijorganxhi.studentinformationsystem.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
