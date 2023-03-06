package com.ardijorganxhi.studentinformationsystem.dto;

import com.ardijorganxhi.studentinformationsystem.model.StudentCourse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentDto {

    private long id;
    private String name;
    private String surname;
    private List<StudentCourse> studentCourses;
}
