package com.ardijorganxhi.studentinformationsystem.repository;

import com.ardijorganxhi.studentinformationsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<User, Long> {
}
