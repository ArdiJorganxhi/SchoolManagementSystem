package com.ardijorganxhi.studentinformationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCourseDto {

    private String courseCode;
    private String courseName;
    private int midterm;
    private int finalExam;
}
