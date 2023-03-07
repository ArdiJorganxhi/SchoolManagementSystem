package com.ardijorganxhi.studentinformationsystem.dto;

import com.ardijorganxhi.studentinformationsystem.model.Course;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentCourseDto {

    private CourseDto course;
    private int midterm;
    private int finalExam;
}
