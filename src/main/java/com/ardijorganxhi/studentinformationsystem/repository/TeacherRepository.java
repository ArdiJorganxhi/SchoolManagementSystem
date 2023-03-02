package com.ardijorganxhi.studentinformationsystem.repository;

import com.ardijorganxhi.studentinformationsystem.model.User;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<User, Long> {
}
