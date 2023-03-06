package com.ardijorganxhi.studentinformationsystem.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseDto {

    private String courseCode;
    private String courseName;
    private int midterm;
    private int finalExam;
}
