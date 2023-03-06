package com.ardijorganxhi.studentinformationsystem.dto;

import com.ardijorganxhi.studentinformationsystem.model.Course;
import com.ardijorganxhi.studentinformationsystem.model.StudentCourse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TeacherDto {

    private long id;
    private String name;
    private String surname;
    private List<Course> teacherCourses;
}
