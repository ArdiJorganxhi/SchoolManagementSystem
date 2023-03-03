package com.ardijorganxhi.studentinformationsystem.service;

import com.ardijorganxhi.studentinformationsystem.repository.GradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
}
