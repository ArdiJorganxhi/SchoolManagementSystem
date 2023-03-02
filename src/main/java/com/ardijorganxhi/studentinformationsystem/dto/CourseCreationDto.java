package com.ardijorganxhi.studentinformationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CourseCreationDto {

    private String courseCode;
    private String courseName;
    private String lecturer;
    private int midterm;
    private int finalExam;
}
